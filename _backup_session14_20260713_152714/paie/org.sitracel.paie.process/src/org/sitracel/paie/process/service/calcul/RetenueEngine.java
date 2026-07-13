package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_Retenue_Salariale;
import org.sitracel.paie.model.MHRRetenueSalariale;

/**
 * Moteur de gestion des retenues et indemnités versées.
 *
 * RETENUES (soustraites du NP) :
 *   - Dettes / acomptes : isindemnitelicenciement=N, isindemniteretraite=N
 *
 * INDEMNITÉS VERSÉES (ajoutées au NP) :
 *   - Indemnité de licenciement : isindemnitelicenciement=Y
 *   - Indemnité de retraite     : isindemniteretraite=Y
 *
 * Une retenue/indemnité est active pour une période si :
 *   - isactive = Y
 *   - reste_retenue > 0
 *   - periode >= debut_prelevement_id
 *   - periode <= fin_prelevement_id (si défini)
 *
 * Extensibilité :
 *   Pour ajouter un nouveau type de retenue ou d'indemnité,
 *   il suffit d'insérer un enregistrement dans HR_RetenueSalariale
 *   avec les bons flags — zéro modification Java.
 */
public class RetenueEngine {

    private static final CLogger log = CLogger.getCLogger(RetenueEngine.class);

    /**
     * Résultat du traitement des retenues pour un employé et une période.
     * Contient le total à soustraire ET le total à ajouter au NP.
     */
    public static class ResultatRetenues {
        /** Montant total des dettes/acomptes à soustraire du NP */
        public final BigDecimal totalRetenues;
        /** Montant total des indemnités à ajouter au NP */
        public final BigDecimal totalIndemnites;

        public ResultatRetenues(BigDecimal totalRetenues, BigDecimal totalIndemnites) {
            this.totalRetenues   = totalRetenues   != null ? totalRetenues   : BigDecimal.ZERO;
            this.totalIndemnites = totalIndemnites != null ? totalIndemnites : BigDecimal.ZERO;
        }

        /** NP final = NP courant - retenues + indemnités */
        public BigDecimal appliquerSur(BigDecimal npCourant) {
            if (npCourant == null) npCourant = BigDecimal.ZERO;
            return npCourant.subtract(totalRetenues).add(totalIndemnites);
        }
    }

    /**
     * Traite toutes les retenues et indemnités actives pour un employé
     * sur une période donnée.
     *
     * Pour chaque retenue active :
     *   - Calcule le montant à prélever ce mois
     *   - Met à jour reste_retenue en base
     *   - Désactive si reste_retenue atteint zéro
     *
     * @param bpartnerId  ID de l'employé
     * @param periodeId   ID de la période salariale courante
     * @param trxName     transaction active
     * @return            ResultatRetenues avec les totaux à appliquer sur le NP
     */
    public static ResultatRetenues traiter(int bpartnerId, int periodeId, String trxName) {
        List<MHRRetenueSalariale> retenuesActives = getRetenuesActives(bpartnerId, periodeId, trxName);

        BigDecimal totalRetenues   = BigDecimal.ZERO;
        BigDecimal totalIndemnites = BigDecimal.ZERO;

        for (MHRRetenueSalariale retenue : retenuesActives) {
            BigDecimal montantCeMois = calculerMontantCeMois(retenue);

            if (montantCeMois.compareTo(BigDecimal.ZERO) <= 0) continue;

            // Mettre à jour le reste
            BigDecimal nouveauReste = retenue.getReste_Retenue().subtract(montantCeMois);
            if (nouveauReste.compareTo(BigDecimal.ZERO) < 0) {
                nouveauReste = BigDecimal.ZERO;
            }
            retenue.setReste_Retenue(nouveauReste);

            // Désactiver si soldé
            if (nouveauReste.compareTo(BigDecimal.ZERO) == 0) {
                retenue.setIsActive(false);
            }

            retenue.save();

            // Classer : indemnité versée OU retenue prélevée
            if (estIndemniteVersee(retenue)) {
                totalIndemnites = totalIndemnites.add(montantCeMois);
            } else {
                totalRetenues = totalRetenues.add(montantCeMois);
            }
        }

        return new ResultatRetenues(totalRetenues, totalIndemnites);
    }

    // -------------------------------------------------------------------------
    // Méthodes privées
    // -------------------------------------------------------------------------

    /**
     * Charge toutes les retenues actives pour un employé et une période.
     * Une retenue est active si :
     *   - isactive = Y ET reste_retenue > 0
     *   - La période courante >= periode de début
     *   - La période courante <= periode de fin (si définie)
     */
    private static List<MHRRetenueSalariale> getRetenuesActives(
            int bpartnerId, int periodeId, String trxName) {

        List<MHRRetenueSalariale> resultat = new ArrayList<>();

        String sql = "SELECT * FROM " + I_HR_Retenue_Salariale.Table_Name
                + " WHERE " + I_HR_Retenue_Salariale.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Retenue_Salariale.COLUMNNAME_IsActive + "='Y'"
                + " AND " + I_HR_Retenue_Salariale.COLUMNNAME_Reste_Retenue + ">0"
                + " AND " + I_HR_Retenue_Salariale.COLUMNNAME_Debut_Prelevement_ID + "<=?"
                + " AND ("
                +     I_HR_Retenue_Salariale.COLUMNNAME_Fin_Prelevement_ID + " IS NULL"
                +     " OR " + I_HR_Retenue_Salariale.COLUMNNAME_Fin_Prelevement_ID + ">=?"
                + ")";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, periodeId);
            pstmt.setInt(3, periodeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRRetenueSalariale(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.severe("getRetenuesActives [bpartnerId=" + bpartnerId + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return resultat;
    }

    /**
     * Calcule le montant à prélever/verser ce mois pour une retenue.
     *
     * Règle :
     *   - Si c'est la dernière mensualité (reste <= mensualité normale)
     *     → on prend exactement le reste (montant_derniere_mensualite si défini)
     *   - Sinon → montant_mensualite normal
     */
    private static BigDecimal calculerMontantCeMois(MHRRetenueSalariale retenue) {
        BigDecimal reste      = retenue.getReste_Retenue();
        BigDecimal mensualite = retenue.getMontant_Mensualite();

        if (reste == null || reste.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        if (mensualite == null || mensualite.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        // Dernière mensualité
        if (reste.compareTo(mensualite) <= 0) {
            BigDecimal derniere = retenue.getMontant_Derniere_Mensualite();
            return (derniere != null && derniere.compareTo(BigDecimal.ZERO) > 0)
                    ? derniere
                    : reste;
        }

        return mensualite;
    }

    /**
     * Détermine si une retenue est une indemnité versée à l'employé
     * (à ajouter au NP) plutôt qu'une retenue prélevée (à soustraire).
     */
    private static boolean estIndemniteVersee(MHRRetenueSalariale retenue) {
        return retenue.isIndemniteRetraite()
                || retenue.isIndemniteLicenciement();
    }
}
