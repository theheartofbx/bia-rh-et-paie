package org.sitracel.paie.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.paie.model.MHRPeriodeSalariale;
import org.sitracel.paie.model.MHRMouvementPaie;

public class CalloutMajRetenueSalarialeFinPeriode implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		Integer periodeDebutID = (Integer) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Debut_Prelevement_ID);
		Integer nombreMensualite = (Integer) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite);
		if(nombreMensualite!=null && periodeDebutID!=null) {
			MHRPeriodeSalariale periodeDebut = new MHRPeriodeSalariale(Env.getCtx(), periodeDebutID, null);
			if(periodeDebut!=null) {
				MHRPeriodeSalariale periodeFin = GeneralSqlController.getPeriodeSalarialeFinRetenue(periodeDebut.getDate_Debut_Defaut(), nombreMensualite, null);
				if(periodeFin!=null) {
					mTab.setValue(MHRMouvementPaie.COLUMNNAME_Fin_Prelevement_ID, periodeFin.getHR_Periode_Salariale_ID());
					mTab.setValue(MHRMouvementPaie.COLUMNNAME_Date_Debut, periodeDebut.getDate_Debut_Defaut());
				}
				else {
					mTab.setValue(MHRMouvementPaie.COLUMNNAME_Fin_Prelevement_ID, null);
					mTab.setValue(MHRMouvementPaie.COLUMNNAME_Date_Debut, null);
				}
			}
		}
		return null;
	}

}
