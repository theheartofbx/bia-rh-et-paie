package org.sitracel.discipline.modelvalidator.service;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;

/**
 * Service — logique métier du modelvalidator discipline.
 *
 * Gère les transitions d'état des sanctions et demandes d'explication,
 * et déclenche les notifications correspondantes.
 *
 * Remplace ModelValidatorDisciplineController.
 */
public final class DisciplineValidatorService {

    private DisciplineValidatorService() {}

    // =========================================================================
    // DEMANDES D'EXPLICATION
    // =========================================================================

    /**
     * Traite les événements sur MHRDemandeExplication.
     *
     * À la création : horodater l'émission et notifier l'employé.
     * À la modification : détecter si une réponse vient d'être saisie.
     */
    public static void demandeExplication(MHRDemandeExplication demandeExplication,
                                           int type) {

        // À la création : horodater
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            demandeExplication.setDate_Emission(
                new Timestamp(System.currentTimeMillis()));
        }

        // Après création : notifier l'employé
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(
                NotificationEvent.DEMANDE_EXPLICATION_CREATED,
                demandeExplication);
        }

        // Avant modification : horodater si une réponse vient d'être saisie
        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            MHRDemandeExplication ancienne = new MHRDemandeExplication(
                Env.getCtx(), demandeExplication.get_ID(), null);
            if (ancienne != null) {
                String ancienneReponse = ancienne.getReponse_Demande_Explication();
                String nouvelleReponse = demandeExplication.getReponse_Demande_Explication();
                if (nouvelleReponse != null && !nouvelleReponse.equals(ancienneReponse)) {
                    demandeExplication.setDate_Reponse(
                        new Timestamp(System.currentTimeMillis()));
                }
            }
        }

        // Après modification : notifier si une réponse vient d'être saisie
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            String ancienneReponse = (String) demandeExplication.get_ValueOld(
                MHRDemandeExplication.COLUMNNAME_Reponse_Demande_Explication);
            String nouvelleReponse = demandeExplication.getReponse_Demande_Explication();

            if (nouvelleReponse != null && !nouvelleReponse.equals(ancienneReponse)) {
                NotificationControler.notify(
                    NotificationEvent.DEMANDE_EXPLICATION_REPLIED,
                    demandeExplication);
            }
        }
    }

    // =========================================================================
    // SANCTIONS
    // =========================================================================

    /**
     * Traite les événements sur MHRPunishment.
     *
     * À la création : initialiser les flags et horodater.
     * Après création : notifier la création.
     * Après modification : détecter la transition d'état.
     */
    public static void discipline(MHRPunishment punishment, int type) {

        // À la création : initialiser
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            punishment.setIsApprobation_Createur(false);
            punishment.setIsValidation_Createur(false);
            punishment.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        }

        // Après création : notifier
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(
                NotificationEvent.SANCTION_CREATED, punishment);
        }

        // Après modification : détecter la transition
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            detecterTransitionEtatSanction(punishment);
        }
    }

    // =========================================================================
    // UTILITAIRES PRIVÉS
    // =========================================================================

    /**
     * Détecte la transition d'état d'une sanction et déclenche la notification.
     * Ordre de priorité : Approbation > Désapprobation > Validation > Rejet.
     */
    private static void detecterTransitionEtatSanction(MHRPunishment punishment) {

        // Approbation
        if (punishment.isApprobation()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsApprobation)) {
            NotificationControler.notify(NotificationEvent.SANCTION_APPROVED, punishment);
            return;
        }

        // Désapprobation
        if (punishment.isDesapprouve()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsDesapprouve)) {
            NotificationControler.notify(NotificationEvent.SANCTION_DISAPPROVED, punishment);
            return;
        }

        // Validation
        if (punishment.isValidee()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsValidee)) {
            NotificationControler.notify(NotificationEvent.SANCTION_VALIDATED, punishment);
            return;
        }

        // Rejet
        if (punishment.isRejetee()
                && !getBooleanOld(punishment, MHRPunishment.COLUMNNAME_IsRejetee)) {
            NotificationControler.notify(NotificationEvent.SANCTION_REJECTED, punishment);
        }
    }

    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) return (Boolean) oldValue;
        if (oldValue instanceof String) return "Y".equalsIgnoreCase((String) oldValue);
        return false;
    }
}
