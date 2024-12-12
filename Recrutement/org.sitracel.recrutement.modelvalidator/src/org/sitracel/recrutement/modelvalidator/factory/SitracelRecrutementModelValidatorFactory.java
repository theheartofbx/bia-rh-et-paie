package org.sitracel.recrutement.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.recrutement.modelvalidator.candidature.SitracelModelValidatorCandidature;
import org.sitracel.recrutement.modelvalidator.evaluationcandidature.SitracelModelValidatorEvaluationCandidature;
import org.sitracel.recrutement.modelvalidator.offreemploi.SitracelModelValidatorOffreEmploi;
import org.sitracel.recrutement.modelvalidator.sessionrecrutement.SitracelModelValidatorSessionRecrutement;
import org.sitracel.recrutement.modelvalidator.testevaluation.SitracelModelValidatorTestEvaluation;

public class SitracelRecrutementModelValidatorFactory implements IModelValidatorFactory{

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.recrutement.modelvalidator.offreemploi")) {
			return new SitracelModelValidatorOffreEmploi();
		}
		if(className.equals("org.sitracel.recrutement.modelvalidator.sessionrecrutement")) {
			return new SitracelModelValidatorSessionRecrutement();
		}
		if(className.equals("org.sitracel.recrutement.modelvalidator.testevaluation")) {
			return new SitracelModelValidatorTestEvaluation();
		}
		if(className.equals("org.sitracel.recrutement.modelvalidator.SitracelModelValidatorCandidature")) {
			return new SitracelModelValidatorCandidature();
		}
		if(className.equals("org.sitracel.recrutement.modelvalidator.evaluationcandidature.SitracelModelValidatorEvaluationCandidature")) {
			return new SitracelModelValidatorEvaluationCandidature();
		}
		return null;
	}

}
