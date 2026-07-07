package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.modelvalidator.service.CongeAbsenceValidatorService;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Point central de traitement des événements congés.
 *
 * Depuis Session 9, la logique Absence (seuil de demandes
 * d'explication, compensation) a été déplacée vers
 * org.sitracel.absence.modelvalidator — elle n'a plus sa place ici.
 *
 * NE PAS ajouter de logique métier directement ici.
 */
public class SitracelCongeGeneralModelValidator {

    private static final CLogger log = CLogger.getCLogger(SitracelCongeGeneralModelValidator.class);

    // =========================================================================
    // CONGÉS — détection des transitions d'état
    // =========================================================================

    public static void conge(PO po, int type) {

        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsApprobation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsValidation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_Date_Emission,
                new Timestamp(System.currentTimeMillis()));
        }

        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_CREATED, po);
        }

        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            MHRHoliday holiday = (MHRHoliday) po;
            detecterTransitionEtatConge(holiday, po);
        }
    }

    private static void detecterTransitionEtatConge(MHRHoliday holiday, PO po) {

        if (holiday.isApprobation()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsApprobation)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_APPROVED, po);
            return;
        }

        if (holiday.isDesapprouve()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsDesapprouve)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_DISAPPROVED, po);
            return;
        }

        if (holiday.isValidee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsValidee)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_VALIDATED, po);
            return;
        }

        if (holiday.isRejetee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsRejetee)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_REJECTED, po);
        }
    }

    // =========================================================================
    // AFFECTATION EMPLOYÉ (changement de poste/contrat)
    // =========================================================================

    public static void employeeJobD(MHRElementBasePaieEmploye lastElement, int type) {
        if (ModelValidator.TYPE_AFTER_CHANGE == type
                || ModelValidator.TYPE_AFTER_NEW == type) {
            CongeAbsenceValidatorService.updateDepartment(lastElement.getC_BPartner_ID());
        }
    }

    // =========================================================================
    // UTILITAIRE
    // =========================================================================

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
