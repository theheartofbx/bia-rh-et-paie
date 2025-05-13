package org.sitracel.paie.process.salairebase;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.compiere.util.AdempiereUserError;
import org.sitracel.paie.process.controller.ProcessControllerPaie;

public class SitracelProcessCalculerPaie extends SvrProcess{
	private int bpartnerID;
	private int periodeSalarialeID;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		for (ProcessInfoParameter para : getParameter()) {
	        String name = para.getParameterName();
	        if ("C_BPartner_ID".equals(name)) {
	            bpartnerID = para.getParameterAsInt();
	        } else if ("HR_Periode_Salariale_ID".equals(name)) {
	            periodeSalarialeID = para.getParameterAsInt();
	        }
	    }
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		if (bpartnerID <= 0 || periodeSalarialeID <=0) {
	        throw new AdempiereUserError("Aucune période salariale sélectionnée !");
	    }
		ProcessControllerPaie.calculerPaie(bpartnerID, periodeSalarialeID);
		return null;
	}

}
