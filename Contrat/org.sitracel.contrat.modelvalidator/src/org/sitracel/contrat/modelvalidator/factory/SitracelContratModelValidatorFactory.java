package org.sitracel.contrat.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.contrat.modelvalidator.SitracelModelValidatorContrat;

public class SitracelContratModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals("org.sitracel.contrat.modelvalidator.SitracelModelValidatorContrat")) {
            return new SitracelModelValidatorContrat();
        }
        return null;
    }
}
