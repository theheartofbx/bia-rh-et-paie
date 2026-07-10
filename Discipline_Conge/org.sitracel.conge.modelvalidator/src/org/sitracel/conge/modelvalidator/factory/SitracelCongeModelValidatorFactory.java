package org.sitracel.conge.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.conge.modelvalidator.conge.SitracelModelValidatorConge;

public class SitracelCongeModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals("org.sitracel.conge.modelvalidator.conge.SitracelModelValidatorConge")) {
            return new SitracelModelValidatorConge();
        }
        return null;
    }
}
