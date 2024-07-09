package org.sitracel.conge.callout.conge;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.callout.bean.BeanConge;
import org.sitracel.conge.callout.conge.controller.CalloutSqlControllerConge;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class CalloutTypeConge implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee, null);
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee, null);
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee, null);
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee, null);
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
		mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);
		Integer idConge = (Integer)mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID);
		MHRTypeConge typeConge = null;
		if(idConge!=null) {
			typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
		}
		if(typeConge!=null) {
			if(typeConge.isCongeAnnuel()) {
				Integer idEmploye = (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID);
				BeanConge bic;
			
				if(idEmploye!=null) {
					bic = CalloutSqlControllerConge.getInfoConge(idEmploye,typeConge.getHR_Type_Conge_ID(), null);
					if(bic != null) {
						mTab.setValue(MHRHoliday.COLUMNNAME_IsCongeAnnuel, typeConge.isCongeAnnuel());
						mTab.setValue(MHRHoliday.COLUMNNAME_IsDefinir_Periode_Conge, typeConge.isDefinir_Periode_Conge());
						mTab.setValue(MHRHoliday.COLUMNNAME_Sex, typeConge.getGenre_Employe());
						
						
						mTab.setValue(MHRHoliday.COLUMNNAME_Enfant_Conge, bic.getNombreEnfantPetit());
						mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Total, bic.getNombreJourCongeTotal());
						mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Deja_Utilise, bic.getNombreJourCongeUtilise());
						mTab.setValue(MHRHoliday.COLUMNNAME_Annee_Anciennete, bic.getAnneeAnciennete());
						mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_A_Compenser, bic.getDetteConge());
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Dernier_Conge, bic.getDateFindernierConge());
						/*mTab.setValue(MHRHoliday.COLUMNNAME_Dates_Absences, bic.getDateAbsence());
						mTab.setValue(MHRHoliday.COLUMNNAME_ID_Absences, bic.getIdAbsence());*/
					}
				}	
			}
		}
		
		return null;
	}

}
