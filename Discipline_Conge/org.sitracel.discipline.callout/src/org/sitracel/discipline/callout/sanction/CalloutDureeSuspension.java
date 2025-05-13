package org.sitracel.discipline.callout.sanction;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralController;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;

public class CalloutDureeSuspension implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp dateDebutApplication = (Timestamp) mTab.getValue(MHRPunishment.COLUMNNAME_Date_Debut_Application);
		
		if(dateDebutApplication!=null) {			
			mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, false);
			mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "");
			
			Integer dureeSuspensionID = (Integer)mTab.getValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID);
			MHRDureeSanction dureeSuspension = null;
			if(dureeSuspensionID!=null) {
				dureeSuspension = new MHRDureeSanction(Env.getCtx(), dureeSuspensionID, null);
			}
			if(dureeSuspension!=null) {
				Timestamp dateFinApplication = GeneralController.ajouterNombreJour(dateDebutApplication, dureeSuspension.getNombre_De_Jour());
				Integer bpartnerID = (Integer)mTab.getValue(MHRPunishment.COLUMNNAME_C_BPartner_ID);
				if(bpartnerID!=null) {
					if(!GeneralSqlController.chevaucheSuspensionNonRejete(bpartnerID, dateDebutApplication, dateFinApplication, null)) {
						if(!GeneralSqlController.chevaucheAnyCongeNonRejete(bpartnerID, dateDebutApplication, dateFinApplication, null)) {
							if(!GeneralSqlController.isPeriodeAbsence(bpartnerID, dateDebutApplication, dateFinApplication, null)) {
								mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
								mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
								mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
								mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "un jour d'absence a été enregistrée durant cette période");
							}
							else {
								mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
								mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
								mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
								mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "un jour d'absence a été enregistrée durant cette période");
							}
						}
						else {
							mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
							mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
							mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
							mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "une période de congé a été enregistrée durant cette période");
						}
					}
					mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
					mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
					mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
					mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "une autre période de suspension a été enregistrée durant cette période");
				}				
				else {
					mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
					mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
					mTab.setValue(MHRPunishment.COLUMNNAME_IsMessageAlerteDisplayed, true);
					mTab.setValue(MHRPunishment.COLUMNNAME_Message_Alerte, "veuillez renseignez des données corrects svp");
				}
			}
		}
		return null;
	}

}
