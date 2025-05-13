package org.sitracel.discipline.callout.sanction;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.callout.sanction.controller.CalloutSqlControllerDiscipline;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;

public class CalloutTypeSanction implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		mTab.setValue(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID, null);
		mTab.setValue(MHRPunishment.COLUMNNAME_Date_Debut_Application, null);
		mTab.setValue(MHRPunishment.COLUMNNAME_Date_Fin_Application, null);
		mTab.setValue(MHRPunishment.COLUMNNAME_IsPeriodSuspension, false);
		mTab.setValue(MHRPunishment.COLUMNNAME_IsLicenciement, false);
		Integer emissionSanctionID= (Integer) mTab.getValue(MHRPunishment.COLUMNNAME_Emission_Sanction_ID);
		Integer posteEmployeID = (Integer)mTab.getValue(MHRPunishment.COLUMNNAME_Poste_Employe_ID);
		Integer posteEmetteurID = (Integer)mTab.getValue(MHRPunishment.COLUMNNAME_Emis_Par_Poste_ID);
		if(emissionSanctionID!=null && posteEmployeID!=null && posteEmetteurID!=null) {
			MHRSanctionAutorisation sanctionAutorisation = new MHRSanctionAutorisation(Env.getCtx(), emissionSanctionID, null);
			if(sanctionAutorisation!=null) {
				MHRTypeSanction typeSanction = new MHRTypeSanction(Env.getCtx(), sanctionAutorisation.getHR_TypeSanction_ID(), null);
				Integer AutorisationSanctionID = CalloutSqlControllerDiscipline.getAutorisationSanctionID(sanctionAutorisation.getHR_TypeSanction_ID(), 
						posteEmployeID, posteEmetteurID, null);
				MHRSanctionAutorisation autorisationSanction = new MHRSanctionAutorisation(Env.getCtx(), AutorisationSanctionID, null);
				if(typeSanction!=null) {
					if(autorisationSanction!=null) {
						mTab.setValue(MHRPunishment.COLUMNNAME_IsApprobation_Createur, autorisationSanction.isApprobation());
						mTab.setValue(MHRPunishment.COLUMNNAME_IsValidation_Createur, autorisationSanction.isValidation());
					}
					if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(MHRTypeSanction.INCIDENCE_SANCTION_ID_PériodeDeSuspension)) {
						mTab.setValue(MHRPunishment.COLUMNNAME_IsPeriodSuspension, true);
						mTab.setValue(MHRPunishment.COLUMNNAME_IsLicenciement, false);
					}
					else if(typeSanction.getIncidence_Sanction_ID().equalsIgnoreCase(MHRTypeSanction.INCIDENCE_SANCTION_ID_Licenciement)) {
						mTab.setValue(MHRPunishment.COLUMNNAME_IsPeriodSuspension, false);
						mTab.setValue(MHRPunishment.COLUMNNAME_IsLicenciement, true);
					}
					else {
						mTab.setValue(MHRPunishment.COLUMNNAME_IsPeriodSuspension, false);
						mTab.setValue(MHRPunishment.COLUMNNAME_IsLicenciement, false);
					}
				}
			}
		}
		return null;
	}

}
