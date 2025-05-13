package org.sitracel.paie.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.paie.callout.CalloutMajPeriodeSalariale;
import org.sitracel.paie.callout.CalloutMajRetenueSalarialeFinPeriode;
import org.sitracel.paie.callout.CalloutMajRetenueSalarialeMensualite;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRRetenueSalariale;

public class SitracelPaieCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
			
		if(tableName.equalsIgnoreCase(MHRPeriodeSalariale.Table_Name) && (columnName.equalsIgnoreCase(MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut)
				|| columnName.equalsIgnoreCase(MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut))) {
			list.add(new CalloutMajPeriodeSalariale());
		}	
		if(tableName.equalsIgnoreCase(MHRRetenueSalariale.Table_Name) && (columnName.equalsIgnoreCase(MHRRetenueSalariale.COLUMNNAME_Montant_Total_Retenue)
				|| columnName.equalsIgnoreCase(MHRRetenueSalariale.COLUMNNAME_Nombre_Mensualite)
				|| columnName.equalsIgnoreCase(MHRRetenueSalariale.COLUMNNAME_Acompte))) {
			list.add(new CalloutMajRetenueSalarialeMensualite());
		}
		if(tableName.equalsIgnoreCase(MHRRetenueSalariale.Table_Name) && (columnName.equalsIgnoreCase(MHRRetenueSalariale.COLUMNNAME_Debut_Prelevement_ID)
				|| columnName.equalsIgnoreCase(MHRRetenueSalariale.COLUMNNAME_Nombre_Mensualite))) {
			list.add(new CalloutMajRetenueSalarialeFinPeriode());
		}				

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
