package org.sitracel.discipline.process.sanction.service;

import java.sql.Timestamp;
import java.util.Calendar;

import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.discipline.model.X_HR_TypeSanction;
import org.sitracel.employe.HREmployeService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service — logique métier des processus de gestion des sanctions.
 *
 * Orchestre les actions de validation, rejet, approbation et
 * désapprobation des sanctions, ainsi que la création des absences
 * de suspension et des dossiers disciplinaires.
 *
 * Les notifications partent automatiquement via le modelvalidator
 * (ModelValidatorDisciplineController) lors de chaque save().
 * Ne pas ajouter d'appels sendEmail() ici.
 *
 * Remplace ProcessControllerDiscipline.
 * Couplage croisé CalloutSqlControllerAbsence → HRCongeRepository.
 */
public final class DisciplineProcessService {

    private DisciplineProcessService() {}

    // =========================================================================
    // VALIDATION
    // =========================================================================

    /**
     * Valide une sanction, crée les absences de suspension
     * et le dossier disciplinaire.
     */
    public static void validerSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant valideur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || valideur == null) return;
        if (valideur.getNomEmploye() == null) return;

        punishment.setValide_Rejete_Par_Nom_ID(valideur.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(valideur.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(valideur.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(true);
        punishment.setIsRejetee(false);
        punishment.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Rejet(null);
        punishment.save(null);
        // ✅ Notification SANCTION_VALIDATED via modelvalidator

        creerAbsencesSuspension(punishment, valideur);
        creerDossierDisciplinaire(punishment);
    }

    // =========================================================================
    // REJET
    // =========================================================================

    /**
     * Rejette une sanction et supprime les absences de suspension associées.
     */
    public static void rejeterSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant rejeteur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || rejeteur == null) return;
        if (rejeteur.getNomEmploye() == null) return;

        punishment.setValide_Rejete_Par_Nom_ID(rejeteur.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(rejeteur.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(rejeteur.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(false);
        punishment.setIsRejetee(true);
        punishment.setDate_Validation(null);
        punishment.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        punishment.save(null);
        // ✅ Notification SANCTION_REJECTED via modelvalidator

        supprimerAbsencesSuspension(punishment);
    }

    // =========================================================================
    // APPROBATION
    // =========================================================================

    /**
     * Approuve une sanction.
     */
    public static void approuverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant approbateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || approbateur == null) return;
        if (approbateur.getNomEmploye() == null) return;

        punishment.setApprouve_Desapprouve_Par_Nom_ID(approbateur.getNumEmploye());
        punishment.setApprouve_Desapprouve_Par_Matricule(approbateur.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Par_Poste_ID(approbateur.getNumeroPoste());
        punishment.setIsApprobation(true);
        punishment.setIsDesapprobation(false);
        punishment.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Desapprobation(null);
        punishment.save(null);
        // ✅ Notification SANCTION_APPROVED via modelvalidator
    }

    // =========================================================================
    // DÉSAPPROBATION
    // =========================================================================

    /**
     * Désapprouve une sanction.
     */
    public static void desapprouverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant desapprobateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || desapprobateur == null) return;
        if (desapprobateur.getNomEmploye() == null) return;

        punishment.setApprouve_Desapprouve_Par_Nom_ID(desapprobateur.getNumEmploye());
        punishment.setApprouve_Desapprouve_Par_Matricule(desapprobateur.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Par_Poste_ID(desapprobateur.getNumeroPoste());
        punishment.setIsApprobation(false);
        punishment.setIsDesapprobation(true);
        punishment.setDate_Approbation(null);
        punishment.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        punishment.save(null);
        // ✅ Notification SANCTION_DISAPPROVED via modelvalidator
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    /**
     * Crée une absence "Suspendu" pour chaque jour ouvrable de la suspension.
     * N'agit que si le type de sanction est une suspension.
     */
    private static void creerAbsencesSuspension(MHRPunishment punishment,
                                                  BeanIdentifiant valideur) {
        if (punishment == null || valideur == null) return;

        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), punishment.getEmission_Sanction_ID(), null);
        if (autorisation == null) return;

        MHRTypeSanction typeSanction = new MHRTypeSanction(
            Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null);
        if (typeSanction == null) return;

        // Agir uniquement pour les suspensions
        if (!X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension
                .equalsIgnoreCase(typeSanction.getIncidence_Sanction_ID())) return;

        MHRDureeSanction dureeSanction = new MHRDureeSanction(
            Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), null);
        int nombreJours = dureeSanction != null ? dureeSanction.getNombre_De_Jour() : 0;

        Timestamp debutAbs = punishment.getDate_Debut_Application();
        Timestamp finAbs   = HRCalendrierService.ajouterJoursOuvrables(debutAbs, nombreJours);

        // Mettre à jour la date de fin de la suspension
        if (nombreJours > 0) {
            punishment.setDate_Fin_Application(finAbs);
        } else {
            punishment.setDate_Fin_Application(null);
        }

        Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(
            I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
            I_HR_Type_Absence.Table_Name,
            I_HR_Type_Absence.COLUMNNAME_Nom_Absence,
            "Suspendu", null);

        if (typeAbsenceID == null || nombreJours <= 0) return;

        Timestamp courant = debutAbs;
        while (courant.before(finAbs)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(courant);

            boolean estDimanche = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            boolean estFerie    = MHRPublicHoliday.isJourFerie(courant, null);

            if (!estDimanche && !estFerie) {
                MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                absence.setC_BPartner_ID(punishment.getC_BPartner_ID());
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
    }

    /**
     * Supprime les absences de suspension créées lors de la validation.
     * Utilise AbsenceCalloutRepository — plus de couplage croisé callout→process.
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

        MHRDossierDisciplinaire dossier =
            new MHRDossierDisciplinaire(Env.getCtx(), 0, null);
        dossier.setC_BPartner_ID(punishment.getC_BPartner_ID());
        dossier.setHR_Punishment_ID(punishment.getHR_Punishment_ID());
        dossier.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        dossier.save(null);
    }
}
