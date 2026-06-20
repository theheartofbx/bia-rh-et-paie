package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.X_C_BPartner;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.process.service.PayrollRepository;
import org.sitracel.paie.process.service.persistence.PayrollPersistence;

/**
 * Moteur de calcul des indemnités de congé.
 *
 * PÉRIODE DE RÉFÉRENCE :
 *   De date_dernier_conge (ou date_embauche si premier congé)
 *   jusqu'à date_debut_effective du congé actuel.
 *   Sur cette période, on cumule les SBR mois par mois depuis
 *   HR_Calcul_Indemnite_Conge → total stocké dans HR_Holiday.salaire_cotisable.
 *
 * VARIABLES INJECTÉES dans les formules :
 *   SC   = salaire_cotisable (cumul SBR sur période référence, depuis hr_holiday)
 *   NJC  = nombre_jour_conge (jours à indemniser, depuis hr_holiday)
 *   SCPR = même valeur que SC (alias pour compatibilité ancien code)
 *   NJCI = nombre de jours de congé de base accumulés
 *   SB   = salaire de base actuel (depuis contrat employé)
 *   CEMS = jours bonus enfants (2j/enfant < 6 ans, femmes uniquement)
 *   CANC = jours bonus ancienneté (2j par tranche de 3 ans)
 *
 * Séquence :
 *   1. Vérifier que salaire_cotisable et nombre_jour_conge sont renseignés
 *   2. Calculer les variables de base (CEMS, CANC, SB, SCPR, NJCI)
 *   3. Calculer tous les éléments congé dans l'ordre des rangs
 *   4. Calculer le Net à Payer congé
 */
public class CongeCalculEngine {

    private static final CLogger log = CLogger.getCLogger(CongeCalculEngine.class);

    // Variables injectées dans les formules
    private static final String CODE_CEMS = "CEMS";
    private static final String CODE_CANC = "CANC";
    private static final String CODE_SC   = "SC";
    private static final String CODE_SCPR = "SCPR";
    private static final String CODE_NJC  = "NJC";
    private static final String CODE_NJCI = "NJCI";
    private static final String CODE_SB   = "SB";
    private static final String CODE_IBC  = "IBC";
    private static final String CODE_NP   = "NP";

    // -------------------------------------------------------------------------
    // API publique
    // -------------------------------------------------------------------------

