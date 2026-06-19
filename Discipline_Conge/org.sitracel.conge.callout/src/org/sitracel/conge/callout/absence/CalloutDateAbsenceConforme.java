package org.sitracel.conge.callout.absence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.conge.callout.absence.service.AbsenceCalloutRepository;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.repository.HRCongeRepository;

/**
 * Callout — validation de la date d'absence.
 *
 * Vérifie que la date saisie ne correspond pas à :
 *   - une absence déjà enregistrée
 *   - un dimanche ou jour férié
 *   - un jour de congé de l'employé
 *   - un jour de suspension de l'employé
 */
public class CalloutDateAbsenceConforme implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        // Réinitialiser le message d'alerte
        mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, false);
        mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "");

        Timestamp date     = (Timestamp) mTab.getValue(MHRAbsence.COLUMNNAME_Date_Absence);
        Integer bpartnerId = (Integer)   mTab.getValue(MHRAbsence.COLUMNNAME_C_BPartner_ID);

        if (date == null || bpartnerId == null) return null;

        String dateFormatee = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date);

        // Absence déjà enregistrée ce jour
        if (AbsenceCalloutRepository.isAbsenceExist(date, bpartnerId, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
            mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte,
                "Une absence a déjà été enregistrée le " + dateFormatee);
            return null;
        }

        // Dimanche ou jour férié
        if (MHRPublicHoliday.isJourFerie(date, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
            mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte,
                "Le " + dateFormatee + " est un dimanche ou un jour férié.");
            return null;
        }

        // Jour de congé de l'employé
        if (HRCongeRepository.isJourCongesNonRejeteByNameConge(
                bpartnerId, "Annuel", date, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
            mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte,
                "Le " + dateFormatee + " fait partie des jours de congé de l'employé.");
            return null;
        }

        // Jour de suspension de l'employé
        if (HRCongeRepository.isJourSuspensionNonRejete(bpartnerId, date, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
            mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte,
                "Le " + dateFormatee
                + " est compris dans une période de suspension de l'employé.");
        }

        return null;
    }
}
