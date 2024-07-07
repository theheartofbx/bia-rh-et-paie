package org.sitracel.discipline.process.sanction;

import org.compiere.process.SvrProcess;
import org.sitracel.discipline.process.sanction.controller.ProcessControllerDiscipline;

public class SitracelProcessNotifierSanction extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerDiscipline.notifierSanction(getRecord_ID());
		return null;
	}

}
