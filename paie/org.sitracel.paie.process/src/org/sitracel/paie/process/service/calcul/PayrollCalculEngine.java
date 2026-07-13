package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_GestionPaieEmploye;
import org.sitracel.paie.model.MHRBareme;
import org.sitracel.paie.model.MHRBaremeConge;
import org.sitracel.paie.model.MHRElementBasePaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;
import org.sitracel.paie.model.MHRGestionPresence;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.process.service.PayrollRepository;
import org.sitracel.paie.process.service.persistence.PayrollPersistence;

/**
 * Moteur de calcul de la paie mensuelle.
 *
 * Séquence pour chaque employé :
 *   1. Vérifier le contrat actif
 *   2. Charger le coefficient de présence
 *   3. Initialiser les variables depuis le contrat
 *   4. Calculer les éléments dans l'ordre des rangs
 *   5. Appliquer les retenues et indemnités (RetenueEngine)
 *   6. Calculer et sauvegarder le Net à Payer
 *
 * Extensibilité :
 *   - Ajouter un élément : insérer dans HR_ElementBasePaie + HR_GestionPaieEmploye
 *   - Modifier un calcul : changer la formule en base
 *   - Activer/désactiver un élément : isactive dans HR_GestionPaieEmploye
 *   - Proratiser un élément : isproportionneltravail dans HR_GestionPaieEmploye
 *   Zéro modification Java dans tous ces cas.
 */
public class PayrollCalculEngine {

    private static final CLogger log = CLogger.getCLogger(PayrollCalculEngine.class);

    private static final String CODE_NP  = "NP";
    private static final String CODE_SBR = "SBR";

    // -------------------------------------------------------------------------
    // API publique
    // -------------------------------------------------------------------------

