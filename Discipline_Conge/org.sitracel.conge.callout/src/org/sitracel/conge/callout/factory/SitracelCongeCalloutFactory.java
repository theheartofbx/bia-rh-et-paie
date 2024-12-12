package org.sitracel.conge.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.conge.callout.absence.CalloutDateAbsenceConforme;
import org.sitracel.conge.callout.conge.CalloutAmpliationConge;
import org.sitracel.conge.callout.conge.CalloutCongeAnnuel;
import org.sitracel.conge.callout.conge.CalloutCongeMaternite;
import org.sitracel.conge.callout.conge.CalloutDateDebutAjustee;
import org.sitracel.conge.callout.conge.CalloutDateEffective;
import org.sitracel.conge.callout.conge.CalloutDateFinAjustee;
import org.sitracel.conge.callout.conge.CalloutTypeConge;
import org.sitracel.conge.callout.conge.CalloutTypeCongeEmploye;
import org.sitracel.conge.callout.conge.CalloutTypeCongeResponsable;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRHoliday;

public class SitracelCongeCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
				
		
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Probable_Accouchement)) {
			list.add(new CalloutCongeMaternite());
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Emission_Conge_ID)) {
			list.add(new CalloutTypeConge());
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Emission_Conge_Employe_ID)) {
			list.add(new CalloutTypeCongeEmploye());
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Emission_Conge_Responsable_ID)) {
			list.add(new CalloutTypeCongeResponsable());
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && (columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee)
				|| columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee))) {
			list.add(new CalloutCongeAnnuel());
		}		
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && (columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee))) { 
		  list.add(new CalloutDateDebutAjustee()); 
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && (columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee))) { 
		  list.add(new CalloutDateFinAjustee()); 
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && (columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Debut_Effective) 
				|| columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_Date_Fin_Effective))) { 
			list.add(new CalloutDateEffective()); 
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name) && (columnName.equalsIgnoreCase(MHRHoliday.COLUMNNAME_HR_Ampliation_ID))) {
			list.add(new CalloutAmpliationConge());
		}
		

		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name) && (columnName.equalsIgnoreCase(MHRAbsence.COLUMNNAME_Date_Absence))) { 
			  list.add(new CalloutDateAbsenceConforme()); 
		} 
		

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
