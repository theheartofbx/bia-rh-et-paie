package org.sitracel.recrutement.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.recrutement.process.offreemploi.SitracelProcessRejeterOffreEmploi;
import org.sitracel.recrutement.process.offreemploi.SitracelProcessValiderOffreEmploi;
import org.sitracel.recrutement.process.testevaluation.SitracelProcessValiderTestEvaluation;
import org.sitracel.recrutement.sessionrecrutement.SitracelProcessRejeterSessionRecrutement;
import org.sitracel.recrutement.sessionrecrutement.SitracelProcessValiderSessionRecrutement;

public class SitracelRecrutementProcessFactory implements IProcessFactory{

	@Override
	public ProcessCall newProcessInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.recrutement.process.offreemploi.SitracelProcessValiderOffreEmploi")) {
			return new SitracelProcessValiderOffreEmploi();
		}
		if(className.equals("org.sitracel.recrutement.process.offreemploi.SitracelProcessRejeterOffreEmploi")) {
			return new SitracelProcessRejeterOffreEmploi();
		}
		if(className.equals("org.sitracel.recrutement.sessionrecrutement.SitracelProcessValiderSessionRecrutement")) {
			return new SitracelProcessValiderSessionRecrutement();
		}
		if(className.equals("org.sitracel.recrutement.sessionrecrutement.SitracelProcessRejeterSessionRecrutement")) {
			return new SitracelProcessRejeterSessionRecrutement();
		}
		if(className.equals("org.sitracel.recrutement.process.testevaluation.SitracelProcessValiderTestEvaluation")) {
			return new SitracelProcessValiderTestEvaluation();
		}
		return null;
	}

}
