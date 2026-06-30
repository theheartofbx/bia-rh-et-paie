package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.modelvalidator.service.CongeAbsenceValidatorService;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Point central de traitement des événements congés et absences.
 *
 * Délègue toute la logique métier aux services dédiés :
 *   - {@link CongeAbsenceValidatorService} pour les absences et compensations
 *   - {@link NotificationControler} pour les notifications
 *
 * NE PAS ajouter de logique métier directement ici.
 */
public class SitracelCongeGeneralModelValidator {

    private static final CLogger log = CLogger.getCLogger(SitracelCongeGeneralModelValidator.class);

    // =========================================================================
    // CONGÉS — détection des transitions d'état
    // =========================================================================

    /**
     * Traite les événements sur MHRHoliday.
     * Initialise les flags à la création et notifie les transitions d'état.
     */
    public static void conge(PO po, int type) {

        // À la création : initialiser les flags et horodater
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsApprobation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_IsValidation_Createur, false);
            po.set_ValueOfColumn(MHRHoliday.COLUMNNAME_Date_Emission,
                new Timestamp(System.currentTimeMillis()));
        }

        // Après création : notifier
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_CREATED, po);
        }

        // Après modification : détecter la transition d'état
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            MHRHoliday holiday = (MHRHoliday) po;
            detecterTransitionEtatConge(holiday, po);
        }
    }

    /**
     * Détecte la transition d'état d'un congé et déclenche la notification.
     *
     * Ordre de priorité : Approbation > Désapprobation > Validation > Rejet.
     * On sort dès la première transition détectée (return).
     */
    private static void detecterTransitionEtatConge(MHRHoliday holiday, PO po) {

        // Approbation
        if (holiday.isApprobation()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsApprobation)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_APPROVED, po);
            return;
        }

        // Désapprobation
        if (holiday.isDesapprouve()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsDesapprouve)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_DISAPPROVED, po);
            return;
        }

        // Validation
        if (holiday.isValidee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsValidee)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_VALIDATED, po);
            return;
        }

        // Rejet
        if (holiday.isRejetee()
                && !getBooleanOld(po, MHRHoliday.COLUMNNAME_IsRejetee)) {
            NotificationControler.notify(NotificationEvent.HOLIDAY_REJECTED, po);
        }
    }

    // =========================================================================
    // ABSENCES
    // =========================================================================

    /**
     * Traite les événements sur MHRAbsence.
     *
     * CORRECTION BUG : traiterDemandeExplication n'est appelée QUE si
     * IsDemandeExplication passe de false à true (plus de doublon).
     */
    public static void absence(PO po, MHRAbsence absence, int type) {

        // À la création : horodater et vérifier le seuil d'absences
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));
            CongeAbsenceValidatorService.traiterDemandeExplicationSuiteAbsence(absence);
        }

        // Avant suppression : annuler la demande d'explication liée
        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            CongeAbsenceValidatorService.annulerDemandeExplicationSuiteAbsence(absence);
        }

        // Après modification : traiter uniquement si IsDemandeExplication
        // vient de passer à true (transition false → true)
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            boolean nouvelleValeur = absence.isDemandeExplication();
            boolean ancienneValeur = getBooleanOld(po,
                MHRAbsence.COLUMNNAME_IsDemandeExplication);

            if (nouvelleValeur && !ancienneValeur) {
                // Annuler l'ancienne demande puis créer la nouvelle
                CongeAbsenceValidatorService.annulerDemandeExplicationSuiteAbsence(absence);
                CongeAbsenceValidatorService.traiterDemandeExplicationSuiteAbsence(absence);
            }
            // ✅ BUG CORRIGÉ : pas d'appel inconditionnel ici
        }
    }

    // =========================================================================
    // COMPENSATION DE CONGÉ
    // =========================================================================

    /**
     * Traite les événements sur MHRAbsenceCompensation.
     */
    public static void compenseAbence(MHRAbsenceCompensation absenceCompense, int type) {

        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            CongeAbsenceValidatorService.activerCompensationConge(absenceCompense);
        }

        if (ModelValidator.TYPE_BEFORE_DELETE == type) {
            CongeAbsenceValidatorService.desactiverCompensationConge(absenceCompense);
        }

        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            CongeAbsenceValidatorService.desactiverCompensationConge(absenceCompense);
            CongeAbsenceValidatorService.activerCompensationConge(absenceCompense);
        }
    }

    // =========================================================================
    // AFFECTATION EMPLOYÉ (changement de poste/contrat)
    // =========================================================================

    /**
     * Traite les événements sur MHRElementBasePaieEmploye.
     * Met à jour le département de l'employé si son contrat change.
     */
    public static void employeeJobD(MHRElementBasePaieEmploye lastElement, int type) {
        if (ModelValidator.TYPE_AFTER_CHANGE == type
                || ModelValidator.TYPE_AFTER_NEW == type) {
            CongeAbsenceValidatorService.updateDepartment(lastElement.getC_BPartner_ID());
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
