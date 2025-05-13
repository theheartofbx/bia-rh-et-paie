package org.sitracel.paie.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.controller.GeneralController;
import org.sitracel.paie.model.MHRPeriodeSalariale;

public class CalloutMajPeriodeSalariale implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Timestamp dateDebut = (Timestamp)mTab.getValue(MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut);
		Timestamp dateFin = (Timestamp)mTab.getValue(MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut);
		if(dateDebut!=null && dateFin!=null) {
			if(dateDebut.after(dateFin)) {
				mTab.setValue(MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut,null);
				mTab.setValue(MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut,null);
			}
			else {
				int nbJourTravaille = GeneralController.getNombreJour(dateDebut, dateFin);
				mTab.setValue(MHRPeriodeSalariale.COLUMNNAME_Nombre_Jour_Salarial, nbJourTravaille);
			}
		}
		return null;
	}

}
