package org.sitracel.discipline.process.sanction.controller;

import java.sql.Timestamp;
import java.util.Calendar;

import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.conge.callout.absence.controller.CalloutSqlControllerAbsence;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.discipline.model.X_HR_TypeSanction;
import org.sitracel.model.MCBPartner;

/**
 * Logique métier des processus de gestion des sanctions.
 *
 * Les notifications sont gérées automatiquement par le modelvalidator
 * (ModelValidatorDisciplineController) lors de chaque punishment.save().
 * Ne pas rajouter d'appels sendEmail() ici.
 */
public class ProcessControllerDiscipline {

    private static CLogger log = CLogger.getCLogger(PO.class);

    // =========================================================================
    // VALIDATION SANCTION
    // =========================================================================

    public static void validerSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        punishment.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(true);
        punishment.setIsRejetee(false);
        punishment.setDate_Validation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Rejet(null);
        punishment.save(null);
        // ✅ La notification SANCTION_VALIDATED part automatiquement via le modelvalidator

        gererAbsenceApresValidationSanction(punishment, bi);
        creerDossierDisciplinaire(punishment);
    }

    // =========================================================================
    // REJET SANCTION
    // =========================================================================

    public static void rejeterSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        punishment.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
        punishment.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
        punishment.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
        punishment.setIsTraitee(true);
        punishment.setIsValidee(false);
        punishment.setIsRejetee(true);
        punishment.setDate_Validation(null);
        punishment.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
        punishment.save(null);
        // ✅ La notification SANCTION_REJECTED part automatiquement via le modelvalidator
    }

    // =========================================================================
    // APPROBATION SANCTION
    // =========================================================================

    public static void approuverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        punishment.setApprouve_Desapprouve_Par_Nom_ID(bi.getNumEmploye());
        punishment.setApprouve_Desapprouve_Par_Matricule(bi.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Par_Poste_ID(bi.getNumeroPoste());
        punishment.setIsApprobation(true);
        punishment.setIsDesapprouve(false);
        punishment.setDate_Approbation(new Timestamp(System.currentTimeMillis()));
        punishment.setDate_Desapprobation(null);
        punishment.save(null);
        // ✅ La notification SANCTION_APPROVED part automatiquement via le modelvalidator
    }

    // =========================================================================
    // DÉSAPPROBATION SANCTION
    // =========================================================================

    public static void desapprouverSanction(Integer idSanction, Integer adUserID) {
        if (idSanction == null || adUserID == null) return;

        BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
        MHRPunishment punishment = new MHRPunishment(Env.getCtx(), idSanction, null);

        if (punishment == null || bi == null) return;
        if (bi.getNomEmploye() == null) return;

        punishment.setApprouve_Desapprouve_Par_Nom_ID(bi.getNumEmploye());
        punishment.setApprouve_Desapprouve_Par_Matricule(bi.getMatriculeEmploye());
        punishment.setApprouve_Desapprouve_Par_Poste_ID(bi.getNumeroPoste());
        punishment.setIsApprobation(false);
        punishment.setIsDesapprouve(true);
        punishment.setDate_Approbation(null);
        punishment.setDate_Desapprobation(new Timestamp(System.currentTimeMillis()));
        punishment.save(null);
        // ✅ La notification SANCTION_DISAPPROVED part automatiquement via le modelvalidator
    }

    // =========================================================================
    // GESTION ABSENCES APRÈS VALIDATION SANCTION
    // =========================================================================

    private static void gererAbsenceApresValidationSanction(
            MHRPunishment punishment, BeanIdentifiant beanIdentifiant) {

        if (punishment == null || beanIdentifiant == null) return;

        MHRSanctionAutorisation autorisation = new MHRSanctionAutorisation(
            Env.getCtx(), punishment.getEmission_Sanction_ID(), null
        );
        if (autorisation == null) return;

        MHRTypeSanction typeSanction = new MHRTypeSanction(
            Env.getCtx(), autorisation.getHR_TypeSanction_ID(), null
        );
        if (typeSanction == null) return;

        if (!typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(
                X_HR_TypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)) return;

        MHRDureeSanction dureeSanction = new MHRDureeSanction(
            Env.getCtx(), punishment.getHR_Duree_Sanction_ID(), null
        );
        int delaiApplication = dureeSanction != null ? dureeSanction.getNombre_De_Jour() : 0;

        Timestamp debutAbs = punishment.getDate_Debut_Application();
        Timestamp finAbs   = GeneralController.ajouterNombreJour(debutAbs, delaiApplication);

        Integer typeAbsenceID = GeneralSqlController.getIDFromTableNameAndName(
            I_HR_Type_Absence.COLUMNNAME_HR_Type_Absence_ID,
            I_HR_Type_Absence.Table_Name,
            I_HR_Type_Absence.COLUMNNAME_Nom_Absence,
            "Suspendu", null
        );
        if (typeAbsenceID == null) return;

        if (delaiApplication > 0) {
            punishment.setDate_Fin_Application(finAbs);
        } else {
            punishment.setDate_Fin_Application(null);
        }

        while (debutAbs.before(finAbs)) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(debutAbs);
            if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY
                    || !MHRPublicHoliday.isJourFerie(debutAbs, null)) {

                MHRAbsence absence = new MHRAbsence(Env.getCtx(), null, null);
                absence.setEmis_Par_Nom_ID(beanIdentifiant.getNumEmploye());
                absence.setEmis_Par_Poste_ID(beanIdentifiant.getNumeroPoste());
                absence.setEmis_Par_Matricule(beanIdentifiant.getMatriculeEmploye());
                absence.setC_BPartner_ID(punishment.getC_BPartner_ID());
                absence.setDate_Absence(debutAbs);
                absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
                absence.setHR_Type_Absence_ID(typeAbsenceID);
                absence.setIsDemandeExplication(false);
                absence.setIsConge(false);
                absence.setIsDemandeExplicationTraite(true);
                absence.setIsCongeTraite(true);
                absence.save(null);
            }
            debutAbs = GeneralController.ajouterNombreJour(debutAbs, 1);
        }
    }

    // =========================================================================
    // DOSSIER DISCIPLINAIRE
    // =========================================================================

    private static void creerDossierDisciplinaire(MHRPunishment punishment) {
        if (punishment == null) return;
        MHRDossierDisciplinaire dossier = new MHRDossierDisciplinaire(Env.getCtx(), 0, null);
        dossier.setC_BPartner_ID(punishment.getC_BPartner_ID());
        dossier.setHR_Punishment_ID(punishment.getHR_Punishment_ID());
        dossier.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        dossier.save(null);
    }
}
