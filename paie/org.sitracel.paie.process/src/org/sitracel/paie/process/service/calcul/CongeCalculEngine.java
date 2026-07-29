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
import org.sitracel.bean.BeanConge;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.X_C_BPartner;
import org.sitracel.paie.model.I_HR_Calcul_Conge;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRCalculConge;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementConge;
import org.sitracel.paie.model.X_HR_DetailIndemniteBrutConge;
import org.sitracel.paie.process.service.PayrollRepository;
import org.sitracel.paie.process.service.persistence.PayrollPersistence;
import org.sitracel.paie.process.service.calcul.fiscal.BaremeFiscalCameroun;

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
     * Le salaire_cotisable est calcule dynamiquement depuis
     * hr_historique_paie (cumul SBR sur la periode de reference).
     * Prerequis : hr_historique_paie alimente sur la periode de reference.
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
        // ÉTAPE 1 — Calculer la base depuis hr_historique_paie
        // ------------------------------------------------------------------
        // Periode de reference : de date_dernier_conge (ou date embauche)
        // jusqu a date_debut_effective du conge
        Timestamp dateDebutRef = holiday.getDate_Dernier_Conge();
        if (dateDebutRef == null) {
            // Premier conge : utiliser la date d embauche
            MHRElementBasePaieEmploye contratRef = getContratActifALaDate(
                    bpartnerId, holiday.getDate_Debut_Effective(), trxName);
            if (contratRef != null) {
                dateDebutRef = contratRef.getDate_Debut();
            }
        }
        Timestamp dateFinRef = holiday.getDate_Debut_Effective();

        BigDecimal salaireCotisable = BigDecimal.ZERO;
        if (dateDebutRef != null && dateFinRef != null) {
            // Cumul des SBR sur la periode de reference depuis hr_historique_paie
            String sqlSC = "SELECT COALESCE(SUM(hp.Montant), 0)"
                    + " FROM HR_Historique_Paie hp"
                    + " JOIN HR_Periode_Salariale ps ON ps.HR_Periode_Salariale_ID = hp.HR_Periode_Salariale_ID"
                    + " JOIN HR_Element_Base_Paie e ON e.HR_Element_Base_Paie_ID = hp.HR_Element_Base_Paie_ID"
                    + " JOIN HR_GestionPaieEmploye g ON g.Value = e.Value AND g.IsActive = 'Y'"
                    + " WHERE hp.C_BPartner_ID=?"
                    + " AND g.IsIndemniteConge = 'Y'"
                    + " AND ps.Date_Debut_Defaut >= ?"
                    + " AND ps.Date_Debut_Defaut < ?";
            salaireCotisable = DB.getSQLValueBD(trxName, sqlSC, bpartnerId, dateDebutRef, dateFinRef);
            if (salaireCotisable == null) salaireCotisable = BigDecimal.ZERO;

            // Mettre a jour hr_holiday pour affichage
            holiday.setSalaire_Cotisable(salaireCotisable);
            holiday.save();
        }

        // Jours_Conge_Correspondant = jours ouvrables du conge (hors dimanches)
        int njc = holiday.getJours_Conge_Correspondant();
        BigDecimal nombreJourConge = BigDecimal.valueOf(njc);

        if (salaireCotisable.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("calculerIndemniteConge : salaire_cotisable=0 "
                    + "— aucun SBR trouve dans hr_historique_paie entre "
                    + dateDebutRef + " et " + dateFinRef);
            return false;
        }

        if (nombreJourConge == null || nombreJourConge.compareTo(BigDecimal.ZERO) <= 0) {
            log.warning("calculerIndemniteConge : jours_conge_correspondant absent ou nul");
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
        BeanConge donneesConge = MHREmployeeChildren.getEnfantMoins6(
                bpartnerId,
                holiday.getDate_Debut_Effective(),
                BeanFactory.getBeanConge(),
                trxName);

        MHRElementBasePaieEmploye contrat = getContratActifALaDate(
                bpartnerId,
                holiday.getDate_Debut_Effective(),
                trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 4 — Initialiser les variables
        // ------------------------------------------------------------------
        Map<String, BigDecimal> variables = new HashMap<>();

        // Base periode de reference — cumul SBR calcule dynamiquement
        // depuis hr_historique_paie (mis a jour dans hr_holiday a l etape 1)
        variables.put(CODE_SC,   salaireCotisable);
        variables.put(CODE_SCPR, salaireCotisable); // alias pour compatibilité formules

        // Nombre de jours de congé à indemniser
        variables.put(CODE_NJC, nombreJourConge);

        // Nombre de jours de congé de BASE (sans bonus anciennete ni enfants)
        // Les bonus sont ajoutes separement via CANC et CEMS
        // pour eviter le double comptage (Jours_Conge_Total les inclut deja)
        int njciBase = org.sitracel.conge.HRCongeService.getNombreJourCongeAnnuelBase();
        BigDecimal njci = BigDecimal.valueOf(njciBase);
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
        // Utilise annee_anciennete du conge (posee par le callout)
        BigDecimal canc = BigDecimal.ZERO;
        int anneeAnc = holiday.getAnnee_Anciennete();
        if (anneeAnc > 0) {
            canc = BigDecimal.valueOf((anneeAnc / 3) * 2);
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

        // Traitement special IRPP : utiliser BaremeFiscalCameroun
        // car les formules en base utilisent des ternaires (? :)
        // que exp4j ne supporte pas
        if ("IRPP".equals(codeElement)) {
            BigDecimal ibc = variables.getOrDefault(CODE_IBC, BigDecimal.ZERO);
            if (ibc.compareTo(BigDecimal.ZERO) > 0) {
                montant = BaremeFiscalCameroun.calculer(ibc);
                montant = montant != null ? montant.setScale(0, RoundingMode.FLOOR) : BigDecimal.ZERO;
            }
            sauvegarderEtStockerConge(bpartnerId, holiday, codeElement, montant, variables, trxName);
            return;
        }

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


    // -------------------------------------------------------------------------
    // Detail IBC — alimentation HR_DetailIndemniteBrutConge (mode test)
    // -------------------------------------------------------------------------

    /**
     * Alimente HR_DetailIndemniteBrutConge avec le detail mois par mois
     * des elements participant au calcul de l indemnite de conge.
     *
     * Utilise le flag IsIndemniteConge de HR_GestionPaieEmploye pour
     * determiner quels elements prendre en compte (meme logique que
     * le cumul SC). Une ligne par element par periode.
     *
     * Ne modifie PAS le calcul — table de detail pour verification.
     * Pour debrancher : commenter l appel dans PayrollOrchestrator.
     */
    public static void alimenterDetailIBC(int bpartnerId,
                                           MHRHoliday holiday,
                                           String trxName) {
        if (holiday == null) return;

        Timestamp dateDebutRef = holiday.getDate_Dernier_Conge();
        if (dateDebutRef == null) {
            MHRElementBasePaieEmploye contratRef = getContratActifALaDate(
                    bpartnerId, holiday.getDate_Debut_Effective(), trxName);
            if (contratRef != null) {
                dateDebutRef = contratRef.getDate_Debut();
            }
        }
        Timestamp dateFinRef = holiday.getDate_Debut_Effective();

        if (dateDebutRef == null || dateFinRef == null) {
            log.warning("alimenterDetailIBC : dates de reference nulles, abandon");
            return;
        }

        int holidayId = holiday.getHR_Holiday_ID();

        // 1. Purger les anciennes lignes de detail pour ce conge
        DB.executeUpdate(
            "DELETE FROM HR_DetailIndemniteBrutConge"
            + " WHERE C_BPartner_ID=" + bpartnerId
            + " AND HR_Holiday_ID=" + holidayId,
            false, trxName);

        // 2. Lire chaque element IsIndemniteConge mois par mois
        String sql = "SELECT hp.HR_Periode_Salariale_ID,"
                + " hp.HR_Element_Base_Paie_ID,"
                + " hp.Montant,"
                + " ps.Name AS nom_periode,"
                + " e.Value AS code_element"
                + " FROM HR_Historique_Paie hp"
                + " JOIN HR_Periode_Salariale ps"
                + "   ON ps.HR_Periode_Salariale_ID = hp.HR_Periode_Salariale_ID"
                + " JOIN HR_Element_Base_Paie e"
                + "   ON e.HR_Element_Base_Paie_ID = hp.HR_Element_Base_Paie_ID"
                + " JOIN HR_GestionPaieEmploye g"
                + "   ON g.Value = e.Value AND g.IsActive = 'Y'"
                + " WHERE hp.C_BPartner_ID=?"
                + " AND g.IsIndemniteConge = 'Y'"
                + " AND ps.Date_Debut_Defaut >= ?"
                + " AND ps.Date_Debut_Defaut < ?"
                + " ORDER BY ps.Date_Debut_Defaut, e.Value";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int count = 0;
        BigDecimal totalVerif = BigDecimal.ZERO;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateDebutRef);
            pstmt.setTimestamp(3, dateFinRef);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                int periodeId    = rs.getInt(1);
                int elementId    = rs.getInt(2);
                BigDecimal mont  = rs.getBigDecimal(3);
                String nomPeriode  = rs.getString(4);
                String codeElement = rs.getString(5);

                if (mont == null) mont = BigDecimal.ZERO;
                totalVerif = totalVerif.add(mont);

                X_HR_DetailIndemniteBrutConge detail =
                    new X_HR_DetailIndemniteBrutConge(
                        Env.getCtx(), 0, trxName);
                detail.setC_BPartner_ID(bpartnerId);
                detail.setHR_Holiday_ID(holidayId);
                detail.setHR_Periode_Salariale_ID(periodeId);
                detail.setHR_Element_Base_Paie_ID(elementId);
                detail.setMontant(mont);
                detail.save();
                count++;

                log.info("DetailIBC : periode=" + nomPeriode
                        + " element=" + codeElement
                        + " montant=" + mont.toPlainString());
            }
        } catch (SQLException e) {
            log.severe("alimenterDetailIBC : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        log.info("alimenterDetailIBC termine : " + count
                + " lignes, total=" + totalVerif.toPlainString()
                + " vs SC=" + holiday.getSalaire_Cotisable()
                + " pour conge ID=" + holidayId);
    }

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
                + " ORDER BY date_debut DESC";
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
                + " AND tc.name='RETENUE CONGÉ'"
                + " AND ec.value <> 'IRPP'";
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
