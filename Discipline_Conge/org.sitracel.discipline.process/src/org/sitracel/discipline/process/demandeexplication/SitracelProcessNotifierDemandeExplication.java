package org.sitracel.discipline.process.demandeexplication;

import org.compiere.process.SvrProcess;
import org.sitracel.discipline.process.demandeexplication.controller.ProcessControllerDemandeExplication;

public class SitracelProcessNotifierDemandeExplication extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerDemandeExplication.notifierDemandeExplication(getRecord_ID());
		return null;
	}

}
