package org.sitracel.mission.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.mission.modelvalidator.missionfrais.SitracelModelValidatorMissionFrais;
import org.sitracel.mission.modelvalidator.missionobjectif.SitracelModelValidatorMissionObjectif;
import org.sitracel.mission.modelvalidator.missionvalidation.SitracelModelValidatorMissionValidation;

public class SitracelMissionModelValidatorFactory implements IModelValidatorFactory{

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub

		if(className.equals("org.sitracel.mission.modelvalidator.missionfrais.SitracelModelValidatorMissionFrais")) {
			return new SitracelModelValidatorMissionFrais();
		}		

		if(className.equals("org.sitracel.mission.modelvalidator.missionobjectif.SitracelModelValidatorMissionObjectif")) {
			return new SitracelModelValidatorMissionObjectif();
		}		

		if(className.equals("org.sitracel.mission.modelvalidator.missionvalidation.SitracelModelValidatorMissionValidation")) {
			return new SitracelModelValidatorMissionValidation();
		}		
		
		return null;
	}

}
