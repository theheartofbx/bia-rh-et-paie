package org.sitracel.conge.process.conge.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.callout.absence.controller.CalloutSqlControllerAbsence;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MCBPartner;

/**
 * Logique métier des processus de gestion des congés.
 *
 * Les notifications sont gérées automatiquement par le modelvalidator
 * (SitracelCongeGeneralModelValidator) lors de chaque conge.save().
 * Ne pas rajouter d'appels sendEmail() ici.
 */
public class ProcessControllerConge {

    // =========================================================================
    // VALIDATION
    // =========================================================================

    public static void validerConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || beanIdentifiant == null) return;
        if (beanIdentifiant.getNomEmploye() == null) return;

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null
        );

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            if (conge.getJours_Conge_Total()
                    - conge.getJours_Conge_Correspondant()
                    - conge.getJours_Conge_Deja_Utilise()
                    - detteConge < 0) {
                conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
                conge.setIsMessageAlerteDisplayed(true);
                conge.save();
            } else {
                conge.setJours_Conge_A_Compenser(detteConge);
                conge.save();
            }
        }

        conge.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
        conge.setIsTraitee(true);
        conge.setIsValidee(true);
        conge.setIsRejetee(false);
        conge.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Rejet(null);
        conge.save(null);
        // ✅ La notification HOLIDAY_VALIDATED part automatiquement via le modelvalidator

        gererAbsenceApresValidationConge(conge, beanIdentifiant);
    }

    // =========================================================================
    // REJET
    // =========================================================================

    public static void rejeterConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant beanIdentifiant = MCBPartner.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || beanIdentifiant == null) return;
        if (beanIdentifiant.getNomEmploye() == null) return;

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), conge.getEmission_Conge_ID(), null);
        int detteConge = GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
            conge.getDate_Debut_Souhaitee(), null
        );

        if (typeConge != null && typeConge.isCongeAnnuel()) {
            if (conge.getJours_Conge_Total()
                    - conge.getJours_Conge_Correspondant()
                    - conge.getJours_Conge_Deja_Utilise()
                    - detteConge < 0) {
                conge.setMessage_Alerte("Attention la période de congé dépasse les droits de l'employé");
                conge.setIsMessageAlerteDisplayed(true);
                conge.save();
            }
        }

        conge.setValide_Rejete_Par_Nom_ID(beanIdentifiant.getNumEmploye());
        conge.setValide_Rejete_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
        conge.setValide_Rejete_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
        conge.setIsTraitee(true);
        conge.setIsValidee(false);
        conge.setIsRejetee(true);
        conge.setDate_Validation(null);
        conge.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        conge.save(null);
        // ✅ La notification HOLIDAY_REJECTED part automatiquement via le modelvalidator

        gererAbsenceApresRejetSanction(conge, beanIdentifiant);
    }

    // =========================================================================
    // APPROBATION
    // =========================================================================

    public static void approuverConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        conge.setApprouve_Desapprouve_Par_Nom_ID(bi.getNumEmploye());
        conge.setApprouve_Desapprouve_Par_Matricule(bi.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Par_Poste_ID(bi.getNumeroPoste());
        conge.setIsApprobation(true);
        conge.setIsDesapprobation(false);
        conge.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        conge.setDate_Desapprobation(null);
        conge.save(null);
        // ✅ La notification HOLIDAY_APPROVED part automatiquement via le modelvalidator
    }

    // =========================================================================
    // DÉSAPPROBATION
    // =========================================================================

    public static void desapprouverConge(Integer idConge, Integer adUserID) {
        if (idConge == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);

        if (conge == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        conge.setApprouve_Desapprouve_Par_Nom_ID(bi.getNumEmploye());
        conge.setApprouve_Desapprouve_Par_Matricule(bi.getMatriculeEmploye());
        conge.setApprouve_Desapprouve_Par_Poste_ID(bi.getNumeroPoste());
        conge.setIsApprobation(false);
        conge.setIsDesapprobation(true);
        conge.setDate_Approbation(null);
        conge.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        conge.save(null);
        // ✅ La notification HOLIDAY_DISAPPROVED part automatiquement via le modelvalidator
    }

    // =========================================================================
    // MISE À JOUR ABSENCES APRÈS VALIDATION
    // =========================================================================

    public static void updateAbsenceConge(Integer idConge) {
        if (idConge == null) return;
        MHRHoliday conge = new MHRHoliday(Env.getCtx(), idConge, null);
        if (conge != null) {
            if (!conge.isValidee() && !conge.isRejetee()
                    && conge.getDate_Debut_Effective().after(
                        new Timestamp(System.currentTimeMillis()))) {
                conge.setJours_Conge_A_Compenser(
                    GeneralSqlController.getNombreJourAbsencesCongeNonTraite(
                        conge.getDate_Debut_Souhaitee(), null
                    )
                );
                conge.save();
            }
        }
    }

    // =========================================================================
    // GESTION DES ABSENCES SUITE À VALIDATION/REJET
    // =========================================================================

    private static void gererAbsenceApresValidationConge(
            MHRHoliday holiday, BeanIdentifiant beanIdentifiant) {

        if (holiday == null || beanIdentifiant == null) return;

        MHRAutorisationConge autorisation = new MHRAutorisationConge(
            Env.getCtx(), holiday.getEmission_Conge_ID(), null
        );
        if (autorisation == null) return;

        MHRTypeConge typeConge = new MHRTypeConge(
            Env.getCtx(), autorisation.getHR_Type_Conge_ID(), null
        );
        if (typeConge == null) return;

        Timestamp dateDebutConge = holiday.getDate_Debut_Effective();
        Timestamp dateFinConge   = holiday.getDate_Fin_Effective();

        Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(
            I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
            I_HR_Type_Absence.Table_Name,
            I_HR_Type_Absence.COLUMNNAME_Nom_Absence,
            "En Congé", null
        );

        if (typeAbsenceID == null) return;

        while (dateDebutConge.before(dateFinConge)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateDebutConge);
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    || !MHRPublicHoliday.isJourFerie(dateDebutConge, null)) {

                MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                absence.setEmis_Par_Nom_ID(beanIdentifiant.getNumEmploye());
                absence.setEmis_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
                absence.setEmis_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
                absence.setDate_Absence(dateDebutConge);
                absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
                absence.setHR_Type_Absence_ID(typeAbsenceID);
                absence.setIsDemandeExplication(false);
                absence.setIsConge(false);
                absence.setIsDemandeExplicationTraite(true);
                absence.setIsCongeTraite(true);
                absence.save(null);
            }
            dateDebutConge = GeneralController.ajouterNombreJour(dateDebutConge, 1);
        }
    }

    private static void gererAbsenceApresRejetSanction(
            MHRHoliday holiday, BeanIdentifiant beanIdentifiant) {

        if (holiday == null || beanIdentifiant == null) return;
        CalloutSqlControllerAbsence.annulerAbsenceConge(
            holiday.getC_BPartner_ID(),
            holiday.getDate_Debut_Effective(),
            holiday.getDate_Fin_Effective(),
            null
        );
    }
}
