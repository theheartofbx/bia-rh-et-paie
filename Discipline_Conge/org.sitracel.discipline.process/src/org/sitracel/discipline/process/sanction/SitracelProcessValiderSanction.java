package org.sitracel.discipline.process.sanction;

import org.compiere.process.SvrProcess;
import org.sitracel.discipline.process.sanction.controller.ProcessControllerDiscipline;

public class SitracelProcessValiderSanction extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerDiscipline.validerSanction(getRecord_ID(), getAD_User_ID());	
		return null;
	}

}
