package org.sitracel.evaluation.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.evaluation.process.ValiderGrille;
import org.sitracel.evaluation.process.AnnulerValidationGrille;

public class SitracelEvaluationProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        switch (className) {
            case "org.sitracel.evaluation.process.ValiderGrille":
                return new ValiderGrille();
            case "org.sitracel.evaluation.process.AnnulerValidationGrille":
                return new AnnulerValidationGrille();
            default:
                return null;
        }
    }
}
