package org.sitracel.conge.process.conge.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.absence.model.I_HR_Type_Absence;
import org.sitracel.absence.model.MHRAbsence;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.employe.HREmployeService;
import org.sitracel.organigramme.ActionOrganigramme;
import org.sitracel.organigramme.ModuleAutorisation;
import org.sitracel.organigramme.OrganigrammeService;
import org.sitracel.time.HRCalendrierService;

/**
 * Service — logique métier des processus de gestion des congés.
 *
 * Orchestre les actions de validation, rejet, approbation et
 * désapprobation des congés, ainsi que la gestion des absences
 * associées.
 *
 * Les notifications partent automatiquement via le modelvalidator
 * (SitracelCongeGeneralModelValidator) lors de chaque save().
 * Ne pas ajouter d'appels sendEmail() ici.
 *
 * Depuis Session 9 : chaque action vérifie réellement, via
 * OrganigrammeService, que la personne qui agit en a l'autorité.
 *
 * CORRECTION (Session 9, suite) : approuverConge()/desapprouverConge()
 * écrivaient sur IsApprobation — une colonne virtuelle (calcul de
 * droit "ai-je le droit d'approuver"), jamais enregistrable. Le vrai
 * champ d'état persistant est IsApprouve. Cette confusion de noms très
 * proches faisait que l'état "approuvé" n'était probablement jamais
 * réellement sauvegardé.
 *
 * Remplace ProcessControllerConge.
 */
public final class CongeProcessService {

    private static final CLogger log = CLogger.getCLogger(CongeProcessService.class);

    private CongeProcessService() {}

    // =========================================================================
    // VALIDATION
    // =========================================================================

