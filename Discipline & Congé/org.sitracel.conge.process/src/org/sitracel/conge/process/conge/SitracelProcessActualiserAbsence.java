package org.sitracel.conge.process.conge;

import org.compiere.process.SvrProcess;
import org.sitracel.conge.process.conge.controller.ProcessControllerConge;

public class SitracelProcessActualiserAbsence extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerConge.updateAbsenceConge(getRecord_ID());
		return null;
	}

}
