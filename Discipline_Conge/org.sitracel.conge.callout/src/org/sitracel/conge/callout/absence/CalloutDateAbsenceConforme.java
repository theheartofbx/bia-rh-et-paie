package org.sitracel.conge.callout.absence;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.conge.callout.absence.controller.CalloutSqlControllerAbsence;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.general.controller.GeneralController;

public class CalloutDateAbsenceConforme implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, false);
		mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "");
		Timestamp date = (Timestamp) mTab.getValue(MHRAbsence.COLUMNNAME_Date_Absence);
		Integer cBPartnerID = (Integer) mTab.getValue(MHRAbsence.COLUMNNAME_C_BPartner_ID);
		if(CalloutSqlControllerAbsence.isAbsenceExist(date, cBPartnerID, null)) {
			mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
			mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
			mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "une absence a déjà été enregistrée le "
			+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date));
		}
		else if(GeneralController.isJourFerie(date)) {
			mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
			mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
			mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "le "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date)
					+" est soit un dimanche, soit un jour férié");
		}
		else if(GeneralController.isCongeAnnuel(cBPartnerID, date)) {
			mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
			mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
			mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "le "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date)
					+" fait partie d'un des jours de congé de l'employé");
		}
		else if(GeneralController.isJourSuspension(cBPartnerID, date)) {
			mTab.setValue(MHRAbsence.COLUMNNAME_Date_Absence, null);
			mTab.setValue(MHRAbsence.COLUMNNAME_IsMessageAlerteDisplayed, true);
			mTab.setValue(MHRAbsence.COLUMNNAME_Message_Alerte, "le "+new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(date)
					+" est compris dans une période de suspension de l'employé");
		}
		return null;
	}

}
