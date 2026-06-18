package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.modelvalidator.controller.absence.ModelValidatorControllerAbsence;
import org.sitracel.conge.modelvalidator.controller.conge.ModelValidatorControllerConge;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

public class SitracelCongeGeneralModelValidator {

    private static CLogger log = CLogger.getCLogger(PO.class);

    // =========================================================================
    // CONGÉS — détection des transitions d'état
    // =========================================================================

    public static void conge(PO po, int type) {

        // --- À la création : initialiser les flags et la date d'émission
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            po.set_ValueOfColumn("isapprobation_createur", false);
            po.set_ValueOfColumn("isvalidation_createur", false);
            po.set_ValueOfColumn("date_emission", new Timestamp(System.currentTimeMillis()));
        }

        // --- Après création : notifier la création
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_CREATED, po);
        }

        // --- Après modification : détecter la transition d'état
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            MHRHoliday holiday = (MHRHoliday) po;
            detecterTransitionEtatConge(holiday, po);
        }
    }

    /**
     * Détecte quelle transition d'état vient de se produire sur un congé
     * et déclenche la notification correspondante.
     *
     * Les états sont portés par des booléens sur MHRHoliday :
     *   isApprobation  → congé approuvé par le N+1
     *   isDesapprobation → congé désapprouvé
     *   isValidee      → congé validé définitivement
     *   isRejetee      → congé rejeté définitivement
     */
    private static void detecterTransitionEtatConge(MHRHoliday holiday, PO po) {

        // Approbation : isApprobation passe de false à true
        boolean nouvelleApprobation  = holiday.isApprobation();
        boolean ancienneApprobation  = getBooleanOld(po, MHRHoliday.COLUMNNAME_IsApprobation);
        if (nouvelleApprobation && !ancienneApprobation) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_APPROVED, po);
            return;
        }

        // Désapprobation : isDesapprobation passe de false à true
        boolean nouvelleDesapprobation = holiday.isDesapprobation();
        boolean ancienneDesapprobation = getBooleanOld(po, MHRHoliday.COLUMNNAME_IsDesapprobation);
        if (nouvelleDesapprobation && !ancienneDesapprobation) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_DISAPPROVED, po);
            return;
        }

        // Validation : isValidee passe de false à true
        boolean nouvelleValidation  = holiday.isValidee();
        boolean ancienneValidation  = getBooleanOld(po, MHRHoliday.COLUMNNAME_IsValidee);
        if (nouvelleValidation && !ancienneValidation) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_VALIDATED, po);
            return;
        }

        // Rejet : isRejetee passe de false à true
        boolean nouveauRejet  = holiday.isRejetee();
        boolean ancienRejet   = getBooleanOld(po, MHRHoliday.COLUMNNAME_IsRejetee);
        if (nouveauRejet && !ancienRejet) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_REJECTED, po);
            return;
        }
    }

    // =========================================================================
    // ABSENCES
    // =========================================================================

    public static void absence(PO po, MHRAbsence absence, int type) {
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
            ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
        }
        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            ModelValidatorControllerAbsence.annulerDemandeExplicationSuiteAbsence(absence);
        }
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            boolean newIsDemandeExplication = absence.isDemandeExplication();
            boolean oldIsDemandeExplication = getBooleanOld(po, MHRAbsence.COLUMNNAME_IsDemandeExplication);
            if (newIsDemandeExplication && !oldIsDemandeExplication) {
                ModelValidatorControllerAbsence.annulerDemandeExplicationSuiteAbsence(absence);
                ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
            }
            ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
        }
    }

    // =========================================================================
    // COMPENSATION DE CONGÉ
    // =========================================================================

    public static void compenseAbence(MHRAbsenceCompensation absenceCompense, int type) {
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            ModelValidatorControllerConge.activerCompensationConge(absenceCompense);
        }
        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            ModelValidatorControllerConge.desactiverCompensationConge(absenceCompense);
        }
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            ModelValidatorControllerConge.desactiverCompensationConge(absenceCompense);
            ModelValidatorControllerConge.activerCompensationConge(absenceCompense);
        }
    }

    // =========================================================================
    // AFFECTATION EMPLOYÉ (poste/département)
    // =========================================================================

    public static void employeeJobD(MHRElementBasePaieEmploye lastElement, int type) {
        if (ModelValidator.TYPE_AFTER_CHANGE == type || ModelValidator.TYPE_AFTER_NEW == type) {
            ModelValidatorControllerConge.updateDepartment(lastElement.getC_BPartner_ID());
        }
    }

    // =========================================================================
    // UTILITAIRE
    // =========================================================================

    /**
     * Récupère l'ancienne valeur booléenne d'un champ avant modification.
     * Retourne false si la valeur est null ou absente.
     */
    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) {
            return (Boolean) oldValue;
        }
        if (oldValue instanceof String) {
            return "Y".equalsIgnoreCase((String) oldValue);
        }
        return false;
    }
}
