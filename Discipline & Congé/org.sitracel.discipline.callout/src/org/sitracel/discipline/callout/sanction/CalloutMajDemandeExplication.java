package org.sitracel.discipline.callout.sanction;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;

public class CalloutMajDemandeExplication implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Integer idDemandeExplication = (Integer)mTab.getValue(MHRPunishment.COLUMNNAME_Demande_Explication_ID);
		if(idDemandeExplication!=null) {
			MHRDemandeExplication demandeExplication = new MHRDemandeExplication(Env.getCtx(), idDemandeExplication, null);
			mTab.setValue(MHRPunishment.COLUMNNAME_Motif_Demande_Explication, demandeExplication.getMotif_Demande_Explication());
			mTab.setValue(MHRPunishment.COLUMNNAME_Reponse_Demande_Explication, demandeExplication.getReponse_Demande_Explication());
			mTab.setValue(MHRPunishment.COLUMNNAME_Date_DE, demandeExplication.getDate_Emission());
			mTab.setValue(MHRPunishment.COLUMNNAME_Date_Reponse_DE, demandeExplication.getDate_Reponse());
			mTab.setValue(MHRPunishment.COLUMNNAME_Delai_Reponse_DE_ID, demandeExplication.getHR_Delai_Reponse_ID());
		}
		return null;
	}

}
