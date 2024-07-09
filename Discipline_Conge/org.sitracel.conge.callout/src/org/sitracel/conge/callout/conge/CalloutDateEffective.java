package org.sitracel.conge.callout.conge;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.conge.model.MHRHoliday;

public class CalloutDateEffective implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				Timestamp dateDebut = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective);
				Timestamp dateFin = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective);
				if(dateDebut!=null && dateFin!=null) {
					if(dateDebut.after(dateFin)) {
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective, null);
						mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective, null);

						mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "Date de Début effective ne peut pas être après Date de Fin effective !");
						mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
					}
					else {
						mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
						mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
					}
				}
				else {
					mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte, "");
					mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "N");
				}
		return null;
	}

}
