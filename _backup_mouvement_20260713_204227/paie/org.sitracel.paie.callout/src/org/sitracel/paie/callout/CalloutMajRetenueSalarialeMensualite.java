package org.sitracel.paie.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.paie.model.MHRMouvementPaie;

public class CalloutMajRetenueSalarialeMensualite implements IColumnCallout{

	@Override
	public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
		// TODO Auto-generated method stub
		BigDecimal montantTotalRenue = (BigDecimal)mTab.getValue(MHRMouvementPaie.COLUMNNAME_Montant_Total);
		BigDecimal acompte = (BigDecimal)mTab.getValue(MHRMouvementPaie.COLUMNNAME_Acompte);
		Integer nombreMensualite = (Integer)mTab.getValue(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite);
		if(nombreMensualite!=null && montantTotalRenue!=null && nombreMensualite>0) {
			if(acompte!=null) {
				montantTotalRenue = montantTotalRenue.subtract(acompte);
			}
			BigDecimal mensualite = montantTotalRenue.divideToIntegralValue(BigDecimal.valueOf(nombreMensualite));
			BigDecimal derniereMensualite = BigDecimal.ZERO;
			if(nombreMensualite==1) {
				derniereMensualite = mensualite;
			}
			else {
				derniereMensualite = montantTotalRenue.subtract((mensualite).multiply(BigDecimal.valueOf(nombreMensualite-1)));
			}
			mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Mensualite, mensualite);
			mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Derniere_Mensualite, derniereMensualite);
		}
		else {

			mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Mensualite, null);
			mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Derniere_Mensualite, null);
		}
		return null;
	}

}
