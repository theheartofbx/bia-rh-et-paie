package org.sitracel.paie.process.service.calcul;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDate;
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
        // ÉTAPE 1 — Charger la période et vérifier le contrat actif
        // ------------------------------------------------------------------
        MHRPeriodeSalariale periode = new MHRPeriodeSalariale(Env.getCtx(), periodeId, trxName);
        if (periode == null || periode.getHR_Periode_Salariale_ID() == 0) {
            log.warning("Calcul paie ignoré : période introuvable periodeId=" + periodeId);
            return false;
        }

        // Utiliser le milieu de la période comme date de référence
        // pour éviter les problèmes de bord (début = 16, fin = 15 du mois suivant)
        Timestamp dateReference = periode.getDate_Debut_Defaut();

        MHRElementBasePaieEmploye contrat = getContratActif(bpartnerId, dateReference, trxName);
        if (contrat == null) {
            log.warning("Calcul paie ignoré : aucun élément de paie valide pour "
                    + "bpartnerId=" + bpartnerId
                    + " à la date " + dateReference
                    + " (période : " + periode.getName() + ")");
            return false;
        }

        // ------------------------------------------------------------------
        // ÉTAPE 2 — Generer la fiche de presence puis charger le coefficient
        // ------------------------------------------------------------------
        genererGestionPresence(bpartnerId, periodeId, periode, contrat, trxName);
        BigDecimal coeffPresence = getCoeffPresence(bpartnerId, periodeId, trxName);

        // ÉTAPE 2b supprimee — les jours avant contrat sont maintenant
        // geres directement dans genererGestionPresence() ci-dessus.

        // ------------------------------------------------------------------
        // ÉTAPE 3 — Initialiser les variables depuis le contrat
        // ------------------------------------------------------------------
        Map<String, BigDecimal> variables = new HashMap<>();
        initialiserVariablesContrat(contrat, variables);

        // ------------------------------------------------------------------
        // ÉTAPE 4 — Réinitialiser les anciens calculs puis recalculer
        // ------------------------------------------------------------------
        PayrollPersistence.resetCalculPaie(bpartnerId, periodeId, trxName);

        List<MHRElementBasePaie> elements = PayrollRepository.getElementBasePaieInitialValues(trxName);
        if (elements == null || elements.isEmpty()) {
            log.warning("Calcul paie : aucun élément de paie défini.");
            return false;
        }

        // Sauvegarder les elements contrat non nuls (SB, CSB, IL, IT...)
        sauvegarderElementsContrat(bpartnerId, periode, coeffPresence, variables, trxName);

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
    /**
     * Sauvegarde les elements issus du contrat (SB, CSB, IL, IT, etc.)
     * dans hr_calcul_paie pour que le rapport les affiche.
     * L'ordre suit HR_GestionPaieEmploye_ID croissant.
     * Seuls les elements non nuls sont sauvegardes.
     */
    private static void sauvegarderElementsContrat(
            int bpartnerId,
            MHRPeriodeSalariale periode,
            BigDecimal coeffPresence,
            Map<String, BigDecimal> variables,
            String trxName) {

        List<MHRGestionPaieEmploye> configs =
            PayrollRepository.getAllGestionPaieEmploye(trxName);

        if (configs == null) return;

        for (MHRGestionPaieEmploye cfg : configs) {
            String code = cfg.getValue();
            if (code == null) continue;

            BigDecimal montant = variables.get(code);
            if (montant != null && montant.compareTo(BigDecimal.ZERO) > 0) {

                // Appliquer le prorata de presence si applicable
                if (cfg.isProportionnelTravail()
                        && coeffPresence.compareTo(BigDecimal.ONE) < 0) {
                    montant = montant.multiply(coeffPresence).setScale(0, RoundingMode.FLOOR);
                    variables.put(code, montant);
                }

                MHRElementBasePaie element =
                    PayrollRepository.getElementBasePaieByValue(code, trxName);
                if (element != null) {
                    PayrollPersistence.sauvegarderCalculPaie(
                        bpartnerId, element, periode, montant, trxName);
                    // Historiciser les elements contrat pour le calcul des indemnites de conge
                    PayrollPersistence.sauvegarderHistorique(
                        bpartnerId, element, periode, montant, trxName);
                }
            }
        }
    }

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
        try {

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
        } catch (Exception e) {
            log.warning("Erreur calcul element [" + codeElement + "] : " + e.getMessage());
            variables.put(codeElement, BigDecimal.ZERO);
        }
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

        // Charges salariales déjà calculées et en base — filtrées par période
        BigDecimal chargesSalariales =
                PayrollRepository.getSumChargesSalariales(
                        bpartnerId, periode.getHR_Periode_Salariale_ID(), trxName);

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

    /**
     * Ajuste automatiquement la fiche de présence si l'employé a été embauché
     * en cours de période (Date_Debut du contrat > Date_Debut de la période).
     *
     * Règle :
     *   joursAvantEmbauche = nombre de jours entre début période et début contrat
     *   Nombre_Jour_Effectif -= joursAvantEmbauche
     *   Nombre_Jour_Avant_DebutContrat = joursAvantEmbauche
     *
     * Ne fait rien si :
     *   - La période IsAvantDebutContratDeduit = N
     *   - Le contrat commence avant ou au début de la période (cas normal)
     *   - La fiche de présence n'existe pas (elle sera créée par le callout)
     */
    private static void ajusterPresencePourEmbauche(
            int bpartnerId, int periodeId,
            MHRElementBasePaieEmploye contrat,
            MHRPeriodeSalariale periode,
            String trxName) {

        // Vérifier que la période demande cette déduction
        if (!periode.isAvantDebutContratDeduit()) return;

        Timestamp dateDebutContrat = contrat.getDate_Debut();
        Timestamp dateDebutPeriode = periode.getDate_Debut_Defaut();
        Timestamp dateFinPeriode   = periode.getDate_Fin_Defaut();

        if (dateDebutContrat == null || dateDebutPeriode == null) return;

        // Le contrat commence avant ou exactement au début de la période → rien à faire
        if (!dateDebutContrat.after(dateDebutPeriode)) return;

        // Le contrat commence après la fin de la période → cas impossible (getContratActif aurait renvoyé null)
        if (dateDebutContrat.after(dateFinPeriode)) return;

        // Calculer les jours avant embauche (du début période jusqu'à la veille du contrat)
        LocalDate ldDebut  = dateDebutPeriode.toLocalDateTime().toLocalDate();
        LocalDate ldContrat = dateDebutContrat.toLocalDateTime().toLocalDate();
        int joursAvant = (int) java.time.temporal.ChronoUnit.DAYS.between(ldDebut, ldContrat);

        if (joursAvant <= 0) return;

        // Mettre à jour la fiche de présence si elle existe
        // DB.executeUpdate en Java 8 iDempiere ne supporte pas Object[]
        // On passe par un PreparedStatement direct
        String sql = "UPDATE adempiere.hr_gestion_presence"
                + " SET nombre_jour_avant_debutcontrat = " + joursAvant + ","
                + "     nombre_jour_effectif = GREATEST(0, nombre_jour_effectif - " + joursAvant + "),"
                + "     updated = NOW()"
                + " WHERE c_bpartner_id = " + bpartnerId
                + " AND hr_periode_salariale_id = " + periodeId
                + " AND isactive = 'Y'";

        int updated = DB.executeUpdate(sql, trxName);

        if (updated > 0) {
            log.info("Présence ajustée pour embauche milieu période — bpartnerId=" + bpartnerId
                    + " joursAvant=" + joursAvant
                    + " période=" + periode.getName());
        } else {
            // Pas de fiche de présence encore — noter dans le log, le callout la créera
            log.fine("Pas de fiche présence pour bpartnerId=" + bpartnerId
                    + " période=" + periode.getName()
                    + " — joursAvantEmbauche=" + joursAvant + " sera appliqué par le callout");
        }
    }

    /**
     * Charge l'élément de paie de l'employé valide pour la période donnée.
     * Un élément est valide si :
     *   - IsActive = 'Y'
     *   - Date_Debut <= dateReference
     *   - Date_Fin IS NULL (CDI) OU Date_Fin >= dateReference
     *
     * Si dateReference est null, retourne le plus récent (comportement legacy).
     */
    private static MHRElementBasePaieEmploye getContratActif(
            int bpartnerId, Timestamp dateReference, String trxName) {

        // dateReference est OBLIGATOIRE — sans elle on ne peut pas determiner
        // quel element de paie couvre la periode. Refuser proprement.
        if (dateReference == null) {
            log.severe("getContratActif : dateReference est null — "
                + "la periode salariale n'a pas de Date_Debut_Defaut. "
                + "Calcul impossible pour bpartnerId=" + bpartnerId);
            return null;
        }

        String sql = "SELECT * FROM " + I_HR_ElementBasePaieEmploye.Table_Name
                + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=?"
                + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_IsActive + "='Y'"
                + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + "<=?"
                + " AND (" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin + " IS NULL"
                + "   OR " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin + ">=?)"
                + " ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " DESC";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateReference);
            pstmt.setTimestamp(3, dateReference);
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
     * Genere ou met a jour la fiche de presence pour un employe et une periode.
     * Sources :
     *   - HR_Absence : jours d absence categorises par type
     *     Type 404 (En Conge) → via hr_holiday → hr_type_conge :
     *       101 = annuel, 202 = maternite, 303 = paternite
     *     Type 505 (Suspendu) → suspension disciplinaire
     *     Types 101, 202, 303 (Injustifiee, Justifiee, Maladie) → autres absences
     *   - Contrat : jours avant debut du contrat si embauche en milieu de periode
     *
     * Cette methode est idempotente : on peut la relancer sans risque.
     */
    private static void genererGestionPresence(int bpartnerId, int periodeId,
            MHRPeriodeSalariale periode, MHRElementBasePaieEmploye contrat, String trxName) {

        Timestamp dateDebutPeriode = periode.getDate_Debut_Defaut();
        Timestamp dateFinPeriode   = periode.getDate_Fin_Defaut();
        int nombreJourMax = 30;

        // --- Conges : toutes les absences de type "En Congé" ---
        // Le type d absence suffit comme reference, pas de jointure HR_Holiday
        // (hr_holiday_id n est pas toujours renseigne sur les absences)
        String sqlCongeAnnuel = "SELECT COUNT(*) FROM HR_Absence"
                + " WHERE C_BPartner_ID=?"
                + " AND date_absence >= ? AND date_absence <= ?"
                + " AND IsActive='Y'"
                + " AND HR_Type_Absence_ID = (SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence='En Congé' AND IsActive='Y')";
        int joursCongeAnnuel = DB.getSQLValue(trxName, sqlCongeAnnuel,
                bpartnerId, dateDebutPeriode, dateFinPeriode);
        if (joursCongeAnnuel < 0) joursCongeAnnuel = 0;

        // Maternite et paternite inclus dans le total conges ci-dessus
        int joursCongeMaternite = 0;
        int joursCongePaternite = 0;

        // --- Suspensions disciplinaires (type 505) ---
        String sqlSuspension = "SELECT COUNT(*) FROM HR_Absence"
                + " WHERE C_BPartner_ID=?"
                + " AND date_absence >= ? AND date_absence <= ?"
                + " AND IsActive='Y'"
                + " AND HR_Type_Absence_ID = (SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence='Suspendu' AND IsActive='Y')";
        int joursSuspension = DB.getSQLValue(trxName, sqlSuspension,
                bpartnerId, dateDebutPeriode, dateFinPeriode);
        if (joursSuspension < 0) joursSuspension = 0;

        // --- Autres absences (injustifiee 101, justifiee 202, maladie 303) ---
        String sqlAutres = "SELECT COUNT(*) FROM HR_Absence"
                + " WHERE C_BPartner_ID=?"
                + " AND date_absence >= ? AND date_absence <= ?"
                + " AND IsActive='Y'"
                + " AND HR_Type_Absence_ID IN (SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence IN ('Absence Injustifiée','Absence Justifiée','Absence Maladie') AND IsActive='Y')";
        int joursAutres = DB.getSQLValue(trxName, sqlAutres,
                bpartnerId, dateDebutPeriode, dateFinPeriode);
        if (joursAutres < 0) joursAutres = 0;

        // --- Jours avant debut du contrat ---
        int joursAvantContrat = 0;
        if (contrat != null && contrat.getDate_Debut() != null) {
            Timestamp dateDebutContrat = contrat.getDate_Debut();
            if (dateDebutContrat.after(dateDebutPeriode)) {
                LocalDate ldDebut   = dateDebutPeriode.toLocalDateTime().toLocalDate();
                LocalDate ldContrat = dateDebutContrat.toLocalDateTime().toLocalDate();
                joursAvantContrat = (int) java.time.temporal.ChronoUnit.DAYS.between(ldDebut, ldContrat);
                if (joursAvantContrat < 0) joursAvantContrat = 0;
            }
        }

        // --- Jours apres fin du contrat (licenciement, rupture, retraite) ---
        int joursApresContrat = 0;
        if (contrat != null && contrat.getDate_Fin() != null) {
            Timestamp dateFinContrat = contrat.getDate_Fin();
            if (dateFinContrat.before(dateFinPeriode)) {
                LocalDate ldFinContrat = dateFinContrat.toLocalDateTime().toLocalDate();
                LocalDate ldFinPeriode = dateFinPeriode.toLocalDateTime().toLocalDate();
                joursApresContrat = (int) java.time.temporal.ChronoUnit.DAYS.between(ldFinContrat, ldFinPeriode);
                if (joursApresContrat < 0) joursApresContrat = 0;
            }
        }

        // --- Calculer le nombre de jours effectifs ---
        // Seuls les conges et suspensions reduisent les jours effectifs
        // Les autres absences (injustifiee, justifiee, maladie) sont informatives
        int totalAbsences = joursCongeAnnuel + joursCongeMaternite + joursCongePaternite
                + joursSuspension + joursAvantContrat + joursApresContrat;
        int joursEffectifs = nombreJourMax - totalAbsences;
        if (joursEffectifs < 0) joursEffectifs = 0;

        // --- Creer ou mettre a jour la fiche de presence ---
        String sqlFind = "SELECT HR_Gestion_Presence_ID FROM HR_Gestion_Presence"
                + " WHERE C_BPartner_ID=? AND HR_Periode_Salariale_ID=? AND IsActive='Y'";
        int existingId = DB.getSQLValue(trxName, sqlFind, bpartnerId, periodeId);

        if (existingId > 0) {
            String sqlUpdate = "UPDATE HR_Gestion_Presence SET"
                    + " Nombre_Jour_Max=" + nombreJourMax
                    + ", Nombre_Jour_Effectif=" + joursEffectifs
                    + ", Nombre_Jour_Avant_DebutContrat=" + joursAvantContrat
                    + ", Nombre_Jour_Conge_Annuel=" + joursCongeAnnuel
                    + ", Nombre_Jour_Conge_Maternite=" + joursCongeMaternite
                    + ", Nombre_Jour_Conge_Paternite=" + joursCongePaternite
                    + ", Nombre_Jour_Suspension=" + joursSuspension
                    + ", Nombre_Jour_Apres_FinContrat=" + joursApresContrat
                    + ", Date_Debut='" + dateDebutPeriode + "'"
                    + ", Date_Fin='" + dateFinPeriode + "'"
                    + ", Updated=NOW()"
                    + " WHERE HR_Gestion_Presence_ID=" + existingId;
            DB.executeUpdate(sqlUpdate, trxName);
        } else {
            int newId = DB.getNextID(Env.getCtx(), "HR_Gestion_Presence", trxName);
            String sqlInsert = "INSERT INTO HR_Gestion_Presence"
                    + " (HR_Gestion_Presence_ID, AD_Client_ID, AD_Org_ID, IsActive,"
                    + "  Created, CreatedBy, Updated, UpdatedBy,"
                    + "  C_BPartner_ID, HR_Periode_Salariale_ID,"
                    + "  Date_Debut, Date_Fin,"
                    + "  Nombre_Jour_Max, Nombre_Jour_Effectif,"
                    + "  Nombre_Jour_Avant_DebutContrat,"
                    + "  Nombre_Jour_Conge_Annuel, Nombre_Jour_Conge_Maternite,"
                    + "  Nombre_Jour_Conge_Paternite, Nombre_Jour_Suspension,"
                    + "  Nombre_Jour_Apres_FinContrat)"
                    + " VALUES (" + newId + ", " + Env.getAD_Client_ID(Env.getCtx())
                    + ", " + Env.getAD_Org_ID(Env.getCtx()) + ", 'Y'"
                    + ", NOW(), " + Env.getAD_User_ID(Env.getCtx())
                    + ", NOW(), " + Env.getAD_User_ID(Env.getCtx())
                    + ", " + bpartnerId + ", " + periodeId
                    + ", '" + dateDebutPeriode + "', '" + dateFinPeriode + "'"
                    + ", " + nombreJourMax + ", " + joursEffectifs
                    + ", " + joursAvantContrat
                    + ", " + joursCongeAnnuel + ", " + joursCongeMaternite
                    + ", " + joursCongePaternite + ", " + joursSuspension
                    + ", " + joursApresContrat + ")";
            DB.executeUpdate(sqlInsert, trxName);
        }

        log.info("Presence generee — bpartnerId=" + bpartnerId
                + " periode=" + periode.getName()
                + " max=" + nombreJourMax
                + " congeAnnuel=" + joursCongeAnnuel
                + " maternite=" + joursCongeMaternite
                + " paternite=" + joursCongePaternite
                + " suspension=" + joursSuspension
                + " autres=" + joursAutres
                + " avantContrat=" + joursAvantContrat
                + " apresContrat=" + joursApresContrat
                + " effectif=" + joursEffectifs);
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

        // Initialiser toutes les variables config a zero
        // pour eviter les erreurs exp4j sur variables inconnues
        java.util.List<MHRGestionPaieEmploye> allConfig =
            PayrollRepository.getAllGestionPaieEmploye(null);
        if (allConfig != null) {
            for (MHRGestionPaieEmploye cfg : allConfig) {
                if (cfg.getValue() != null) {
                    variables.put(cfg.getValue(), BigDecimal.ZERO);
                }
            }
        }

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
        putSafe(variables, "IDE",  contrat.getIndemnite_Deces());

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
