package org.sitracel.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.callout.organigramme.CalloutOrganigrammeMessageDoublonPosteResponsable;

public class SitracelCalloutFactory implements IColumnCalloutFactory{

	@Override
	public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
		// TODO Auto-generated method stub
		List<IColumnCallout> list = new ArrayList<IColumnCallout>();

		if(tableName.equalsIgnoreCase("hr_organigramme") && (columnName.equalsIgnoreCase("poste_responsable_id"))) {
			list.add(new CalloutOrganigrammeMessageDoublonPosteResponsable());
		}

		return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
	}

}
