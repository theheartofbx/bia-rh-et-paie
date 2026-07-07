package org.sitracel.absence.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.absence.modelvalidator.SitracelModelValidatorAbsence;
import org.sitracel.absence.modelvalidator.SitracelModelValidatorCompenseConge;

public class SitracelAbsenceModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals("org.sitracel.absence.modelvalidator.SitracelModelValidatorAbsence")) {
            return new SitracelModelValidatorAbsence();
        }
        if (className.equals("org.sitracel.absence.modelvalidator.SitracelModelValidatorCompenseConge")) {
            return new SitracelModelValidatorCompenseConge();
        }
        return null;
    }
}
