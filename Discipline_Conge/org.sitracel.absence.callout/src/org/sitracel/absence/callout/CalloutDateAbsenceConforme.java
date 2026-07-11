package org.sitracel.absence.callout;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.conge.HRCongeRepository;
import org.sitracel.absence.model.MHRAbsence;
import org.sitracel.conge.model.MHRPublicHoliday;

/**
 * Callout - validation de la date d'absence.
 *
 * Affichage via le mecanisme standard iDempiere (return message = popup).
 */
public class CalloutDateAbsenceConforme implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        Timestamp date     = (Timestamp) mTab.getValue(MHRAbsence.COLUMNNAME_Date_Absence);
        Integer bpartnerId = (Integer)   mTab.getValue(MHRAbsence.COLUMNNAME_C_BPartner_ID);

        if (date == null || bpartnerId == null) return null;

        String dateFormatee = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date);

        // Absence deja enregistree ce jour
        if (HRCongeRepository.isAbsenceExist(date, bpartnerId, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            return "Une absence a deja ete enregistree le " + dateFormatee + ".";
        }

        // Dimanche
        if (MHRPublicHoliday.isDimanche(date)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            return "Le " + dateFormatee + " est un dimanche.";
        }

        // Jour ferie
        String nomFerie = MHRPublicHoliday.getNomJourFerie(date, null);
        if (nomFerie != null) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            return "Le " + dateFormatee + " est un jour ferie (" + nomFerie + ").";
        }

        // Jour de conge de l'employe
        if (HRCongeRepository.isJourCongesNonRejeteByNameConge(
                bpartnerId, "Annuel", date, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            return "Le " + dateFormatee + " fait partie des jours de conge de l'employe.";
        }

        // Jour de suspension de l'employe
        if (HRCongeRepository.isJourSuspensionNonRejete(bpartnerId, date, null)) {
            mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
            return "Le " + dateFormatee
                + " est compris dans une periode de suspension de l'employe.";
        }

        return null;
    }
}
