package org.sitracel.callout.organigramme;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.organigramme.model.MHROrganigramme;

public class CalloutOrganigrammeMessageDoublonPosteResponsable implements IColumnCallout{
	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		MHROrganigramme organigramme = GeneralSqlController.getOrganigramme(
				(Integer)mTab.getValue(MHROrganigramme.COLUMNNAME_Poste_ID), 
				(Integer)mTab.getValue(MHROrganigramme.COLUMNNAME_Poste_Responsable_ID), 
				null);
		if(organigramme!=null) {
			mTab.setValue(MHROrganigramme.COLUMNNAME_Poste_Responsable_ID, null);
			mTab.setValue(MHROrganigramme.COLUMNNAME_IsMessageAlerteDisplayed, true);
			mTab.setValue(MHROrganigramme.COLUMNNAME_Message_Alerte, "Ce poste de responsabilité a déjà été attribué,"
					+ " Vous ne pouvez pas en définir un nouveau, veuillez modifier l'ancien enregistrement ");
		}
		else {
			mTab.setValue(MHROrganigramme.COLUMNNAME_IsMessageAlerteDisplayed, false);
			mTab.setValue(MHROrganigramme.COLUMNNAME_Message_Alerte, "");
		}
		return null;
	}

}
