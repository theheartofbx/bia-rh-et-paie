package org.sitracel.paie.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.paie.callout.CalloutMajPeriodeReference;
import org.sitracel.paie.model.MHRAttribute;

public class SitracelPaieCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();
		
		if(tableName.equalsIgnoreCase(MHRAttribute.Table_Name) && (columnName.equalsIgnoreCase(MHRAttribute.COLUMNNAME_HR_Holiday_ID))) {
			list.add(new CalloutMajPeriodeReference());
		}		

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
