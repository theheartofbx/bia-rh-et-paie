package org.sitracel.evaluation.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.evaluation.process.ValiderGrille;
import org.sitracel.evaluation.process.AnnulerValidationGrille;
import org.sitracel.evaluation.process.GenererEvaluation;
import org.sitracel.evaluation.process.SoumettreEmploye;
import org.sitracel.evaluation.process.SoumettreN1;
import org.sitracel.evaluation.process.ValiderEvaluation;
import org.sitracel.evaluation.process.RenvoyerVersN1;
import org.sitracel.evaluation.process.RenvoyerVersEmploye;

public class SitracelEvaluationProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        switch (className) {
            case "org.sitracel.evaluation.process.ValiderGrille":
                return new ValiderGrille();
            case "org.sitracel.evaluation.process.AnnulerValidationGrille":
                return new AnnulerValidationGrille();
            case "org.sitracel.evaluation.process.GenererEvaluation":
                return new GenererEvaluation();
            case "org.sitracel.evaluation.process.SoumettreEmploye":
                return new SoumettreEmploye();
            case "org.sitracel.evaluation.process.SoumettreN1":
                return new SoumettreN1();
            case "org.sitracel.evaluation.process.ValiderEvaluation":
                return new ValiderEvaluation();
            case "org.sitracel.evaluation.process.RenvoyerVersN1":
                return new RenvoyerVersN1();
            case "org.sitracel.evaluation.process.RenvoyerVersEmploye":
                return new RenvoyerVersEmploye();
            default:
                return null;
        }
    }
}