    /**
     * Calcule les indemnités de congé pour un employé partant en congé.
     *
     * Prérequis : hr_holiday.salaire_cotisable et nombre_jour_conge
     * doivent être renseignés (calculés lors de la validation du congé).
     *
     * @param bpartner  employé concerné
     * @param holiday   congé validé
     * @param trxName   transaction active
     * @return          true si le calcul s'est bien déroulé
     */
    public static boolean calculerIndemniteConge(MCBPartner bpartner,
                                                  MHRHoliday holiday,
                                                  String trxName) {
        if (bpartner == null || holiday == null) {
            log.warning("calculerIndemniteConge : paramètres null");
            return false;
        }

        int bpartnerId = bpartner.getC_BPartner_ID();
        log.info("Début calcul indemnité congé — bpartnerId=" + bpartnerId
                + " holidayId=" + holiday.getHR_Holiday_ID());

        // ------------------------------------------------------------------
        // ÉTAPE 1 — Vérifier la base de calcul depuis hr_holiday
        // ------------------------------------------------------------------
        BigDecimal salaireCotisable = holiday.getSalaire_Cotisable();
        BigDecimal nombreJourConge  = holiday.getNombre_Jour_Conge();

        if (salaireCotisable == null || salaireCotisable.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("calculerIndemniteConge : salaire_cotisable absent ou nul "
                    + "— vérifier que le congé a bien été validé avec calcul de la "
                    + "période de référence (date_dernier_conge → date_debut_effective)");
            return false;
        }

        if (nombreJourConge == null || nombreJourConge.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("calculerIndemniteConge : nombre_jour_conge absent ou nul");
            return false;
        }

        // ------------------------------------------------------------------
        // ÉTAPE 2 — Réinitialiser les anciens calculs congé
        // ------------------------------------------------------------------
        PayrollPersistence.resetCalculConge(bpartnerId, trxName);
        PayrollPersistence.resetIndemniteConge(bpartnerId, trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 3 — Charger les données employé (ancienneté, enfants, contrat)
        // ------------------------------------------------------------------
        MHREmployeeChildren donneesConge = MHREmployeeChildren.getEnfantMoins6(
                bpartnerId,
                holiday.getDate_Debut_Effective(),
                null,
                trxName);

        MHRElementBasePaieEmploye contrat = getContratActifALaDate(
                bpartnerId,
                holiday.getDate_Debut_Effective(),
                trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 4 — Initialiser les variables
        // ------------------------------------------------------------------
        Map<String, BigDecimal> variables = new HashMap<>();

        // Base période de référence — cumul SBR de date_dernier_conge à date_debut_effective
        // Stocké dans hr_holiday.salaire_cotisable lors de la validation du congé
        variables.put(CODE_SC,   salaireCotisable);
        variables.put(CODE_SCPR, salaireCotisable); // alias pour compatibilité formules

        // Nombre de jours de congé à indemniser
        variables.put(CODE_NJC, nombreJourConge);

        // Nombre de jours de congé de base accumulés (depuis hr_holiday)
        BigDecimal njci = holiday.getJours_Conge_Total() != null
                ? BigDecimal.valueOf(holiday.getJours_Conge_Total())
                : nombreJourConge;
        variables.put(CODE_NJCI, njci);

        // Salaire de base actuel depuis le contrat
        BigDecimal sb = BigDecimal.ZERO;
        if (contrat != null && contrat.getSalaire_Base() != null) {
            sb = contrat.getSalaire_Base();
        }
        variables.put(CODE_SB, sb);

        // CEMS : 2 jours par enfant de moins de 6 ans (femmes uniquement)
        BigDecimal cems = BigDecimal.ZERO;
        if (donneesConge != null
                && donneesConge.getNombreEnfantPetit() != null
                && X_C_BPartner.SEX_Femme.equalsIgnoreCase(donneesConge.getGenre())) {
            cems = BigDecimal.valueOf(donneesConge.getNombreEnfantPetit() * 2);
        }
        sauvegarderEtStockerConge(bpartnerId, holiday, CODE_CEMS, cems, variables, trxName);

        // CANC : 2 jours par tranche de 3 ans d'ancienneté
        BigDecimal canc = BigDecimal.ZERO;
        if (donneesConge != null && donneesConge.getMoisAnciennete() != null) {
            int nbAnnees = donneesConge.getMoisAnciennete() / 36;
            canc = BigDecimal.valueOf(nbAnnees * 2);
        }
        sauvegarderEtStockerConge(bpartnerId, holiday, CODE_CANC, canc, variables, trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 5 — Calculer tous les éléments dans l'ordre des rangs
        // ------------------------------------------------------------------
        List<MHRElementConge> elements = PayrollRepository.getElementsCongeInitialValues(trxName);
        if (elements == null || elements.isEmpty()) {
            log.warning("calculerIndemniteConge : aucun élément congé défini");
            return false;
        }

        for (MHRElementConge element : elements) {
            calculerElementConge(bpartnerId, holiday, element, variables, trxName);
        }

        // ------------------------------------------------------------------
        // ÉTAPE 6 — Net à Payer congé
        // ------------------------------------------------------------------
        calculerNetAPayer(bpartnerId, holiday, variables, trxName);

        log.info("Fin calcul indemnité congé — bpartnerId=" + bpartnerId
                + " SC=" + salaireCotisable
                + " NJC=" + nombreJourConge
                + " NP=" + variables.getOrDefault(CODE_NP, BigDecimal.ZERO));
        return true;
    }

    // -------------------------------------------------------------------------
    // Calcul d'un élément congé
    // -------------------------------------------------------------------------

    private static void calculerElementConge(int bpartnerId,
                                              MHRHoliday holiday,
                                              MHRElementConge element,
                                              Map<String, BigDecimal> variables,
                                              String trxName) {
        if (element == null || element.getValue() == null) return;

        String codeElement = element.getValue();
        BigDecimal montant = BigDecimal.ZERO;
        String typeCalcul  = getTypeCalculNom(element.getHR_Type_Calcul_ID(), trxName);

        if (typeCalcul == null || typeCalcul.equalsIgnoreCase("Aucun")) {
            montant = variables.getOrDefault(codeElement, BigDecimal.ZERO);

        } else if (typeCalcul.equalsIgnoreCase("Formule")) {
            montant = FormulaEvaluator.evaluer(codeElement, element.getFormule(), variables);

        } else if (typeCalcul.equalsIgnoreCase("Pourcentage")) {
            BigDecimal base = getBaseConge(element, variables, trxName);
            montant = base.multiply(element.getPourcentage());

        } else if (typeCalcul.equalsIgnoreCase("Barème")) {
            BigDecimal base = getBaseConge(element, variables, trxName);
            MHRBaremeConge bareme = PayrollRepository.getBaremeConge(
                    element.getHR_Element_Conge_ID(), base, trxName);
            if (bareme != null) montant = bareme.getMontant();

        } else if (typeCalcul.equalsIgnoreCase("Formule-Barème")) {
            BigDecimal base = getBaseConge(element, variables, trxName);
            MHRBaremeConge bareme = PayrollRepository.getBaremeConge(
                    element.getHR_Element_Conge_ID(), base, trxName);
            if (bareme != null && bareme.getFormule() != null) {
                montant = FormulaEvaluator.evaluer(codeElement, bareme.getFormule(), variables);
            }
        }

        montant = montant != null ? montant.setScale(0, RoundingMode.FLOOR) : BigDecimal.ZERO;
        sauvegarderEtStockerConge(bpartnerId, holiday, codeElement, montant, variables, trxName);
    }

    // -------------------------------------------------------------------------
    // Net à Payer congé
    // -------------------------------------------------------------------------

    private static void calculerNetAPayer(int bpartnerId,
                                           MHRHoliday holiday,
                                           Map<String, BigDecimal> variables,
                                           String trxName) {
        BigDecimal ibc      = variables.getOrDefault(CODE_IBC,  BigDecimal.ZERO);
        BigDecimal irpp     = variables.getOrDefault("IRPP",    BigDecimal.ZERO);
        BigDecimal retenues = getSumRetenuesConge(bpartnerId, trxName);

        BigDecimal np = ibc.subtract(irpp).subtract(retenues);
        if (np.compareTo(BigDecimal.ZERO) < 0) np = BigDecimal.ZERO;
        np = np.setScale(0, RoundingMode.FLOOR);

        sauvegarderEtStockerConge(bpartnerId, holiday, CODE_NP, np, variables, trxName);
        log.info("NP congé = " + np + " FCFA pour bpartnerId=" + bpartnerId);
    }

    // -------------------------------------------------------------------------
    // Méthodes privées
    // -------------------------------------------------------------------------

    private static void sauvegarderEtStockerConge(int bpartnerId,
                                                    MHRHoliday holiday,
                                                    String codeElement,
                                                    BigDecimal montant,
                                                    Map<String, BigDecimal> variables,
                                                    String trxName) {
        variables.put(codeElement, montant != null ? montant : BigDecimal.ZERO);

        MHRElementConge element = getElementCongeByValue(codeElement, trxName);
        if (element == null) return; // variable interne, pas d'élément en base

        MHRCalculConge calculConge = PayrollRepository.getCalculCongeByValue(
                bpartnerId, codeElement, trxName);

        if (calculConge == null) {
            calculConge = new MHRCalculConge(Env.getCtx(), 0, trxName);
            calculConge.setHR_Calcul_Conge_ID(
                    DB.getNextID(Env.getCtx(), I_HR_Calcul_Conge.Table_Name, trxName));
            calculConge.setC_BPartner_ID(bpartnerId);
            calculConge.setHR_Element_Conge_ID(element.getHR_Element_Conge_ID());
        }
        calculConge.setHR_Holiday_ID(holiday.getHR_Holiday_ID());
        calculConge.setMontant(montant != null ? montant : BigDecimal.ZERO);
        calculConge.save();
    }

    /**
     * Charge le contrat de l'employé actif à une date donnée.
     * Utile pour lire le salaire de base à la date de départ en congé.
     */
    private static MHRElementBasePaieEmploye getContratActifALaDate(
            int bpartnerId, Timestamp date, String trxName) {
        String sql = "SELECT * FROM adempiere.hr_elementbasepaieemploye"
                + " WHERE c_bpartner_id=?"
                + " AND isactive='Y'"
                + " AND date_debut<=?"
                + " ORDER BY date_debut DESC LIMIT 1";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, date != null ? date : new Timestamp(System.currentTimeMillis()));
            rs = pstmt.executeQuery();
            if (rs.next()) return new MHRElementBasePaieEmploye(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.warning("getContratActifALaDate : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static BigDecimal getBaseConge(MHRElementConge element,
                                            Map<String, BigDecimal> variables,
                                            String trxName) {
        if (element.getBase_Calcul_ID() > 0) {
            MHRElementConge elementBase = new MHRElementConge(
                    Env.getCtx(), element.getBase_Calcul_ID(), trxName);
            if (elementBase != null) {
                BigDecimal base = variables.get(elementBase.getValue());
                return base != null ? base : BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    private static MHRElementConge getElementCongeByValue(String value, String trxName) {
        String sql = "SELECT * FROM adempiere.hr_element_conge WHERE value=? AND isactive='Y'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setString(1, value);
            rs = pstmt.executeQuery();
            if (rs.next()) return new MHRElementConge(Env.getCtx(), rs, trxName);
        } catch (SQLException e) {
            log.warning("getElementCongeByValue [" + value + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static String getTypeCalculNom(int typeCalculId, String trxName) {
        String sql = "SELECT name FROM adempiere.hr_type_calcul WHERE hr_type_calcul_id=?";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, typeCalculId);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getString("name");
        } catch (SQLException e) {
            log.warning("getTypeCalculNom : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    private static BigDecimal getSumRetenuesConge(int bpartnerId, String trxName) {
        String sql = "SELECT COALESCE(SUM(cc.montant), 0)"
                + " FROM adempiere.hr_calcul_conge cc"
                + " INNER JOIN adempiere.hr_element_conge ec"
                + " ON ec.hr_element_conge_id = cc.hr_element_conge_id"
                + " INNER JOIN adempiere.hr_typedecharge tc"
                + " ON tc.hr_typedecharge_id = ec.hr_typedecharge_id"
                + " WHERE cc.c_bpartner_id=?"
                + " AND tc.name='RETENUE CONGÉ'";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal val = rs.getBigDecimal(1);
                return val != null ? val : BigDecimal.ZERO;
            }
        } catch (SQLException e) {
            log.warning("getSumRetenuesConge : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return BigDecimal.ZERO;
    }
}
