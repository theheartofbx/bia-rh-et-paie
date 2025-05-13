package org.sitracel.paie.callout;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.sitracel.model.MCBPartner;
import org.sitracel.paie.model.MHRGestionPresence;

public class CalloutPeriodeNonTravaille implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		CLogger log = CLogger.getCLogger(PO.class);
		Integer bpartnerID = (Integer)mTab.getValue(MHRGestionPresence.COLUMNNAME_C_BPartner_ID);
		Timestamp dateDebut = (Timestamp)mTab.getValue(MHRGestionPresence.COLUMNNAME_Date_Debut);
		Timestamp dateFin = (Timestamp)mTab.getValue(MHRGestionPresence.COLUMNNAME_Date_Fin);
		log.warning("\nHolidayID : "+bpartnerID);
		MCBPartner bpartner = new MCBPartner(Env.getCtx(), bpartnerID, null);
		if(bpartner!=null) {
			if(dateDebut!=null && dateFin!=null) {
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Suspension, CalloutControllerPaie.getNombreJourSuspensionValideByname(bpartnerID, dateDebut, dateFin));
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Annuel, CalloutControllerPaie.getNombreJourCongeValideByname(bpartnerID, "Annuel", dateDebut, dateFin));
				if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Femme)) {
					mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Maternite, CalloutControllerPaie.getNombreJourCongeValideByname(bpartnerID, "Maternité", dateDebut, dateFin));
				}
				if(bpartner.getSex().equalsIgnoreCase(MCBPartner.SEX_Homme)) {
					mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Paternite, CalloutControllerPaie.getNombreJourCongeValideByname(bpartnerID, "Paternité", dateDebut, dateFin));
				}
			}
			else {
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Suspension, null);
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Annuel, null);
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Maternite, null);
				mTab.setValue(MHRGestionPresence.COLUMNNAME_Nombre_Jour_Conge_Paternite, null);
			}
		}
		return null;
	}

}
