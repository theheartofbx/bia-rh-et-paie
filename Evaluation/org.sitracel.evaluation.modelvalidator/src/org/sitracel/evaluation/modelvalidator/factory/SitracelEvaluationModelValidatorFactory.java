package org.sitracel.evaluation.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrille;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleLigne;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleFormule;
import org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEval;
import org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEvalLigne;

public class SitracelEvaluationModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        switch (className) {
            // --- Grille ---
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrille":
                return new SitracelModelValidatorGrille();
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleLigne":
                return new SitracelModelValidatorGrilleLigne();
            case "org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleFormule":
                return new SitracelModelValidatorGrilleFormule();
            // --- Évaluation ---
            case "org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEval":
                return new SitracelModelValidatorEval();
            case "org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEvalLigne":
                return new SitracelModelValidatorEvalLigne();
            default:
                return null;
        }
    }
}
