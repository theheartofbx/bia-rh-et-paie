package org.sitracel.mission.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.mission.callout.mission.SitracelMissionCalloutMission;
import org.sitracel.mission.callout.mission.missionvalidation.SitracelMissionCalloutMIssionValidation;
import org.sitracel.mission.callout.missionaffectation.SitracelMissionCalloutMissionAffectation;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.mission.model.MHRMissionValidation;

public class SitracelMissionCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();

		
		if(tableName.equalsIgnoreCase(MHRMission.Table_Name) 
				&& (columnName.equalsIgnoreCase(MHRMission.COLUMNNAME_Date_Debut) || columnName.equalsIgnoreCase(MHRMission.COLUMNNAME_Date_Fin))) {
			list.add(new SitracelMissionCalloutMission());
		}	

		if(tableName.equalsIgnoreCase(MHRMissionValidation.Table_Name) 
				&& (columnName.equalsIgnoreCase(MHRMissionValidation.COLUMNNAME_IsValidee) || columnName.equalsIgnoreCase(MHRMissionValidation.COLUMNNAME_IsRejetee))) {
			list.add(new SitracelMissionCalloutMIssionValidation());
		}	

		if(tableName.equalsIgnoreCase(MHRMissionAffectation.Table_Name) 
				&& (columnName.equalsIgnoreCase(MHRMissionAffectation.COLUMNNAME_Date_Debut) || columnName.equalsIgnoreCase(MHRMissionAffectation.COLUMNNAME_Date_Fin))) {
			list.add(new SitracelMissionCalloutMissionAffectation());
		}	
		
		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
