package org.sitracel.stage.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.stage.modelvalidator.stage.*;
import org.sitracel.stage.modelvalidator.suivi.*;
import org.sitracel.stage.modelvalidator.programmeligne.*;
import org.sitracel.stage.modelvalidator.programme.*;
import org.sitracel.stage.modelvalidator.objectif.*;

public class SitracelStageModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.endsWith("SitracelModelValidatorStage")) return new SitracelModelValidatorStage();
        if (className.endsWith("SitracelModelValidatorStageSuivi")) return new SitracelModelValidatorStageSuivi();
        if (className.endsWith("SitracelModelValidatorStageProgrammeLigne")) return new SitracelModelValidatorStageProgrammeLigne();
        if (className.endsWith("SitracelModelValidatorStageProgramme")) return new SitracelModelValidatorStageProgramme();
        if (className.endsWith("SitracelModelValidatorStageObjectif")) return new SitracelModelValidatorStageObjectif();
        return null;
    }
}