    /**
     * Calcule la paie complète d'un employé pour une période.
     *
     * @param bpartnerId  ID de l'employé (C_BPartner_ID)
     * @param periodeId   ID de la période salariale
     * @param trxName     transaction active (peut être null)
     * @return            true si le calcul s'est bien déroulé
     */
    public static boolean calculerPaie(int bpartnerId, int periodeId, String trxName) {
        log.info("Début calcul paie — bpartnerId=" + bpartnerId + " periodeId=" + periodeId);

        // ------------------------------------------------------------------
        // ÉTAPE 1 — Vérifier le contrat actif
        // ------------------------------------------------------------------
        MHRElementBasePaieEmploye contrat = getContratActif(bpartnerId, trxName);
        if (contrat == null) {
            log.warning("Calcul paie ignoré : aucun contrat actif pour bpartnerId=" + bpartnerId);
            return false;
        }

        MHRPeriodeSalariale periode = new MHRPeriodeSalariale(Env.getCtx(), periodeId, trxName);
        if (periode == null || periode.getHR_Periode_Salariale_ID() == 0) {
            log.warning("Calcul paie ignoré : période introuvable periodeId=" + periodeId);
            return false;
        }

        // ------------------------------------------------------------------
        // ÉTAPE 2 — Charger le coefficient de présence
        // ------------------------------------------------------------------
        BigDecimal coeffPresence = getCoeffPresence(bpartnerId, periodeId, trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 3 — Initialiser les variables depuis le contrat
        // ------------------------------------------------------------------
        Map<String, BigDecimal> variables = new HashMap<>();
        initialiserVariablesContrat(contrat, variables);

        // ------------------------------------------------------------------
        // ÉTAPE 4 — Réinitialiser les anciens calculs puis recalculer
        // ------------------------------------------------------------------
        PayrollPersistence.resetCalculPaie(bpartnerId, trxName);

        List<MHRElementBasePaie> elements = PayrollRepository.getElementBasePaieInitialValues(trxName);
        if (elements == null || elements.isEmpty()) {
            log.warning("Calcul paie : aucun élément de paie défini.");
            return false;
        }

        for (MHRElementBasePaie element : elements) {
            calculerElement(
                bpartnerId, periode, contrat, element,
                coeffPresence, variables, trxName
            );
        }

        // ------------------------------------------------------------------
        // ÉTAPE 5 — Appliquer les retenues et indemnités
        // ------------------------------------------------------------------
        RetenueEngine.ResultatRetenues retenues =
                RetenueEngine.traiter(bpartnerId, periodeId, trxName);

        // ------------------------------------------------------------------
        // ÉTAPE 6 — Calculer le Net à Payer
        // ------------------------------------------------------------------
        calculerNetAPayer(
            bpartnerId, periode, variables,
            retenues, trxName
        );

        log.info("Fin calcul paie — bpartnerId=" + bpartnerId);
        return true;
    }

    // -------------------------------------------------------------------------
    // Calcul d'un élément
    // -------------------------------------------------------------------------

    /**
     * Calcule un élément de paie et le sauvegarde.
     *
     * Logique selon le type de calcul (HR_Type_Calcul) :
     *   - Formule       → FormulaEvaluator (exp4j ou calculateur natif)
     *   - Pourcentage   → base × pourcentage
     *   - Barème        → montant fixe de la tranche
     *   - Formule-Barème→ formule dans la tranche du barème
     *   - Aucun         → valeur issue du contrat employé (déjà dans variables)
     *
     * Si isproportionneltravail=Y → résultat × coeffPresence
     */
    private static void calculerElement(
            int bpartnerId,
            MHRPeriodeSalariale periode,
            MHRElementBasePaieEmploye contrat,
            MHRElementBasePaie element,
            BigDecimal coeffPresence,
            Map<String, BigDecimal> variables,
            String trxName) {

        String codeElement = element.getValue();
        if (codeElement == null) return;

        // Lire la configuration de cet élément dans HR_GestionPaieEmploye
        // (optionnel : seuls les éléments contrat y figurent, pas les calculés)
        MHRGestionPaieEmploye config = getConfigElement(codeElement, trxName);
        if (config != null && !config.isActive()) {
            // Élément explicitement désactivé → on saute
            return;
        }

        BigDecimal montant = BigDecimal.ZERO;

        // Déterminer le type de calcul
        String typeCalcul = getTypeCalculNom(element.getHR_Type_Calcul_ID(), trxName);

        if (typeCalcul == null || typeCalcul.equalsIgnoreCase("Aucun")) {
            // Valeur déjà dans variables (issue du contrat)
            montant = variables.getOrDefault(codeElement, BigDecimal.ZERO);

        } else if (typeCalcul.equalsIgnoreCase("Formule")) {
            montant = FormulaEvaluator.evaluer(codeElement, element.getFormule(), variables);

        } else if (typeCalcul.equalsIgnoreCase("Pourcentage")) {
            BigDecimal base = getBaseCalcul(element, variables, trxName);
            montant = base.multiply(element.getPourcentage());

        } else if (typeCalcul.equalsIgnoreCase("Barème")) {
            BigDecimal base = getBaseCalcul(element, variables, trxName);
            MHRBareme bareme = PayrollRepository.getBareme(
                    element.getHR_Element_Base_Paie_ID(), base, trxName);
            if (bareme != null) {
                montant = bareme.getMontant();
            }

        } else if (typeCalcul.equalsIgnoreCase("Formule-Barème")) {
            BigDecimal base = getBaseCalcul(element, variables, trxName);
            MHRBareme bareme = PayrollRepository.getBareme(
                    element.getHR_Element_Base_Paie_ID(), base, trxName);
            if (bareme != null && bareme.getFormule() != null) {
                montant = FormulaEvaluator.evaluer(codeElement, bareme.getFormule(), variables);
            }
        }

        // Prorata de présence si applicable
        if (config != null && config.isProportionnelTravail()
                && coeffPresence.compareTo(BigDecimal.ONE) < 0
                && montant.compareTo(BigDecimal.ZERO) > 0) {
            montant = montant.multiply(coeffPresence).setScale(0, RoundingMode.FLOOR);
        }

        // Stocker dans la map pour les éléments suivants
        variables.put(codeElement, montant);

        // Sauvegarder en base
        PayrollPersistence.sauvegarderCalculPaie(
                bpartnerId, element, periode, montant, trxName);

        // Sauvegarder dans l'historique
        PayrollPersistence.sauvegarderHistorique(
                bpartnerId, element, periode, montant, trxName);
    }

    // -------------------------------------------------------------------------
    // Net à Payer
    // -------------------------------------------------------------------------

    private static void calculerNetAPayer(
            int bpartnerId,
            MHRPeriodeSalariale periode,
            Map<String, BigDecimal> variables,
            RetenueEngine.ResultatRetenues retenues,
            String trxName) {

        // Charges salariales déjà calculées et en base
        BigDecimal chargesSalariales =
                PayrollRepository.getSumChargesSalariales(bpartnerId, trxName);

        BigDecimal sbr = variables.getOrDefault(CODE_SBR, BigDecimal.ZERO);

        // NP = SBR - charges salariales - retenues + indemnités versées
        BigDecimal np = sbr
                .subtract(chargesSalariales)
                .subtract(retenues.totalRetenues)
                .add(retenues.totalIndemnites);

        // NP ne peut pas être négatif
        if (np.compareTo(BigDecimal.ZERO) < 0) {
            np = BigDecimal.ZERO;
        }

        np = np.setScale(0, RoundingMode.FLOOR);
        variables.put(CODE_NP, np);

        // Charger la définition de l'élément NP pour la sauvegarde
        MHRElementBasePaie elementNP =
                PayrollRepository.getElementBasePaieByValue(CODE_NP, trxName);
        if (elementNP != null) {
            PayrollPersistence.sauvegarderCalculPaie(
                    bpartnerId, elementNP, periode, np, trxName);
            PayrollPersistence.sauvegarderHistorique(
                    bpartnerId, elementNP, periode, np, trxName);
        }

        log.info("NP calculé = " + np + " FCFA pour bpartnerId=" + bpartnerId);
    }

    // -------------------------------------------------------------------------
    // Méthodes privées — données
    // -------------------------------------------------------------------------

    /**
     * Charge le contrat actif de l'employé.
     * C'est l'enregistrement HR_ElementBasePaieEmploye avec IsActive=Y.
     */
    private static MHRElementBasePaieEmploye getContratActif(int bpartnerId, String trxName) {
        String sql = "SELECT * FROM " + I_HR_ElementBasePaieEmploye.Table_Name
                + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_IsActive + "='Y'"
                + " ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " DESC"
                ;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRElementBasePaieEmploye(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.severe("getContratActif : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Calcule le coefficient de présence pour un employé et une période.
     * coefficient = nombre_jour_effectif / nombre_jour_max
     * Si pas de fiche présence → 1.0 (mois complet payé)
     */
    private static BigDecimal getCoeffPresence(int bpartnerId, int periodeId, String trxName) {
        String sql = "SELECT nombre_jour_effectif, nombre_jour_max"
                + " FROM adempiere.hr_gestion_presence"
                + " WHERE c_bpartner_id=?"
                + " AND hr_periode_salariale_id=?"
                + " AND isactive='Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setInt(2, periodeId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BigDecimal joursEffectifs = rs.getBigDecimal("nombre_jour_effectif");
                BigDecimal joursMax       = rs.getBigDecimal("nombre_jour_max");
                if (joursMax != null && joursMax.compareTo(BigDecimal.ZERO) > 0
                        && joursEffectifs != null) {
                    BigDecimal coeff = joursEffectifs.divide(joursMax, 6, RoundingMode.HALF_UP);
                    // Plafonner à 1 (on ne peut pas travailler plus que le max)
                    if (coeff.compareTo(BigDecimal.ONE) > 0) coeff = BigDecimal.ONE;
                    return coeff;
                }
            }
        } catch (SQLException e) {
            log.warning("getCoeffPresence : " + e.getMessage() + " → coefficient 1.0 utilisé");
        } finally {
            DB.close(rs, pstmt);
        }
        // Pas de fiche présence → mois complet
        return BigDecimal.ONE;
    }

    /**
     * Initialise la map des variables avec les valeurs du contrat employé.
     * SB, IL, IT, etc. sont lus depuis HR_ElementBasePaieEmploye.
     */
    private static void initialiserVariablesContrat(
            MHRElementBasePaieEmploye contrat,
            Map<String, BigDecimal> variables) {

        // Valeurs issues du contrat — les colonnes clés
        putSafe(variables, "SB",   contrat.getSalaire_Base());
        putSafe(variables, "IL",   contrat.getIndemnite_Logement());
        putSafe(variables, "IT",   contrat.getIndemnite_Transport());
        putSafe(variables, "IREP", contrat.getIndemnite_Representation());
        putSafe(variables, "IPN",  contrat.getIndemnite_Panier_Nuit());
        putSafe(variables, "PO",   contrat.getPrime_Outillage());
        putSafe(variables, "PCAI", contrat.getPrime_Caisse());
        putSafe(variables, "DO",   contrat.getDomesticite());
        putSafe(variables, "CSB",  contrat.getComplement_Salaire());
        putSafe(variables, "ILC",  contrat.getIndemnite_Logement_Complement());
        putSafe(variables, "ITC",  contrat.getIndemnite_Transport_Complement());
        putSafe(variables, "RIMP", contrat.getRappel_Imposable());
        putSafe(variables, "RNI",  contrat.getRappel_Non_Imposable());
        putSafe(variables, "PREN", contrat.getPrime_Rendement());
        putSafe(variables, "PRES", contrat.getPrime_Responsabilite());
        putSafe(variables, "PRI",  contrat.getPrime_Risque());

        // Initialiser les éléments calculés à zéro
        variables.put("SBR",    BigDecimal.ZERO);
        variables.put("BCNPS",  BigDecimal.ZERO);
        variables.put("BIMP",   BigDecimal.ZERO);
        variables.put("BAIRPP", BigDecimal.ZERO);
        variables.put("BMIRPP", BigDecimal.ZERO);
        variables.put("IRPP",   BigDecimal.ZERO);
        variables.put("NP",     BigDecimal.ZERO);
        variables.put("IC",     BigDecimal.ZERO);
    }

    /**
     * Charge la configuration d'un élément depuis HR_GestionPaieEmploye.
     */
    private static MHRGestionPaieEmploye getConfigElement(String codeElement, String trxName) {
        String sql = "SELECT * FROM adempiere.hr_gestionpaieemploye"
                + " WHERE value=?"
                + " AND isactive='Y'"
                + " AND ad_client_id=?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setString(1, codeElement);
            pstmt.setInt(2, Env.getAD_Client_ID(Env.getCtx()));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new MHRGestionPaieEmploye(Env.getCtx(), rs, trxName);
            }
        } catch (SQLException e) {
            log.warning("getConfigElement [" + codeElement + "] : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }

    /**
     * Retourne le montant de base pour un calcul de type Pourcentage ou Barème.
     * La base est l'élément référencé par Base_Calcul_ID.
     */
    private static BigDecimal getBaseCalcul(
            MHRElementBasePaie element,
            Map<String, BigDecimal> variables,
            String trxName) {

        if (element.getBase_Calcul_ID() > 0) {
            MHRElementBasePaie elementBase = new MHRElementBasePaie(
                    Env.getCtx(), element.getBase_Calcul_ID(), trxName);
            if (elementBase != null) {
                BigDecimal base = variables.get(elementBase.getValue());
                return base != null ? base : BigDecimal.ZERO;
            }
        }
        return BigDecimal.ZERO;
    }

    /**
     * Retourne le nom du type de calcul depuis son ID.
     */
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

    private static void putSafe(Map<String, BigDecimal> variables,
                                  String key, BigDecimal value) {
        variables.put(key, value != null ? value : BigDecimal.ZERO);
    }
}
