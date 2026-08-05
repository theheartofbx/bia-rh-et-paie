package org.sitracel.recrutement.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.recrutement.modelvalidator.candidature.SitracelModelValidatorCandidature;
import org.sitracel.recrutement.modelvalidator.critereevaluation.SitracelModelValidatorCritereEvaluation;
import org.sitracel.recrutement.modelvalidator.evaluationcandidature.SitracelModelValidatorEvaluationCandidature;
import org.sitracel.recrutement.modelvalidator.offreemploi.SitracelModelValidatorOffreEmploi;
import org.sitracel.recrutement.modelvalidator.sessionrecrutement.SitracelModelValidatorSessionRecrutement;
import org.sitracel.recrutement.modelvalidator.testevaluation.SitracelModelValidatorTestEvaluation;

/**
 * Factory ModelValidator du module Recrutement.
 * Session 18 : ajout SitracelModelValidatorCritereEvaluation.
 */
public class SitracelRecrutementModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals(SitracelModelValidatorOffreEmploi.class.getName()))
            return new SitracelModelValidatorOffreEmploi();
        if (className.equals(SitracelModelValidatorSessionRecrutement.class.getName()))
            return new SitracelModelValidatorSessionRecrutement();
        if (className.equals(SitracelModelValidatorTestEvaluation.class.getName()))
            return new SitracelModelValidatorTestEvaluation();
        if (className.equals(SitracelModelValidatorCandidature.class.getName()))
            return new SitracelModelValidatorCandidature();
        if (className.equals(SitracelModelValidatorEvaluationCandidature.class.getName()))
            return new SitracelModelValidatorEvaluationCandidature();
        if (className.equals(SitracelModelValidatorCritereEvaluation.class.getName()))
            return new SitracelModelValidatorCritereEvaluation();
        return null;
    }
}
