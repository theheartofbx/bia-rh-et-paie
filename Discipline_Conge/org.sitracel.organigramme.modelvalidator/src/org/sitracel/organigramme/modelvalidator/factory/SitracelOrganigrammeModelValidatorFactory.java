package org.sitracel.organigramme.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.organigramme.modelvalidator.SitracelModelValidatorOrganigramme;

public class SitracelOrganigrammeModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals("org.sitracel.organigramme.modelvalidator.SitracelModelValidatorOrganigramme")) {
            return new SitracelModelValidatorOrganigramme();
        }
        return null;
    }
}
