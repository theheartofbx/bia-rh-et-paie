package org.sitracel.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.callout.organigramme.CalloutOrganigrammeMessageDoublonPosteResponsable;
import org.sitracel.model.MHROrganigramme;

public class SitracelCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();

		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name) && (columnName.equalsIgnoreCase(MHROrganigramme.COLUMNNAME_Poste_Responsable_ID))) {
			list.add(new CalloutOrganigrammeMessageDoublonPosteResponsable());
		}

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
