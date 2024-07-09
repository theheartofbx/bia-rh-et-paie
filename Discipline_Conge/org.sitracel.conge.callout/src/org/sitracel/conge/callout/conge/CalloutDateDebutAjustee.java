package org.sitracel.conge.callout.conge;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class CalloutDateDebutAjustee implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp dateDebut = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee);
		Timestamp dateFin = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee);
		if(dateDebut!=null) {
			/*
			boolean ajustementAuto = false;
			if(mTab.getValue(MHRHoliday.COLUMNNAME_Ajustement_Auto) instanceof String) {
				ajustementAuto = ((String)mTab.getValue(MHRHoliday.COLUMNNAME_Ajustement_Auto)).equals("Y");
			}
			if(mTab.getValue(MHRHoliday.COLUMNNAME_Ajustement_Auto) instanceof Boolean) {
				ajustementAuto = (Boolean)mTab.getValue(MHRHoliday.COLUMNNAME_Ajustement_Auto);
			}
			if(ajustementAuto) {
				Timestamp dateDebuts = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee);
				Timestamp dateFins = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee);
				Integer nbJour = CalloutController.getNombreJourConge(dateDebuts, dateFins);
				if(nbJour!=null) {
					Timestamp dateFinn = CalloutController.ajouterNombreJour(dateDebut, nbJour);
					mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, dateFinn);
					mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, dateFinn);
				}
			}
			else*/
			if(dateFin!=null) {
				if(dateDebut.after(dateFin)) {
					mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
					mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);

					mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "Date de Début ajustée ne peut pas être après Date de Fin ajustée !");
					mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
				}
				else {
					mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
					mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
				}
			}
			else {
				mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
				mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
			}			
		}
		Integer idConge = (Integer)mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID);
		if(idConge!=null) {
			MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
			if(typeConge!=null) {
				if(typeConge.isCongeAnnuel()) {
					dateDebut = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee);
					dateFin = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee);
					if(dateDebut!=null) {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, dateDebut);
					}
					else {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
					}
					if(dateFin!=null) {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, dateFin);
					}
					else {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
					}
				}
			}
		}
		return null;
	}

}
