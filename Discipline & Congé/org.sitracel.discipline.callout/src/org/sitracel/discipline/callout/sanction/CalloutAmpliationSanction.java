package org.sitracel.discipline.callout.sanction;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.callout.sanction.controller.CalloutControllerDiscipline;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.model.MHRAmpliation;

public class CalloutAmpliationSanction implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
				Timestamp dateEmission = (Timestamp) mTab.getValue(MHRPunishment.COLUMNNAME_Date_Emission);
				String ampliation  = (String) mTab.getValue(MHRPunishment.COLUMNNAME_Initial);
				Integer idAmpliation = (Integer) mTab.getValue(MHRPunishment.COLUMNNAME_HR_Ampliation_ID);
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
						ampliation = CalloutControllerDiscipline.getAmpliation(ampliation, initial.getAbreviation(), annee);
						mTab.setValue(MHRPunishment.COLUMNNAME_Initial, ampliation);
					}
				}
		return null;
	}

}
