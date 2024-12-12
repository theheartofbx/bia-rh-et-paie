package org.sitracel.recrutement.process.offreemploi;

import org.compiere.process.SvrProcess;
import org.sitracel.recrutement.process.controller.ProcessControllerRecrutement;

public class SitracelProcessValiderOffreEmploi extends SvrProcess{

	@Override
	protected void prepare() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected String doIt() throws Exception {
		// TODO Auto-generated method stub
		ProcessControllerRecrutement.validerOffreEmploi(getRecord_ID(), getAD_User_ID());
		return null;
	}

}
