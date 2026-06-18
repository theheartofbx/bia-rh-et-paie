package org.sitracel.discipline.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationControler;

public class ModelValidatorDisciplineController {

    // =========================================================================
    // DEMANDE D'EXPLICATION
    // =========================================================================

    public static void demandeExplication(MHRDemandeExplication demandeExplication, int type) {

        // À la création : horodater l'émission
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            demandeExplication.setDate_Emission(
                new Timestamp(System.currentTimeMillis())
            );
        }

        // Après création : notifier l'employé
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(
                NotificationEvent.DEMANDE_EXPLICATION_CREATED,
                demandeExplication
            );
        }

        // Avant modification : détecter si une réponse vient d'être saisie
        if (ModelValidator.TYPE_BEFORE_CHANGE == type) {
            MHRDemandeExplication ancienne = new MHRDemandeExplication(
                Env.getCtx(), demandeExplication.get_ID(), null
            );
            if (ancienne != null) {
                String ancienneReponse  = ancienne.getReponse_Demande_Explication();
                String nouvelleReponse  = demandeExplication.getReponse_Demande_Explication();
                boolean reponseAjoutee  =
                    nouvelleReponse != null
                    && !nouvelleReponse.equals(ancienneReponse);

                if (reponseAjoutee) {
                    demandeExplication.setDate_Reponse(
                        new Timestamp(System.currentTimeMillis())
                    );
                }
            }
        }

        // Après modification : notifier si une réponse vient d'être saisie
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            String ancienneReponse = (String) demandeExplication.get_ValueOld(
                MHRDemandeExplication.COLUMNNAME_Reponse_Demande_Explication
            );
            String nouvelleReponse = demandeExplication.getReponse_Demande_Explication();

            boolean reponseAjoutee =
                nouvelleReponse != null
                && !nouvelleReponse.equals(ancienneReponse);

            if (reponseAjoutee) {
                NotificationControler.notify(
                    NotificationEvent.DEMANDE_EXPLICATION_REPLIED,
                    demandeExplication
                );
            }
        }
    }

    // =========================================================================
    // SANCTIONS (MHRPunishment)
    // =========================================================================

    public static void discipline(MHRPunishment punishment, int type) {

        // À la création : initialiser les flags et horodater
        if (ModelValidator.TYPE_BEFORE_NEW == type) {
            punishment.setIsApprobation_Createur(false);
            punishment.setIsValidation_Createur(false);
            punishment.setDate_Emission(new Timestamp(System.currentTimeMillis()));
        }

        // Après création : notifier
        if (ModelValidator.TYPE_AFTER_NEW == type) {
            NotificationControler.notify(
                NotificationEvent.SANCTION_CREATED,
                punishment
            );
        }

        // Après modification : détecter la transition d'état
        if (ModelValidator.TYPE_AFTER_CHANGE == type) {
            detecterTransitionEtatSanction(punishment);
        }
    }

    /**
     * Détecte quelle transition d'état vient de se produire sur une sanction
     * et déclenche la notification correspondante.
     *
     * Même logique que pour les congés — les états sont portés par des booléens :
     *   isApprobation    → sanction approuvée par le N+1
     *   isDesapprobation → sanction désapprouvée
     *   isValidee        → sanction validée définitivement
     *   isRejetee        → sanction rejetée définitivement
     */
    private static void detecterTransitionEtatSanction(MHRPunishment punishment) {

        // Approbation
        boolean nouvelleApprobation = punishment.isApprobation();
        boolean ancienneApprobation = getBooleanOld(punishment,
            MHRPunishment.COLUMNNAME_IsApprobation);
        if (nouvelleApprobation && !ancienneApprobation) {
            NotificationControler.notify(NotificationEvent.SANCTION_APPROVED, punishment);
            return;
        }

        // Désapprobation
        boolean nouvelleDesapprobation = punishment.isDesapprobation();
        boolean ancienneDesapprobation = getBooleanOld(punishment,
            MHRPunishment.COLUMNNAME_IsDesapprobation);
        if (nouvelleDesapprobation && !ancienneDesapprobation) {
            NotificationControler.notify(NotificationEvent.SANCTION_DISAPPROVED, punishment);
            return;
        }

        // Validation
        boolean nouvelleValidation = punishment.isValidee();
        boolean ancienneValidation = getBooleanOld(punishment,
            MHRPunishment.COLUMNNAME_IsValidee);
        if (nouvelleValidation && !ancienneValidation) {
            NotificationControler.notify(NotificationEvent.SANCTION_VALIDATED, punishment);
            return;
        }

        // Rejet
        boolean nouveauRejet = punishment.isRejetee();
        boolean ancienRejet  = getBooleanOld(punishment,
            MHRPunishment.COLUMNNAME_IsRejetee);
        if (nouveauRejet && !ancienRejet) {
            NotificationControler.notify(NotificationEvent.SANCTION_REJECTED, punishment);
            return;
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
