package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.I_HR_Parametre_Numerique;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Moteur de calcul des indemnités de fin de contrat.
 *
 * LICENCIEMENT (Art. 37 Code du Travail camerounais + Arrêté 016/MTPS 26/05/1993) :
 *   Base    = moyenne du salaire brut mensuel (SBR) des 12 derniers mois
 *   Barème  = % par année d'ancienneté selon tranches légales
 *   Années 1–5   : 20% par an
 *   Années 6–10  : 25% par an
 *   Années 11–15 : 30% par an
 *   Années 16–20 : 35% par an
 *   Au-delà 21   : 40% par an
 *   Fraction ≥ 6 mois = 1 année complète
 *   Condition : ancienneté ≥ 2 ans, pas de faute lourde
 *
 * RETRAITE :
 *   Base = même calcul que licenciement
 *   × coefficient paramétrable (HR_Parametre_Numerique, value='COEF_RETRAITE')
 *   Valeur par défaut : 2 (convention collective la plus répandue au Cameroun)
 *
 * Extensibilité :
 *   - Modifier COEF_RETRAITE en base pour ajuster sans toucher au code
 *   - Les éléments entrant dans la base de calcul sont ceux marqués
 *     isindemnitelicenciement=Y dans HR_GestionPaieEmploye
 *     (actuellement on utilise SBR = salaire brut global)
 */
public class IndemniteEngine {

    private static final CLogger log = CLogger.getCLogger(IndemniteEngine.class);

    // Valeur du paramètre retraite dans HR_Parametre_Numerique
    private static final String PARAM_COEF_RETRAITE = "COEF_RETRAITE";

    // Code élément SBR dans HR_Element_Base_Paie
    private static final String CODE_SBR = "SBR";

    // Ancienneté minimale pour avoir droit à l'indemnité de licenciement (2 ans)
    private static final int ANCIENNETE_MIN_ANNEES = 2;

    // -------------------------------------------------------------------------
    // API publique
    // -------------------------------------------------------------------------

    /**
     * Calcule l'indemnité de licenciement légale camerounaise.
     *
     * @param bpartnerId  ID de l'employé
     * @param trxName     transaction active
     * @return            montant de l'indemnité, ou ZERO si conditions non remplies
     */
    public static BigDecimal calculerIndemniteLicenciement(int bpartnerId, String trxName) {
        // 1. Charger la date d'embauche
        Timestamp dateEmbauche = getDateEmbauche(bpartnerId, trxName);
        if (dateEmbauche == null) {
            log.warning("calculerIndemniteLicenciement : date embauche introuvable pour bpartnerId=" + bpartnerId);
            return BigDecimal.ZERO;
        }

        // 2. Calculer l'ancienneté
        int[] anciennete = calculerAnciennete(dateEmbauche);
        int anneesCompletes = anciennete[0];
        int moisRestants    = anciennete[1];

        // Fraction >= 6 mois = 1 année complète
        if (moisRestants >= 6) anneesCompletes++;

        // Condition minimale : 2 ans d'ancienneté
        if (anneesCompletes < ANCIENNETE_MIN_ANNEES) {
            log.info("calculerIndemniteLicenciement : ancienneté insuffisante (" 
                    + anneesCompletes + " an(s)) pour bpartnerId=" + bpartnerId);
            return BigDecimal.ZERO;
        }

        // 3. Calculer la moyenne SBR des 12 derniers mois
        BigDecimal moyenneSBR = getMoyenneSBR12Mois(bpartnerId, trxName);
        if (moyenneSBR.compareTo(BigDecimal.ZERO) == 0) {
            log.warning("calculerIndemniteLicenciement : aucun historique SBR pour bpartnerId=" + bpartnerId);
            return BigDecimal.ZERO;
        }

        // 4. Appliquer le barème légal
        return appliquerBaremeLicenciement(moyenneSBR, anneesCompletes);
    }

