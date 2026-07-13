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
import org.sitracel.conge.model.CongeStatut;
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
 * Service - logique metier des processus de gestion des conges.
 *
 * Depuis Session 9 (v3) : l'etat du conge est port par une seule
 * colonne, HR_CongeStatut_ID (remplace les 5 anciens booleens
 * IsApprouve/IsDesapprouve/IsValidee/IsRejetee/IsTraitee).
 *
 * Nouvelle regle metier imposee (jamais garantie auparavant) :
 * la validation/le rejet ne sont possibles que si le conge est deja
 * au statut APPROUVE - workflow sequentiel, pas deux pistes
 * independantes comme avant.
 *
 * Remplace ProcessControllerConge.
 */
public final class CongeProcessService {

    private static final CLogger log = CLogger.getCLogger(CongeProcessService.class);

    private CongeProcessService() {}

    // =========================================================================
    // APPROBATION
    // =========================================================================

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

        if (estStatutFinal(conge)) {
            log.warning("Approbation refusée : congé " + idConge
                + " déjà dans un statut final (Validé/Rejeté)");
            return;
        }

        conge.setApprouve_Desapprouve_Nom_ID(approbateur.getNumEmploye());
        conge.setApprouve_Desapprouve_Matricule(approbateur.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Poste_ID(approbateur.getNumeroPoste());
        conge.setHR_CongeStatut_ID(CongeStatut.APPROUVE);
        conge.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Desapprobation(null);
        conge.save(null);
        // ✅ Notification HOLIDAY_APPROVED via modelvalidator
    }

    // =========================================================================
    // DÉSAPPROBATION
    // =========================================================================

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

        if (estStatutFinal(conge)) {
            log.warning("Désapprobation refusée : congé " + idConge
                + " déjà dans un statut final (Validé/Rejeté)");
            return;
        }

        conge.setApprouve_Desapprouve_Nom_ID(desapprobateur.getNumEmploye());
        conge.setApprouve_Desapprouve_Matricule(desapprobateur.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Poste_ID(desapprobateur.getNumeroPoste());
        conge.setHR_CongeStatut_ID(CongeStatut.DESAPPROUVE);
        conge.setDate_Approbation(null);
        conge.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        conge.save(null);
        // ✅ Notification HOLIDAY_DISAPPROVED via modelvalidator
    }

    // =========================================================================
    // VALIDATION
    // =========================================================================

    /**
     * Valide un congé et crée les absences correspondantes.
     *
     * NOUVELLE RÈGLE : impossible si le congé n'est pas au statut
     * APPROUVE — la validation ne peut plus intervenir hors séquence.
     */
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

        if (conge.getHR_CongeStatut_ID() != CongeStatut.APPROUVE) {
            log.warning("Validation refusée : congé " + idConge
                + " n'est pas au statut Approuvé (statut actuel : "
                + conge.getHR_CongeStatut_ID() + ")");
            return;
        }

        MHRTypeConge typeConge = chargerTypeConge(conge);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null);

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            verifierDroitsConge(conge, detteConge);
        }

        // Verifier que les dates sont toujours disponibles
        // (aucune absence creee entre l'approbation et la validation)
        java.util.List<Timestamp> absencesExistantes =
            HRCongeRepository.getAbsencesExistantesDansPeriode(
                conge.getC_BPartner_ID(),
                conge.getDate_Debut_Effective(),
                conge.getDate_Fin_Effective(),
                null);
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
            throw new IllegalStateException(sb.toString());
        }

        conge.setValide_Rejete_Par_Nom_ID(valideur.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(valideur.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(valideur.getNumeroPoste());
        conge.setHR_CongeStatut_ID(CongeStatut.VALIDE);
        conge.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Rejet(null);
        conge.save(null);
        // Notification HOLIDAY_VALIDATED via modelvalidator

        // Creer les absences seulement apres validation reussie
        creerAbsencesConge(conge, valideur);
    }

    // =========================================================================
    // REJET
    // =========================================================================

    /**
     * Rejette un congé et supprime les absences associées.
     *
     * NOUVELLE RÈGLE : impossible si le congé n'est pas au statut
     * APPROUVE — même contrainte que la validation.
     */
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

        int statutActuel = conge.getHR_CongeStatut_ID();
        if (statutActuel != CongeStatut.APPROUVE && statutActuel != CongeStatut.VALIDE) {
            log.warning("Rejet refusé : congé " + idConge
                + " n'est pas au statut Approuvé ou Validé (statut actuel : "
                + statutActuel + ")");
            return;
        }

        // Si le conge etait valide, supprimer les absences creees
        boolean etaitValide = (statutActuel == CongeStatut.VALIDE);

        MHRTypeConge typeConge = chargerTypeConge(conge);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null);

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            verifierDroitsConge(conge, detteConge);
        }

        conge.setValide_Rejete_Par_Nom_ID(rejeteur.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(rejeteur.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(rejeteur.getNumeroPoste());
        conge.setHR_CongeStatut_ID(CongeStatut.REJETE);
        conge.setDate_Validation(null);
        conge.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        conge.save(null);

        // Supprimer les absences si le conge etait deja valide
        if (etaitValide) {
            supprimerAbsencesConge(conge);
            log.info("Conge " + idConge + " rejete apres validation : "
                + "absences supprimees");
        }

        // ✅ Notification HOLIDAY_REJECTED via modelvalidator
    }

    // =========================================================================
    // MISE À JOUR ABSENCES
    // =========================================================================

    public static void updateAbsenceConge(Integer idConge) {
        if (idConge == null) return;

        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
        if (conge == null) return;

        if (!estStatutFinal(conge)
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

    private static boolean estStatutFinal(MHRHoliday conge) {
        int statut = conge.getHR_CongeStatut_ID();
        return statut == CongeStatut.VALIDE || statut == CongeStatut.REJETE;
    }

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

        // Recuperer le matricule de l'employe concerne
        BeanIdentifiant employe = HREmployeService.getIdentifiantByBPartner(
            conge.getC_BPartner_ID(), null);
        String matriculeEmploye = (employe != null)
            ? employe.getMatriculeEmploye() : "";

        // Poser le drapeau systeme pour bypasser les controles
        // "jour de conge" et "jour de suspension" dans le
        // ModelValidator Absence (AbsenceValidatorService).
        Env.getCtx().setProperty("#IS_CREATION_ABSENCE_SYSTEME", "Y");
        try {
            while (dateDebut.before(dateFin)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(dateDebut);

                boolean estDimanche = cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
                boolean estFerie    = MHRPublicHoliday.isJourFerie(dateDebut, null);

                if (!estDimanche && !estFerie) {
                    MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                    absence.setC_BPartner_ID(conge.getC_BPartner_ID());
                    absence.setMatricule_Employe(matriculeEmploye);
                    absence.setPoste_Employe_ID(employe.getNumeroPoste());
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
        } finally {
            Env.getCtx().remove("#IS_CREATION_ABSENCE_SYSTEME");
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
