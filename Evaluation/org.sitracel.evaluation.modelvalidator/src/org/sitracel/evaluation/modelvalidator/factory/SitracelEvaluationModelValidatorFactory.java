package org.sitracel.evaluation.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrille;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleLigne;
import org.sitracel.evaluation.modelvalidator.grille.SitracelModelValidatorGrilleFormule;
import org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEval;
import org.sitracel.evaluation.modelvalidator.eval.SitracelModelValidatorEvalLigne;
import org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorAppreciation;
import org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorObjectif;
import org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorPeriode;

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
            // --- Référence ---
            case "org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorAppreciation":
                return new SitracelModelValidatorAppreciation();
            case "org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorObjectif":
                return new SitracelModelValidatorObjectif();
            case "org.sitracel.evaluation.modelvalidator.reference.SitracelModelValidatorPeriode":
                return new SitracelModelValidatorPeriode();
            default:
                return null;
        }
    }
}
