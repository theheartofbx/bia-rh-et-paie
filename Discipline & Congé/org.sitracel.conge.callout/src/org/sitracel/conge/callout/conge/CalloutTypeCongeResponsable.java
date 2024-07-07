package org.sitracel.conge.callout.conge;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class CalloutTypeCongeResponsable implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Integer idConge = (Integer)mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_Responsable_ID);
		MHRAutorisationConge autorisationConge = new MHRAutorisationConge(Env.getCtx(), idConge, null);
		MHRTypeConge typeConge = null;
		if(autorisationConge!=null) {
			typeConge = new MHRTypeConge(Env.getCtx(), autorisationConge.getHR_Type_Conge_ID(), null);
		}
		if(typeConge!=null) {
			mTab.setValue(MHRHoliday.COLUMNNAME_IsApprobation_Createur, autorisationConge.isApprobation());
			mTab.setValue(MHRHoliday.COLUMNNAME_IsValidation_Createur, autorisationConge.isValidation());
			mTab.setValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID, typeConge.getHR_Type_Conge_ID());
		}
		return null;
	}

}
