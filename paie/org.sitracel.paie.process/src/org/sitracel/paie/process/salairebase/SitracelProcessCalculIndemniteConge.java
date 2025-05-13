package org.sitracel.paie.process.salairebase;

import org.compiere.process.ProcessInfoParameter;
import org.compiere.process.SvrProcess;
import org.sitracel.paie.process.controller.ProcessControllerPaie;

public class SitracelProcessCalculIndemniteConge extends SvrProcess{
	private int bpartnerID;
	private int holidayID;

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		for (ProcessInfoParameter para : getParameter()) {
	        String name = para.getParameterName();
	        if ("C_BPartner_ID".equals(name)) {
	            bpartnerID = para.getParameterAsInt();
	        } else if ("HR_Holiday_ID".equals(name)) {
	            holidayID = para.getParameterAsInt();
	        }
	    }
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerPaie.calculerIndemniteConge(bpartnerID, holidayID);
		return null;
	}

}
