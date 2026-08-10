package org.sitracel.formation.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.formation.modelvalidator.SitracelFormationModelValidator;

public class SitracelFormationModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        // Cette factory retourne un ModelValidator global
        if ("org.sitracel.formation.modelvalidator.SitracelFormationModelValidator".equals(className)) {
            return new SitracelFormationModelValidator();
        }
        return null;
    }
}
