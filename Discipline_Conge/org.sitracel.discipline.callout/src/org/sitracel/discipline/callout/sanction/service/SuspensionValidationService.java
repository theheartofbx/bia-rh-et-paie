package org.sitracel.discipline.callout.sanction.service;

import java.sql.Timestamp;

import org.compiere.model.GridTab;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRPunishment;

/**
 * Service partagé — valide qu'une période de suspension ne chevauche pas
 * une autre suspension, un congé ou une absence.
 *
 * Utilisé par CalloutDureeSuspension ET CalloutDateDebutApplication, qui
 * déclenchent la même règle métier à deux moments différents (l'utilisateur
 * peut saisir la durée ou la date en premier). Avant cette factorisation,
 * la règle était écrite deux fois séparément et avait déjà divergé
 * silencieusement (l'une des deux copies avait une condition inversée et
 * un bug de réinitialisation systématique).
 */
public final class SuspensionValidationService {

    private SuspensionValidationService() {}

    /**
     * Vérifie la période [dateDebut, dateFin] pour l'employé donné.
     *
     * En cas de chevauchement : réinitialise Date_Debut_Application et
     * HR_Duree_Sanction_ID, affiche le message d'alerte correspondant,
     * et retourne false.
     * Si tout est valide : efface le message d'alerte existant et
     * retourne true.
     */
    public static boolean validerPeriodeSuspension(GridTab mTab, Integer bpartnerID,
                                                    Timestamp dateDebut, Timestamp dateFin) {
        if (bpartnerID == null) {
            bloquerAvecMessage(mTab, "Veuillez renseigner l'employé concerné.");
            return false;
        }

        if (GeneralSqlController.chevaucheSuspensionNonRejete(bpartnerID, dateDebut, dateFin, null)) {
            bloquerAvecMessage(mTab, "Une autre période de suspension a été enregistrée durant cette période.");
            return false;
        }

        if (GeneralSqlController.chevaucheAnyCongeNonRejete(bpartnerID, dateDebut, dateFin, null)) {
            bloquerAvecMessage(mTab, "Une période de congé a été enregistrée durant cette période.");
            return false;
        }

        if (GeneralSqlController.isPeriodeAbsence(bpartnerID, dateDebut, dateFin, null)) {
            bloquerAvecMessage(mTab, "Une absence a été enregistrée durant cette période.");
            return false;
        }

        mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, false);
        mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "");
        return true;
    }

    private static void bloquerAvecMessage(GridTab mTab, String message) {
        mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
        mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
        mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
        mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, message);
    }
}
