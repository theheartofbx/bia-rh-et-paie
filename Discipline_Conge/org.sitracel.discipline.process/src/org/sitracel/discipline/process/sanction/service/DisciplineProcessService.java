package org.sitracel.discipline.process.sanction.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.absence.model.I_HR_Type_Absence;
import org.sitracel.absence.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.paie.process.service.PayrollOrchestrator;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.discipline.model.X_HR_TypeSanction;
import org.sitracel.employe.HREmployeService;
import org.sitracel.organigramme.ActionOrganigramme;
import org.sitracel.organigramme.ModuleAutorisation;
import org.sitracel.organigramme.OrganigrammeService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service — logique métier des processus de gestion des sanctions.
 *
 * Orchestre les actions de validation, rejet, approbation et
 * désapprobation des sanctions, ainsi que la création des absences
 * de suspension et des dossiers disciplinaires.
 *
 * Les notifications partent automatiquement via le modelvalidator
 * (DisciplineValidatorService) lors de chaque save().
 * Ne pas ajouter d'appels sendEmail() ici.
 *
 * Garde-fou d'habilitation ajouté sur les 4 actions (même principe que
 * CongeProcessService) : on vérifie via OrganigrammeService que l'acteur
 * a vraiment le droit d'agir, pas seulement que le bouton était visible
 * à l'écran (la colonne virtuelle IsApprobation ne garantit rien côté
 * serveur). Point d'attention : Emission_Sanction_ID pointe vers
 * HR_Sanction_Autorisation, pas directement vers HR_TypeSanction — il
 * faut donc résoudre HR_TypeSanction_ID via l'autorisation avant
 * d'appeler OrganigrammeService, qui l'attend directement.
 */
public final class DisciplineProcessService {

    private static final CLogger log = CLogger.getCLogger(DisciplineProcessService.class);

    private DisciplineProcessService() {}

    // =========================================================================
    // VALIDATION
    // =========================================================================

    /**
     * Valide une sanction, crée les absences de suspension
     * et le dossier disciplinaire.
     */
    public static String validerSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return "Parametres invalides.";