    /**
     * Calcule l'indemnité de retraite.
     * = indemnité de licenciement × coefficient paramétrable (COEF_RETRAITE)
     *
     * @param bpartnerId  ID de l'employé
     * @param trxName     transaction active
     * @return            montant de l'indemnité de retraite
     */
    public static BigDecimal calculerIndemniteRetraite(int bpartnerId, String trxName) {
        BigDecimal indemntieLicenciement = calculerIndemniteLicenciement(bpartnerId, trxName);
        if (indemntieLicenciement.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        BigDecimal coefRetraite = getCoefRetraite(trxName);
        return indemntieLicenciement.multiply(coefRetraite)
                .setScale(0, RoundingMode.FLOOR);
    }

    // -------------------------------------------------------------------------
    // Barème légal licenciement
    // -------------------------------------------------------------------------

    /**
     * Applique le barème légal camerounais par tranches d'ancienneté.
     *
     * Exemple : 7 ans d'ancienneté, SBR moyen = 300 000 FCFA
     *   T1 : 5 ans × 20% × 300 000 =  300 000
     *   T2 : 2 ans × 25% × 300 000 =  150 000
     *   Total =  450 000 FCFA
     */
    private static BigDecimal appliquerBaremeLicenciement(BigDecimal moyenneSBR, int anneesTotal) {
        BigDecimal total = BigDecimal.ZERO;
        int anneesRestantes = anneesTotal;

        // Tranche 1 : années 1 à 5 → 20%
        if (anneesRestantes > 0) {
            int anneesT1 = Math.min(anneesRestantes, 5);
            total = total.add(
                moyenneSBR
                    .multiply(new BigDecimal("0.20"))
                    .multiply(new BigDecimal(anneesT1))
            );
            anneesRestantes -= anneesT1;
        }

        // Tranche 2 : années 6 à 10 → 25%
        if (anneesRestantes > 0) {
            int anneesT2 = Math.min(anneesRestantes, 5);
            total = total.add(
                moyenneSBR
                    .multiply(new BigDecimal("0.25"))
                    .multiply(new BigDecimal(anneesT2))
            );
            anneesRestantes -= anneesT2;
        }

        // Tranche 3 : années 11 à 15 → 30%
        if (anneesRestantes > 0) {
            int anneesT3 = Math.min(anneesRestantes, 5);
            total = total.add(
                moyenneSBR
                    .multiply(new BigDecimal("0.30"))
                    .multiply(new BigDecimal(anneesT3))
            );
            anneesRestantes -= anneesT3;
        }

        // Tranche 4 : années 16 à 20 → 35%
        if (anneesRestantes > 0) {
            int anneesT4 = Math.min(anneesRestantes, 5);
            total = total.add(
                moyenneSBR
                    .multiply(new BigDecimal("0.35"))
                    .multiply(new BigDecimal(anneesT4))
            );
            anneesRestantes -= anneesT4;
        }

        // Tranche 5 : au-delà de 20 ans → 40%
        if (anneesRestantes > 0) {
            total = total.add(
                moyenneSBR
                    .multiply(new BigDecimal("0.40"))
                    .multiply(new BigDecimal(anneesRestantes))
            );
        }

        return total.setScale(0, RoundingMode.FLOOR);
    }

    // -------------------------------------------------------------------------
    // Méthodes privées — données
    // -------------------------------------------------------------------------

    /**
     * Retourne la date d'embauche de l'employé depuis HR_ElementBasePaieEmploye.
     */
    private static Timestamp getDateEmbauche(int bpartnerId, String trxName) {
        String sql = "SELECT " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut
                + " FROM " + I_HR_ElementBasePaieEmploye.Table_Name
                + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_IsActive + "='Y'"
                + " ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " ASC"
                + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getTimestamp(I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut);
            }
        } catch (SQLException e) {
            log.severe("getDateEmbauche : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Calcule l'ancienneté entre la date d'embauche et aujourd'hui.
     * @return int[0] = années complètes, int[1] = mois restants
     */
    private static int[] calculerAnciennete(Timestamp dateEmbauche) {
        LocalDate debut = dateEmbauche.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate aujourdhui = LocalDate.now();
        Period periode = Period.between(debut, aujourdhui);
        return new int[]{periode.getYears(), periode.getMonths()};
    }

    /**
     * Calcule la moyenne du SBR sur les 12 derniers mois depuis HR_Historique_Paie.
     */
    private static BigDecimal getMoyenneSBR12Mois(int bpartnerId, String trxName) {
        String sql = "SELECT AVG(h." + I_HR_Historique_Paie.COLUMNNAME_Montant + ")"
                + " FROM " + I_HR_Historique_Paie.Table_Name + " h"
                + " INNER JOIN adempiere.hr_element_base_paie e"
                + " ON e.hr_element_base_paie_id = h." + I_HR_Historique_Paie.COLUMNNAME_HR_Element_Base_Paie_ID
                + " WHERE h." + I_HR_Historique_Paie.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND e.value=?"
                + " ORDER BY h." + I_HR_Historique_Paie.COLUMNNAME_Date_Debut + " DESC"
                + " LIMIT 12";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setString(2, CODE_SBR);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal moyenne = rs.getBigDecimal(1);
                return moyenne != null ? moyenne : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            log.severe("getMoyenneSBR12Mois : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return BigDecimal.ZERO;
    }

    /**
     * Lit le coefficient retraite depuis HR_Parametre_Numerique.
     * Valeur par défaut : 2 si le paramètre est absent.
     */
    private static BigDecimal getCoefRetraite(String trxName) {
        String sql = "SELECT valeur_parametre"
                + " FROM adempiere.hr_parametre_numerique"
                + " WHERE value=?"
                + " AND isactive='Y'"
                + " AND ad_client_id=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setString(1, PARAM_COEF_RETRAITE);
            pstmt.setInt(2, Env.getAD_Client_ID(Env.getCtx()));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal coef = rs.getBigDecimal("valeur_parametre");
                if (coef != null && coef.compareTo(BigDecimal.ZERO) > 0) {
                    return coef;
                }
            }
        } catch (SQLException e) {
            log.warning("getCoefRetraite : paramètre introuvable, valeur par défaut 2 utilisée. " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        // Valeur par défaut légale
        return new BigDecimal("2");
    }
}
