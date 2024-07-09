package org.sitracel.discipline.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.discipline.modelvalidator.demandeexplication.SitracelModelValidatorDemandeExplication;
import org.sitracel.discipline.modelvalidator.sanction.SitracelModelValidatorDiscipline;

public class SitracelDisciplineModelValidatorFactory implements IModelValidatorFactory{

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.modelvalidator.v2.discipline.SitracelModelValidatorDiscipline")) {
			return new SitracelModelValidatorDiscipline();
		}
		
		
		if(className.equals("org.sitracel.modelvalidator.v2.demandeexplication.SitracelModelValidatorDemandeExplication")) {
			return new SitracelModelValidatorDemandeExplication();
		}
		return null;
	}

}