        BeanIdentifiant valideur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || valideur == null) return "Donnees introuvables.";
        if (valideur.getNomEmploye() == null) return "Identifiant valideur introuvable.";

        if (!estHabiliteAAgir(punishment, valideur.getNumEmploye(), ActionOrganigramme.VALIDATION)) {
            log.warning("Validation refusée : sanction " + idSanction
                + " par C_BPartner_ID " + valideur.getNumEmploye() + " (non habilité)");
            return "Vous n'etes pas habilite a valider cette sanction.";
        }

        punishment.setValide_Rejete_Par_Nom_ID(valideur.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(valideur.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(valideur.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(true);
        punishment.setIsRejetee(false);
        punishment.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Rejet(null);
        if (!punishment.save(null)) {
            log.warning("Validation impossible : erreur lors de la sauvegarde de la sanction " + idSanction);
            return "Validation impossible : la periode de suspension n'est plus disponible. Verifiez les absences et conges.";
        }
        // ✅ Notification SANCTION_VALIDATED via modelvalidator

        // Verifier que la date de debut n'est pas avant la date de validation
        if (punishment.getDate_Debut_Application() != null) {
            Timestamp dateValidation = punishment.getDate_Validation();
            Timestamp dateDebut = punishment.getDate_Debut_Application();
            // Comparer les dates sans l'heure
            Calendar calVal = Calendar.getInstance();
            calVal.setTime(dateValidation);
            calVal.set(Calendar.HOUR_OF_DAY, 0);
            calVal.set(Calendar.MINUTE, 0);
            calVal.set(Calendar.SECOND, 0);
            calVal.set(Calendar.MILLISECOND, 0);
            Calendar calDeb = Calendar.getInstance();
            calDeb.setTime(dateDebut);
            calDeb.set(Calendar.HOUR_OF_DAY, 0);
            calDeb.set(Calendar.MINUTE, 0);
            calDeb.set(Calendar.SECOND, 0);
            calDeb.set(Calendar.MILLISECOND, 0);
            if (calDeb.before(calVal)) {
                return "La date de debut d'application (" 
                    + new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateDebut)
                    + ") ne peut pas etre anterieure a la date de validation ("
                    + new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateValidation) + ").";
            }
        }

        String errSusp = creerAbsencesSuspension(punishment, valideur);
        if (errSusp != null) return errSusp;
        creerDossierDisciplinaire(punishment);

        // =================================================================
        // SUSPENSION DU CONTRAT
        // =================================================================
        if (punishment.getDate_Debut_Application() != null 
                && punishment.getDate_Fin_Application() != null) {
            traiterSuspensionContrat(punishment);
        }

        // =================================================================
        // LICENCIEMENT — fermer contrat, affectation, elements de paie
        //                et calculer l'indemnite legale
        // =================================================================
        if (punishment.isLicenciement()) {
            String errLic = traiterLicenciement(punishment, valideur);
            if (errLic != null) {
                log.warning("Licenciement partiel : " + errLic);
                // On ne bloque pas la validation, on log le warning
            }
        }

        return null;
    }

    // =========================================================================
    // REJET
    // =========================================================================

    /**
     * Rejette une sanction et supprime les absences de suspension associées.
     */
    public static String rejeterSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return "Parametres invalides.";

        BeanIdentifiant rejeteur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || rejeteur == null) return "Donnees introuvables.";
        if (rejeteur.getNomEmploye() == null) return "Identifiant rejeteur introuvable.";

        if (!estHabiliteAAgir(punishment, rejeteur.getNumEmploye(), ActionOrganigramme.VALIDATION)) {
            log.warning("Rejet refusé : sanction " + idSanction
                + " par C_BPartner_ID " + rejeteur.getNumEmploye() + " (non habilité)");
            return "Vous n'etes pas habilite a rejeter cette sanction.";
        }

        // --- Garde-fous de transition d'etat ---
        if (punishment.isValidee()) {
            return "Cette sanction a deja ete validee et ne peut plus etre rejetee.";
        }
        if (punishment.isRejetee()) {
            return "Cette sanction a deja ete rejetee.";
        }


        punishment.setValide_Rejete_Par_Nom_ID(rejeteur.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(rejeteur.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(rejeteur.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(false);
        punishment.setIsRejetee(true);
        punishment.setDate_Validation(null);
        punishment.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        if (!punishment.save(null)) {
            log.warning("Rejet impossible : erreur lors de la sauvegarde de la sanction " + idSanction);
            return "Rejet impossible : erreur lors de la sauvegarde.";
        }
        // ✅ Notification SANCTION_REJECTED via modelvalidator

        supprimerAbsencesSuspension(punishment);
        return null;
    }

    // =========================================================================
    // APPROBATION
    // =========================================================================

    /**
     * Approuve une sanction.
     */
    public static String approuverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return "Parametres invalides.";

        BeanIdentifiant approbateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || approbateur == null) return "Donnees introuvables.";
        if (approbateur.getNomEmploye() == null) return "Identifiant approbateur introuvable.";

        if (!estHabiliteAAgir(punishment, approbateur.getNumEmploye(), ActionOrganigramme.APPROBATION)) {
            log.warning("Approbation refusée : sanction " + idSanction
                + " par C_BPartner_ID " + approbateur.getNumEmploye() + " (non habilité)");
            return "Vous n'etes pas habilite a approuver cette sanction.";
        }

        // --- Garde-fous de transition d'etat ---
        if (punishment.isApprouve()) {
            return "Cette sanction a deja ete approuvee.";
        }
        if (punishment.isDesapprouve()) {
            return "Cette sanction a ete desapprouvee. Elle ne peut plus etre approuvee.";
        }
        if (punishment.isValidee()) {
            return "Cette sanction a deja ete validee et ne peut plus etre modifiee.";
        }
        if (punishment.isRejetee()) {
            return "Cette sanction a deja ete rejetee et ne peut plus etre modifiee.";
        }


        // --- Garde-fous de transition d'etat ---
        if (punishment.isApprouve()) {
            return "Cette sanction a deja ete approuvee.";
        }
        if (punishment.isDesapprouve()) {
            return "Cette sanction a ete desapprouvee. Elle ne peut plus etre approuvee.";
        }
        if (punishment.isValidee()) {
            return "Cette sanction a deja ete validee et ne peut plus etre modifiee.";
        }
        if (punishment.isRejetee()) {
            return "Cette sanction a deja ete rejetee et ne peut plus etre modifiee.";
        }


        punishment.setApprouve_Desapprouve_Nom_ID(approbateur.getNumEmploye());
        punishment.setApprouve_Desapprouve_Matricule(approbateur.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Poste_ID(approbateur.getNumeroPoste());
        punishment.setIsApprouve(true);
        punishment.setIsDesapprouve(false);
        punishment.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Desapprobation(null);
        if (!punishment.save(null)) {
            log.warning("Approbation impossible : erreur lors de la sauvegarde de la sanction " + idSanction);
            return "Approbation impossible : erreur lors de la sauvegarde.";
        }
        // ✅ Notification SANCTION_APPROVED via modelvalidator
        return null;
    }

    // =========================================================================
    // DÉSAPPROBATION
    // =========================================================================

    /**
     * Désapprouve une sanction.
     */
    public static String desapprouverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return "Parametres invalides.";

        BeanIdentifiant desapprobateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || desapprobateur == null) return "Donnees introuvables.";
        if (desapprobateur.getNomEmploye() == null) return "Identifiant desapprobateur introuvable.";

        if (!estHabiliteAAgir(punishment, desapprobateur.getNumEmploye(), ActionOrganigramme.APPROBATION)) {
            log.warning("Désapprobation refusée : sanction " + idSanction
                + " par C_BPartner_ID " + desapprobateur.getNumEmploye() + " (non habilité)");
            return "Vous n'etes pas habilite a desapprouver cette sanction.";
        }

        // --- Garde-fous de transition d'etat ---
        if (punishment.isDesapprouve()) {
            return "Cette sanction a deja ete desapprouvee.";
        }
        if (punishment.isValidee()) {
            return "Cette sanction a deja ete validee et ne peut plus etre modifiee.";
        }
        if (punishment.isRejetee()) {
            return "Cette sanction a deja ete rejetee et ne peut plus etre modifiee.";
        }


        // --- Garde-fous de transition d'etat ---
        if (punishment.isDesapprouve()) {
            return "Cette sanction a deja ete desapprouvee.";
        }
        if (punishment.isValidee()) {
            return "Cette sanction a deja ete validee et ne peut plus etre modifiee.";
        }
        if (punishment.isRejetee()) {
            return "Cette sanction a deja ete rejetee et ne peut plus etre modifiee.";
        }


        punishment.setApprouve_Desapprouve_Nom_ID(desapprobateur.getNumEmploye());
        punishment.setApprouve_Desapprouve_Matricule(desapprobateur.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Poste_ID(desapprobateur.getNumeroPoste());
        punishment.setIsApprouve(false);
        punishment.setIsDesapprouve(true);
        punishment.setDate_Approbation(null);
        punishment.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        if (!punishment.save(null)) {
            log.warning("Desapprobation impossible : erreur lors de la sauvegarde de la sanction " + idSanction);
            return "Desapprobation impossible : erreur lors de la sauvegarde.";
        }
        // ✅ Notification SANCTION_DISAPPROVED via modelvalidator
        return null;
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /**
     * Vérifie que l'acteur donné a vraiment le droit d'effectuer l'action
     * sur cette sanction, via l'organigramme.
     *
     * Emission_Sanction_ID pointe vers HR_Sanction_Autorisation (pas
     * directement vers HR_TypeSanction) — on résout donc HR_TypeSanction_ID
     * en passant par cette table avant d'interroger OrganigrammeService,
     * qui attend directement un HR_TypeSanction_ID.
     */
    private static boolean estHabiliteAAgir(MHRPunishment punishment, int bpartnerActeur, ActionOrganigramme action) {
        if (punishment.getC_BPartner_ID() <= 0 || punishment.getEmission_Sanction_ID() <= 0 || bpartnerActeur <= 0) {
            return false;
        }

        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
        if (autorisation == null || autorisation.getHR_TypeSanction_ID() <= 0) {
            return false;
        }

        List<Integer> acteurs = OrganigrammeService.getActeurs(
            punishment.getC_BPartner_ID(), autorisation.getHR_TypeSanction_ID(),
            ModuleAutorisation.SANCTION, action, punishment.get_TrxName());

        return acteurs.contains(bpartnerActeur);
    }

    /**
     * Crée une absence "Suspendu" pour chaque jour ouvrable de la suspension.
     * N'agit que si le type de sanction est une suspension.
     */
    private static String creerAbsencesSuspension(MHRPunishment punishment,
                                                  BeanIdentifiant valideur) {
        if (punishment == null || valideur == null) return null;

        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
        if (autorisation == null) return null;

        MHRTypeSanction typeSanction = new MHRTypeSanction(
            Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
        if (typeSanction == null) return null;

        // Agir uniquement pour les suspensions
        if (!X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension
                .equalsIgnoreCase(typeSanction.getIncidence_Sanction_ID())) return null;

        MHRDureeSanction dureeSanction = new MHRDureeSanction(
            Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), null);
        int nombreJours = dureeSanction != null ? dureeSanction.getNombre_De_Jour() : 0;

        Timestamp debutAbs = punishment.getDate_Debut_Application();
        Timestamp finAbs   = HRCalendrierService.ajouterJoursOuvrables(debutAbs, nombreJours);

        // Sauvegarder la date de fin AVANT de creer les absences
        if (nombreJours > 0) {
            punishment.setDate_Fin_Application(finAbs);
            punishment.save(null);
        }

        Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(
            I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
            I_HR_Type_Absence.Table_Name,
            I_HR_Type_Absence.COLUMNNAME_Nom_Absence,
            "Suspendu", null);

        if (typeAbsenceID == null || nombreJours <= 0) return null;

        // Recuperer le matricule de l'employe concerne
        BeanIdentifiant employe = HREmployeService.getIdentifiantByBPartner(
            punishment.getC_BPartner_ID(), null);
        String matriculeEmploye = (employe != null)
            ? employe.getMatriculeEmploye() : "";

        // Verifier que les dates sont toujours disponibles
        java.util.List<Timestamp> absencesExistantes =
            HRCongeRepository.getAbsencesExistantesDansPeriode(
                punishment.getC_BPartner_ID(), debutAbs, finAbs, null);
        if (!absencesExistantes.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Validation impossible : l'employe a deja des absences ");
            sb.append("enregistrees aux dates suivantes : ");
            java.text.SimpleDateFormat sdf =
                new java.text.SimpleDateFormat("dd/MM/yyyy");
            for (int i = 0; i < absencesExistantes.size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(sdf.format(absencesExistantes.get(i)));
            }
            sb.append(". Veuillez les supprimer ou les traiter avant de valider.");
            return sb.toString();
        }

        // Poser le drapeau systeme pour bypasser les controles
        // "jour de conge" et "jour de suspension" dans le
        // ModelValidator Absence (AbsenceValidatorService).
        Env.getCtx().setProperty("#IS_CREATION_ABSENCE_SYSTEME", "Y");
        try {
            Timestamp courant = debutAbs;
            while (courant.before(finAbs)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(courant);

                boolean estDimanche = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
                boolean estFerie    = MHRPublicHoliday.isJourFerie(courant, null);

                if (!estDimanche && !estFerie) {
                    MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                    absence.setC_BPartner_ID(punishment.getC_BPartner_ID());
                    absence.setMatricule_Employe(matriculeEmploye);
                    absence.setPoste_Employe_ID(employe.getNumeroPoste());
                    absence.setEmis_Par_Nom_ID(valideur.getNumEmploye());
                    absence.setEmis_Par_Poste_ID(valideur.getNumeroPoste());
                    absence.setEmis_Par_Matricule(valideur.getMatriculeEmploye());
                    absence.setDate_Absence(courant);
                    absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
                    absence.setHR_Type_Absence_ID(typeAbsenceID);
                    absence.setIsDemandeExplication(false);
                    absence.setIsConge(false);
                    absence.setIsDemandeExplicationTraite(true);
                    absence.setIsCongeTraite(true);
                    absence.save(null);
                }

                courant = HRCalendrierService.ajouterJoursOuvrables(courant, 1);
            }
        } finally {
            Env.getCtx().remove("#IS_CREATION_ABSENCE_SYSTEME");
        }
        return null;
    }

    /**
     * Supprime les absences de suspension créées lors de la validation.
     */
    private static void supprimerAbsencesSuspension(MHRPunishment punishment) {
        if (punishment == null) return;
        if (punishment.getDate_Debut_Application() == null
                || punishment.getDate_Fin_Application() == null) return;

        HRCongeRepository.annulerAbsenceConge(
            punishment.getC_BPartner_ID(),
            punishment.getDate_Debut_Application(),
            punishment.getDate_Fin_Application(),
            null);
    }

    /**
     * Crée un dossier disciplinaire lié à la sanction validée.
     */
    private static void creerDossierDisciplinaire(MHRPunishment punishment) {
        if (punishment == null) return;

        // Resoudre le type de sanction via l'autorisation
        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
        int typeSanctionId = (autorisation != null) ? autorisation.getHR_TypeSanction_ID() : 0;

        MHRDossierDisciplinaire dossier =
            new MHRDossierDisciplinaire(Env.getCtx(), 0, null);
        dossier.setC_BPartner_ID(punishment.getC_BPartner_ID());
        dossier.setHR_Punishment_ID(punishment.getHR_Punishment_ID());
        dossier.setDate_Emission(punishment.getDate_Emission());
        dossier.setDate_Validation(punishment.getDate_Validation());
        dossier.setValide_Rejete_Par_Nom_ID(punishment.getValide_Rejete_Par_Nom_ID());
        dossier.setValide_Rejete_Par_Poste_ID(punishment.getValide_Rejete_Par_Poste_ID());
        dossier.setValide_Rejete_Par_Matricule(punishment.getValide_Rejete_Par_Matricule());
        dossier.setPoste_Employe_ID(punishment.getPoste_Employe_ID());
        dossier.setMatricule_Employe(punishment.getMatricule_Employe());
        dossier.setMotif(punishment.getMotif_Demande_Explication());
        if (typeSanctionId > 0) {
            dossier.setHR_TypeSanction_ID(typeSanctionId);
        }
        dossier.save(null);
    }


    // =========================================================================
    // SUSPENSION DU CONTRAT
    // =========================================================================

    /**
     * Passe le contrat actif en statut "Suspendu" (202) uniquement si
     * la date de debut d'application est aujourd'hui ou deja passee.
     * Si la date de debut est dans le futur, le Scheduler s'en chargera.
     */
    private static void traiterSuspensionContrat(MHRPunishment punishment) {
        Timestamp dateDebut = punishment.getDate_Debut_Application();
        if (dateDebut == null) return;

        // Comparer date_debut avec aujourd'hui (sans l'heure)
        Calendar calDebut = Calendar.getInstance();
        calDebut.setTime(dateDebut);
        calDebut.set(Calendar.HOUR_OF_DAY, 0);
        calDebut.set(Calendar.MINUTE, 0);
        calDebut.set(Calendar.SECOND, 0);
        calDebut.set(Calendar.MILLISECOND, 0);

        Calendar calAujourdhui = Calendar.getInstance();
        calAujourdhui.set(Calendar.HOUR_OF_DAY, 0);
        calAujourdhui.set(Calendar.MINUTE, 0);
        calAujourdhui.set(Calendar.SECOND, 0);
        calAujourdhui.set(Calendar.MILLISECOND, 0);

        if (calDebut.after(calAujourdhui)) {
            log.info("Suspension contrat differee : debut le "
                + new java.text.SimpleDateFormat("dd/MM/yyyy").format(dateDebut)
                + " pour bpartnerId=" + punishment.getC_BPartner_ID());
            return;
        }

        int bpartnerId = punishment.getC_BPartner_ID();
        String sql = "UPDATE adempiere.hr_contrat"
            + " SET hr_contratstatut_id = 202,"
            + "     updated = statement_timestamp(),"
            + "     updatedby = " + Env.getAD_User_ID(Env.getCtx())
            + " WHERE c_bpartner_id = " + bpartnerId
            + "   AND hr_contratstatut_id = 101"
            + "   AND isactive = 'Y'";
        int nb = DB.executeUpdate(sql, false, null);
        if (nb > 0) {
            log.info("Suspension contrat : statut passe a Suspendu pour bpartnerId=" + bpartnerId);
        }
    }

    // =========================================================================
    // LICENCIEMENT
    // =========================================================================

    /**
     * Traite les consequences d'un licenciement valide :
     * 1. Ferme le contrat actif (statut Rompu, date_rupture, motif)
     * 2. Ferme l'affectation active (date_fin)
     * 3. Ferme les elements de paie (date_fin)
     * 4. Calcule et enregistre l'indemnite de licenciement
     */
    private static String traiterLicenciement(MHRPunishment punishment, BeanIdentifiant valideur) {
        int bpartnerId = punishment.getC_BPartner_ID();
        Timestamp dateLicenciement = new Timestamp(System.currentTimeMillis());
        String motif = punishment.getMotif_Demande_Explication();
        StringBuilder warnings = new StringBuilder();

        // 1. Fermer le contrat actif
        String sqlContrat = "UPDATE adempiere.hr_contrat"
            + " SET hr_contratstatut_id = 303,"
            + "     date_rupture = ?,"
            + "     motif_rupture = ?,"
            + "     updated = statement_timestamp(),"
            + "     updatedby = ?"
            + " WHERE c_bpartner_id = ?"
            + "   AND hr_contratstatut_id = 101"
            + "   AND isactive = 'Y'";
        int nbContrat = DB.executeUpdate(sqlContrat, new Object[]{
            dateLicenciement, motif, Env.getAD_User_ID(Env.getCtx()), bpartnerId
        }, false, null);
        if (nbContrat == 0) warnings.append("Aucun contrat actif trouve. ");
        else log.info("Licenciement : " + nbContrat + " contrat(s) ferme(s) pour bpartnerId=" + bpartnerId);

        // 2. Fermer l'affectation active
        String sqlAffect = "UPDATE adempiere.hr_affectation"
            + " SET date_fin = ?,"
            + "     updated = statement_timestamp(),"
            + "     updatedby = ?"
            + " WHERE c_bpartner_id = ?"
            + "   AND date_fin IS NULL"
            + "   AND isactive = 'Y'";
        int nbAffect = DB.executeUpdate(sqlAffect, new Object[]{
            dateLicenciement, Env.getAD_User_ID(Env.getCtx()), bpartnerId
        }, false, null);
        if (nbAffect == 0) warnings.append("Aucune affectation active trouvee. ");
        else log.info("Licenciement : " + nbAffect + " affectation(s) fermee(s) pour bpartnerId=" + bpartnerId);

        // 3. Fermer les elements de paie
        String sqlElem = "UPDATE adempiere.hr_elementbasepaieemploye"
            + " SET date_fin = ?,"
            + "     updated = statement_timestamp(),"
            + "     updatedby = ?"
            + " WHERE c_bpartner_id = ?"
            + "   AND date_fin IS NULL"
            + "   AND isactive = 'Y'";
        int nbElem = DB.executeUpdate(sqlElem, new Object[]{
            dateLicenciement, Env.getAD_User_ID(Env.getCtx()), bpartnerId
        }, false, null);
        log.info("Licenciement : " + nbElem + " element(s) de paie ferme(s) pour bpartnerId=" + bpartnerId);

        // 4. Calculer et enregistrer l'indemnite de licenciement
        try {
            int periodeId = DB.getSQLValue(null,
                "SELECT HR_Periode_Salariale_ID FROM HR_Periode_Salariale"
                + " WHERE Date_Debut_Defaut <= now() AND Date_Fin_Defaut >= now()"
                + " AND IsActive='Y'");
            if (periodeId <= 0) {
                warnings.append("Periode salariale courante introuvable - indemnite non calculee. ");
            } else {
                String result = PayrollOrchestrator.calculerIndemniteLicenciement(
                    bpartnerId, periodeId);
                log.info("Licenciement indemnite : " + result);
            }
        } catch (Exception e) {
            log.severe("Erreur calcul indemnite licenciement : " + e.getMessage());
            warnings.append("Erreur calcul indemnite : " + e.getMessage());
        }

        return warnings.length() > 0 ? warnings.toString() : null;
    }

}
