package org.sitracel.stage.modelvalidator.factory;

import java.util.ArrayList;
import java.util.List;
import org.adempiere.base.IModelValidationFactory;
import org.compiere.model.ModelValidator;

public class SitracelStageModelValidatorFactory implements IModelValidationFactory {

    @Override
    public List<ModelValidator> newModelValidatorInstances() {
        List<ModelValidator> list = new ArrayList<>();
        list.add(new org.sitracel.stage.modelvalidator.stage.SitracelModelValidatorStage());
        list.add(new org.sitracel.stage.modelvalidator.suivi.SitracelModelValidatorStageSuivi());
        list.add(new org.sitracel.stage.modelvalidator.programmeligne.SitracelModelValidatorStageProgrammeLigne());
        list.add(new org.sitracel.stage.modelvalidator.programme.SitracelModelValidatorStageProgramme());
        list.add(new org.sitracel.stage.modelvalidator.objectif.SitracelModelValidatorStageObjectif());
        return list;
    }
}