    public static void validerConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant valideur = HREmployeService.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || valideur == null) return;
        if (valideur.getNomEmploye() == null) return;

        if (!estHabiliteAAgir(conge, valideur.getNumEmploye(), ActionOrganigramme.VALIDATION)) {
            log.warning("Validation refusée : congé " + idConge
                + " par C_BPartner_ID " + valideur.getNumEmploye() + " (non habilité)");
            return;
        }

        MHRTypeConge typeConge = chargerTypeConge(conge);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null);

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            verifierDroitsConge(conge, detteConge);
        }

        conge.setValide_Rejete_Par_Nom_ID(valideur.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(valideur.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(valideur.getNumeroPoste());
        conge.setIsTraitee(true);
        conge.setIsValidee(true);
        conge.setIsRejetee(false);
        conge.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Rejet(null);
        conge.save(null);
        // ✅ Notification HOLIDAY_VALIDATED via modelvalidator

        creerAbsencesConge(conge, valideur);
    }

    // =========================================================================
    // REJET
    // =========================================================================

    public static void rejeterConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant rejeteur = HREmployeService.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || rejeteur == null) return;
        if (rejeteur.getNomEmploye() == null) return;

        if (!estHabiliteAAgir(conge, rejeteur.getNumEmploye(), ActionOrganigramme.VALIDATION)) {
            log.warning("Rejet refusé : congé " + idConge
                + " par C_BPartner_ID " + rejeteur.getNumEmploye() + " (non habilité)");
            return;
        }

        MHRTypeConge typeConge = chargerTypeConge(conge);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null);

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            verifierDroitsConge(conge, detteConge);
        }

        conge.setValide_Rejete_Par_Nom_ID(rejeteur.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(rejeteur.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(rejeteur.getNumeroPoste());
        conge.setIsTraitee(true);
        conge.setIsValidee(false);
        conge.setIsRejetee(true);
        conge.setDate_Validation(null);
        conge.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        conge.save(null);
        // ✅ Notification HOLIDAY_REJECTED via modelvalidator

        supprimerAbsencesConge(conge);
    }

    // =========================================================================
    // APPROBATION
    // =========================================================================

    /**
     * Approuve un congé.
     *
     * CORRECTION : setIsApprouve() (le vrai champ d'état), pas
     * setIsApprobation() (colonne virtuelle de droit, jamais persistée).
     */
    public static void approuverConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant approbateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || approbateur == null) return;
        if (approbateur.getNomEmploye() == null) return;

        if (!estHabiliteAAgir(conge, approbateur.getNumEmploye(), ActionOrganigramme.APPROBATION)) {
            log.warning("Approbation refusée : congé " + idConge
                + " par C_BPartner_ID " + approbateur.getNumEmploye() + " (non habilité)");
            return;
        }

        conge.setApprouve_Desapprouve_Nom_ID(approbateur.getNumEmploye());
        conge.setApprouve_Desapprouve_Matricule(approbateur.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Poste_ID(approbateur.getNumeroPoste());
        conge.setIsApprouve(true);
        conge.setIsDesapprouve(false);
        conge.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Desapprobation(null);
        conge.save(null);
        // ✅ Notification HOLIDAY_APPROVED via modelvalidator
    }

    // =========================================================================
    // DÉSAPPROBATION
    // =========================================================================

    /**
     * Désapprouve un congé.
     *
     * CORRECTION : setIsApprouve(false), même correction que ci-dessus.
     */
    public static void desapprouverConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant desapprobateur = HREmployeService.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || desapprobateur == null) return;
        if (desapprobateur.getNomEmploye() == null) return;

        if (!estHabiliteAAgir(conge, desapprobateur.getNumEmploye(), ActionOrganigramme.APPROBATION)) {
            log.warning("Désapprobation refusée : congé " + idConge
                + " par C_BPartner_ID " + desapprobateur.getNumEmploye() + " (non habilité)");
            return;
        }

        conge.setApprouve_Desapprouve_Nom_ID(desapprobateur.getNumEmploye());
        conge.setApprouve_Desapprouve_Matricule(desapprobateur.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Poste_ID(desapprobateur.getNumeroPoste());
        conge.setIsApprouve(false);
        conge.setIsDesapprouve(true);
        conge.setDate_Approbation(null);
        conge.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        conge.save(null);
        // ✅ Notification HOLIDAY_DISAPPROVED via modelvalidator
    }

    // =========================================================================
    // MISE À JOUR ABSENCES
    // =========================================================================

    public static void updateAbsenceConge(Integer idConge) {
        if (idConge == null) return;

        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
        if (conge == null) return;

        if (!conge.isValidee() && !conge.isRejetee()
                && conge.getDate_Debut_Effective() != null
                && conge.getDate_Debut_Effective().after(
                    new Timestamp(System.currentTimeMillis()))) {

            conge.setJours_Conge_A_Compenser(
                GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
                    conge.getDate_Debut_Souhaitee(), null));
            conge.save();
        }
    }

    // =========================================================================
    // MÉTHODES PRIVÉES
    // =========================================================================

    private static boolean estHabiliteAAgir(MHRHoliday conge, int bpartnerActeur, ActionOrganigramme action) {
        if (conge.getC_BPartner_ID() <= 0 || conge.getEmission_Conge_ID() <= 0 || bpartnerActeur <= 0) {
            return false;
        }

        List<Integer> acteurs = OrganigrammeService.getActeurs(
            conge.getC_BPartner_ID(), conge.getEmission_Conge_ID(),
            ModuleAutorisation.CONGE, action, conge.get_TrxName());

        return acteurs.contains(bpartnerActeur);
    }

    private static MHRTypeConge chargerTypeConge(MHRHoliday conge) {
        if (conge.getEmission_Conge_ID() <= 0) return null;
        return new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
    }

    private static void verifierDroitsConge(MHRHoliday conge, int detteConge) {
        int solde = conge.getJours_Conge_Total()
            - conge.getJours_Conge_Correspondant()
            - conge.getJours_Conge_Deja_Utilise()
            - detteConge;

        if (solde < 0) {
            conge.setMessage_Alerte(
                "Attention la période de congé dépasse les droits de l'employé");
            conge.setIsMessageAlerteDisplayed(true);
        } else {
            conge.setJours_Conge_A_Compenser(detteConge);
        }
        conge.save();
    }

    private static void creerAbsencesConge(MHRHoliday conge, BeanIdentifiant valideur) {
        if (conge.getDate_Debut_Effective() == null
                || conge.getDate_Fin_Effective() == null) return;

        Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(
            I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
            I_HR_Type_Absence.Table_Name,
            I_HR_Type_Absence.COLUMNNAME_Nom_Absence,
            "En Congé", null);

        if (typeAbsenceID == null) return;

        Timestamp dateDebut = conge.getDate_Debut_Effective();
        Timestamp dateFin   = conge.getDate_Fin_Effective();

        while (dateDebut.before(dateFin)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebut);

            boolean estDimanche = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
            boolean estFerie    = MHRPublicHoliday.isJourFerie(dateDebut, null);

            if (!estDimanche && !estFerie) {
                MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                absence.setC_BPartner_ID(conge.getC_BPartner_ID());
                absence.setEmis_Par_Nom_ID(valideur.getNumEmploye());
                absence.setEmis_Par_Poste_ID(valideur.getNumeroPoste());
                absence.setEmis_Par_Matricule(valideur.getMatriculeEmploye());
                absence.setDate_Absence(dateDebut);
                absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
                absence.setHR_Type_Absence_ID(typeAbsenceID);
                absence.setIsDemandeExplication(false);
                absence.setIsConge(false);
                absence.setIsDemandeExplicationTraite(true);
                absence.setIsCongeTraite(true);
                absence.save(null);
            }

            dateDebut = HRCalendrierService.ajouterJoursOuvrables(dateDebut, 1);
        }
    }

    private static void supprimerAbsencesConge(MHRHoliday conge) {
        if (conge.getDate_Debut_Effective() == null
                || conge.getDate_Fin_Effective() == null) return;

        HRCongeRepository.annulerAbsenceConge(
            conge.getC_BPartner_ID(),
            conge.getDate_Debut_Effective(),
            conge.getDate_Fin_Effective(),
            null);
    }
}
