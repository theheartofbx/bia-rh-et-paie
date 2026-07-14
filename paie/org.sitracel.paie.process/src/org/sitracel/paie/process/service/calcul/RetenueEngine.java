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
import org.sitracel.paie.model.I_HR_Mouvement_Paie;
import org.sitracel.paie.model.MHRMouvementPaie;

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
 *   - solde > 0
 *   - periode >= debut_prelevement_id
 *   - periode <= fin_prelevement_id (si défini)
 *
 * Extensibilité :
 *   Pour ajouter un nouveau type de retenue ou d'indemnité,
 *   il suffit d'insérer un enregistrement dans HR_MouvementPaie
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
     *   - Met à jour solde en base
     *   - Désactive si solde atteint zéro
     *
     * @param bpartnerId  ID de l'employé
     * @param periodeId   ID de la période salariale courante
     * @param trxName     transaction active
     * @return            ResultatRetenues avec les totaux à appliquer sur le NP
     */
    public static ResultatRetenues traiter(int bpartnerId, int periodeId, String trxName) {
        // Traiter séparément retenues (IsIndemnite='N') et indemnités (IsIndemnite='Y')
        BigDecimal totalRetenues   = traiterMouvements(bpartnerId, periodeId, "N", trxName);
        BigDecimal totalIndemnites = traiterMouvements(bpartnerId, periodeId, "Y", trxName);
        return new ResultatRetenues(totalRetenues, totalIndemnites);
    }

    /**
     * Traite les mouvements d'un type donné (retenues OU indemnités).
     * Pour chaque mouvement actif, calcule le montant du mois, met à jour
     * le solde et désactive si soldé.
     *
     * @param isIndemnite  "Y" = indemnités (ajoutées au NP),
     *                     "N" = retenues (soustraites du NP)
     * @return             total des montants traités ce mois
     */
    private static BigDecimal traiterMouvements(int bpartnerId, int periodeId,
                                                 String isIndemnite, String trxName) {
        List<MHRMouvementPaie> mouvements =
                getMouvementsActifs(bpartnerId, periodeId, isIndemnite, trxName);

        BigDecimal total = BigDecimal.ZERO;

        for (MHRMouvementPaie mouvement : mouvements) {
            BigDecimal montantCeMois = calculerMontantCeMois(mouvement);

            if (montantCeMois.compareTo(BigDecimal.ZERO) <= 0) continue;

            // Mode récurrent : ne pas toucher au solde, ne jamais désactiver
            if (!"Y".equals(mouvement.getIsRecurrent())) {
                BigDecimal nouveauSolde = mouvement.getSolde().subtract(montantCeMois);
                if (nouveauSolde.compareTo(BigDecimal.ZERO) < 0) {
                    nouveauSolde = BigDecimal.ZERO;
                }
                mouvement.setSolde(nouveauSolde);
                if (nouveauSolde.compareTo(BigDecimal.ZERO) == 0) {
                    mouvement.setIsActive(false);
                }
                mouvement.save();
            }
            total = total.add(montantCeMois);

            log.fine("Mouvement traité [" + mouvement.getName()
                    + "] montant=" + montantCeMois
                    + " solde restant=" + nouveauSolde
                    + " isIndemnite=" + isIndemnite);
        }

        return total;
    }

    // -------------------------------------------------------------------------
    // Méthodes privées
    // -------------------------------------------------------------------------

    /**
     * Charge les mouvements actifs d'un type donné pour un employé et une période.
     *
     * Un mouvement est actif si :
     *   - IsActive = 'Y' ET Solde > 0
     *   - La période courante >= Debut_Prelevement_ID
     *   - La période courante <= Fin_Prelevement_ID (si définie)
     *
     * @param isIndemnite  "Y" = indemnités, "N" = retenues
     */
    private static List<MHRMouvementPaie> getMouvementsActifs(
            int bpartnerId, int periodeId, String isIndemnite, String trxName) {

        List<MHRMouvementPaie> resultat = new ArrayList<>();

        // Un mouvement est actif si :
        //   - Mode normal (IsRecurrent='N') : IsActive='Y' ET Solde > 0
        //   - Mode récurrent (IsRecurrent='Y') : IsActive='Y' (pas de solde requis)
        //   - Dans les deux cas : période dans la plage de prélèvement
        //   - IsIndemnite=NULL traité comme 'N' (retenue) par défaut
        String sql = "SELECT * FROM " + I_HR_Mouvement_Paie.Table_Name
                + " WHERE " + I_HR_Mouvement_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_Mouvement_Paie.COLUMNNAME_IsActive + "='Y'"
                + " AND (IsRecurrent='Y' OR " + I_HR_Mouvement_Paie.COLUMNNAME_Solde + ">0)"
                + " AND " + I_HR_Mouvement_Paie.COLUMNNAME_Debut_Prelevement_ID + "<=?"
                + " AND ("
                +     I_HR_Mouvement_Paie.COLUMNNAME_Fin_Prelevement_ID + " IS NULL"
                +     " OR " + I_HR_Mouvement_Paie.COLUMNNAME_Fin_Prelevement_ID + ">=?"
                + ")"
                + " AND COALESCE(IsIndemnite,'N')=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, periodeId);
            pstmt.setInt(3, periodeId);
            pstmt.setString(4, isIndemnite);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRMouvementPaie(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.severe("getMouvementsActifs [bpartnerId=" + bpartnerId
                    + " isIndemnite=" + isIndemnite + "] : " + e.getMessage());
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
    /**
     * Calcule le montant à prélever/verser ce mois.
     *
     * Mode récurrent (IsRecurrent='Y') :
     *   → Montant_Mensualite fixe chaque mois, sans décrémenter le solde
     *
     * Mode normal (IsRecurrent='N') :
     *   → Si reste <= mensualite → dernière mensualité (absorbe les centimes)
     *   → Sinon → mensualite normale
     */
    private static BigDecimal calculerMontantCeMois(MHRMouvementPaie mouvement) {
        BigDecimal mensualite = mouvement.getMontant_Mensualite();

        // Mode récurrent : appliquer la mensualité fixe sans limite
        boolean recurrent = "Y".equals(mouvement.getIsRecurrent());
        if (recurrent) {
            if (mensualite == null || mensualite.compareTo(BigDecimal.ZERO) <= 0) {
                // Fallback : utiliser Montant_Total comme mensualité mensuelle
                BigDecimal total = mouvement.getMontant_Total();
                return (total != null) ? total : BigDecimal.ZERO;
            }
            return mensualite;
        }

        // Mode normal : décompter sur le solde
        BigDecimal solde = mouvement.getSolde();
        if (solde == null || solde.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }
        if (mensualite == null || mensualite.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        // Dernière mensualité
        if (solde.compareTo(mensualite) <= 0) {
            BigDecimal derniere = mouvement.getMontant_Derniere_Mensualite();
            return (derniere != null && derniere.compareTo(BigDecimal.ZERO) > 0)
                    ? derniere : solde;
        }

        return mensualite;
    }

    // ---------------------------------------------------------------
    // estIndemniteVersee() SUPPRIMÉE — Session 14
    // Le tri retenues/indemnités se fait désormais au niveau SQL
    // via la colonne IsIndemnite dans getMouvementsActifs().
    //
    // EXTENSIBILITÉ : pour ajouter un nouveau type d'indemnité,
    // créer un enregistrement HR_Mouvement_Paie avec IsIndemnite='Y'.
    // Aucune modification Java requise.
    // ---------------------------------------------------------------
}
