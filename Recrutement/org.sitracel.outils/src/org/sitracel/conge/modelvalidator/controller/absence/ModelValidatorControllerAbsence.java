package org.sitracel.conge.modelvalidator.controller.absence;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.compiere.model.MBPartner;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanInfoAbsence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.model.I_C_BPartner;
import org.sitracel.model.MCBPartner;
import org.sitracel.notification.NotificationControler;

public class ModelValidatorControllerAbsence {

    public static int getRHSystemID() {
        return GeneralSqlController.getParametreFromParametreNumerique("RH_Système_ID");
    }

    public static int getDelaiReponse() {
        return GeneralSqlController.getParametreFromParametreNumerique("Délai Réponse Demande Explication");
    }

    // =========================================================================
    // DEMANDE D'EXPLICATION suite à absence
    // =========================================================================

    public static void traiterDemandeExplicationSuiteAbsence(MHRAbsence absence) {
        if (absence == null) return;

        Integer typeAbsenceId = absence.getHR_Type_Absence_ID();
        if (typeAbsenceId == null) return;

        MHRTypeAbsence typeAbsence = new MHRTypeAbsence(Env.getCtx(), typeAbsenceId, null);
        int absMax        = GeneralController.getNombreJourMaxAbsenceAvantDemandeExplication();
        int rhSystemID    = getRHSystemID();
        int delaiReponse  = getDelaiReponse();

        BeanIdentifiant beanRHSystem = MCBPartner.getIdentifiant(rhSystemID, null);
        BeanIdentifiant beanPartner  = MCBPartner.getIdentifiant(absence.getC_BPartner_ID(), null);

        if (typeAbsence == null) return;

        BeanInfoAbsence infoAbsence =
            ModelValidatorSqlControllerAbsence.getAbsenceNonAutoriseNonTraite(
                absence.getC_BPartner_ID(), null
            );

        if (!typeAbsence.isDemandeExplication()) return;
        if (infoAbsence == null) return;
        if ((infoAbsence.getNombreJour() + 1) < absMax) return;

        try {
            MHRDemandeExplication demandeExplication =
                new MHRDemandeExplication(Env.getCtx(), null, null);

            demandeExplication.setMotif_Demande_Explication(
                "Vous avez été absent(e) le(s) " + infoAbsence.getDate()
                + " sans justification, nous vous prions d'éclaircir la situation "
                + "et de nous donner des explications sur cet état de fait."
            );
            demandeExplication.setC_BPartner_ID(absence.getC_BPartner_ID());
            demandeExplication.setEmis_Par_Nom_ID(rhSystemID);
            demandeExplication.setHR_Delai_Reponse_ID(delaiReponse);

            if (beanRHSystem != null) {
                demandeExplication.setEmis_Par_Poste_ID(beanRHSystem.getNumeroPoste());
                demandeExplication.setEmis_Par_Matricule(beanRHSystem.getMatriculeEmploye());
            }
            if (beanPartner != null) {
                demandeExplication.setPoste_Employe_ID(beanPartner.getNumeroPoste());
            }

            demandeExplication.setIsActive(true);
            demandeExplication.saveEx();

            // ✅ Nouveau système : NotificationControler.notify()
            //    remplace l'ancien sendEmail() direct
            NotificationControler.notify(
                NotificationEvent.DEMANDE_EXPLICATION_CREATED,
                demandeExplication
            );

        } catch (Exception e) {
            // On log l'erreur mais on ne bloque pas la sauvegarde de l'absence
            e.printStackTrace();
        }
    }

    public static void annulerDemandeExplicationSuiteAbsence(MHRAbsence absence) {
        if (absence == null) return;
        ModelValidatorSqlControllerAbsence.annulerDemandeExplicationNonTraite(
            absence.getC_BPartner_ID(),
            absence.getDate_Absence(),
            null
        );
    }
}
