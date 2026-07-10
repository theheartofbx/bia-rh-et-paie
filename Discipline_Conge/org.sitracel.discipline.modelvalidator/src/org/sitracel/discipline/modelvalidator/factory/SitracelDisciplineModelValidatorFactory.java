package org.sitracel.discipline.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.discipline.modelvalidator.demandeexplication.SitracelModelValidatorDemandeExplication;
import org.sitracel.discipline.modelvalidator.sanction.SitracelModelValidatorDiscipline;

public class SitracelDisciplineModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals("org.sitracel.discipline.modelvalidator.sanction.SitracelModelValidatorDiscipline")) {
            return new SitracelModelValidatorDiscipline();
        }
        if (className.equals("org.sitracel.discipline.modelvalidator.demandeexplication.SitracelModelValidatorDemandeExplication")) {
            return new SitracelModelValidatorDemandeExplication();
        }
        return null;
    }
}
