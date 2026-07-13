package org.sitracel.paie.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorActualiserPoste;
import org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorHistoriquePaie;

public class SitracelPaieModelValidatorFactory implements IModelValidatorFactory{

	@Override
	public ModelValidator newModelValidatorInstance(String className) {
		// TODO Auto-generated method stub
		if(className.equals("org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorHistoriquePaie")) {
			return new SitracelModelValidatorHistoriquePaie();
		}
		if(className.equals("org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorActualiserPoste")) {
			return new SitracelModelValidatorActualiserPoste();
		}
		return null;
	}

}
