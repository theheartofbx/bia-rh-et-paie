package org.sitracel.stage.process.factory;

import org.adempiere.base.IProcessFactory;
import org.compiere.process.ProcessCall;
import org.sitracel.stage.process.GenererPlanningStage;
import org.sitracel.stage.process.EvaluerStage;
import org.sitracel.stage.process.ValiderStage;

public class SitracelStageProcessFactory implements IProcessFactory {

    @Override
    public ProcessCall newProcessInstance(String className) {
        switch (className) {
            case "org.sitracel.stage.process.GenererPlanningStage": return new GenererPlanningStage();
            case "org.sitracel.stage.process.EvaluerStage":        return new EvaluerStage();
            case "org.sitracel.stage.process.ValiderStage":        return new ValiderStage();
            default: return null;
        }
    }
}
