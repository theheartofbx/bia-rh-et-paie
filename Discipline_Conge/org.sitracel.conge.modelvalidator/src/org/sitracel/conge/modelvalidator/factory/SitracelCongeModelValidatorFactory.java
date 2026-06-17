package org.sitracel.conge.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.conge.modelvalidator.absence.SitracelModelValidatorAbsence;
import org.sitracel.conge.modelvalidator.conge.SitracelModelValidatorCompenseConge;
import org.sitracel.conge.modelvalidator.conge.SitracelModelValidatorConge;

public class SitracelCongeModelValidatorFactory implements IModelValidatorFactory{

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.modelvalidator.v2.absence.SitracelModelValidatorAbsence")) {
			return new SitracelModelValidatorAbsence();
		}		
		
		if(className.equals("org.sitracel.modelvalidator.v2.conge.SitracelModelValidatorConge")) {
			return new SitracelModelValidatorConge();
		}
		
		if(className.equals("org.sitracel.modelvalidator.v2.conge.ModelValidatorCompenseConge")) {
			return new SitracelModelValidatorCompenseConge();
		}		
		
		return null;
	}

}
