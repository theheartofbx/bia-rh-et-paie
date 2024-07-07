package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;
import org.sitracel.conge.process.conge.controller.ProcessControllerConge;

public class SitracelProcessNotifierConge extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerConge.notifierTraitementConge(getRecord_ID());
		return null;
	}

}
