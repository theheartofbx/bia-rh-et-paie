package org.sitracel.paie.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorElementBasePaieEmploye;
import org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorHistoriquePaie;
import org.sitracel.paie.modelvalidator.MouvementPaie.SitracelModelValidatorMouvementPaie;

public class SitracelPaieModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.equals(
                "org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorHistoriquePaie"))
            return new SitracelModelValidatorHistoriquePaie();
        if (className.equals(
                "org.sitracel.paie.modelvalidator.CalculPaie.SitracelModelValidatorElementBasePaieEmploye"))
            return new SitracelModelValidatorElementBasePaieEmploye();
        if (className.equals(
                "org.sitracel.paie.modelvalidator.MouvementPaie.SitracelModelValidatorMouvementPaie"))
            return new SitracelModelValidatorMouvementPaie();
        return null;
    }
}
