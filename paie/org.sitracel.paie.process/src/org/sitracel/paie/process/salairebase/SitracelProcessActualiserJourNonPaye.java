package org.sitracel.paie.process.salairebase;

import org.compiere.process.SvrProcess;
import org.sitracel.paie.process.controller.ProcessControllerPaie;

public class SitracelProcessActualiserJourNonPaye extends SvrProcess {

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerPaie.mAJJNonPaie(getRecord_ID());
		return null;
	}

}
