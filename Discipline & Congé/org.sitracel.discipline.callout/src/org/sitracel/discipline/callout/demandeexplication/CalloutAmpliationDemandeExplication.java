package org.sitracel.discipline.callout.demandeexplication;


import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.callout.demandeexplication.controller.CalloutControllerDemandeExplication;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.model.MHRAmpliation;

public class CalloutAmpliationDemandeExplication implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp dateEmission = (Timestamp) mTab.getValue(MHRDemandeExplication.COLUMNNAME_Date_Emission);
		String ampliation  = (String) mTab.getValue(MHRDemandeExplication.COLUMNNAME_Initial);
		Integer idAmpliation = (Integer) mTab.getValue(MHRDemandeExplication.COLUMNNAME_HR_Ampliation_ID);
		String annee = "";
		if(idAmpliation!=null) {
			if(ampliation==null) {
				ampliation = "";
			}
			MHRAmpliation initial = new MHRAmpliation(Env.getCtx(), idAmpliation, null);
			if(initial!=null) {
				if(dateEmission!=null) {
					StringBuilder stringBuilder = new StringBuilder();
					stringBuilder.append(dateEmission.getYear());
					stringBuilder.append("");
					annee = stringBuilder.toString();
				}
				ampliation = CalloutControllerDemandeExplication.getAmpliation(ampliation, initial.getAbreviation(), annee);
				mTab.setValue(MHRDemandeExplication.COLUMNNAME_Initial, ampliation);
			}
		}
		return null;
	}

}
