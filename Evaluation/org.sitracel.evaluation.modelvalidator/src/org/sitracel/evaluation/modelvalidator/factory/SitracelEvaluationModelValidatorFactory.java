package org.sitracel.evaluation.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrille;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleLigne;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleFormule;

public class SitracelEvaluationModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        switch (className) {
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrille":
                return new SitracelModelValidatorGrille();
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleLigne":
                return new SitracelModelValidatorGrilleLigne();
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleFormule":
                return new SitracelModelValidatorGrilleFormule();
            default:
                return null;
        }
    }
}
