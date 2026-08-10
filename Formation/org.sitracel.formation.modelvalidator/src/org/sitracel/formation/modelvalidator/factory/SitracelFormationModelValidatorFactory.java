package org.sitracel.formation.modelvalidator.factory;

import org.adempiere.base.IModelValidatorFactory;
import org.compiere.model.ModelValidator;
import org.sitracel.formation.modelvalidator.catalogue.*;
import org.sitracel.formation.modelvalidator.module.*;
import org.sitracel.formation.modelvalidator.programme.*;
import org.sitracel.formation.modelvalidator.session.*;
import org.sitracel.formation.modelvalidator.planning.*;
import org.sitracel.formation.modelvalidator.planningligne.*;
import org.sitracel.formation.modelvalidator.participant.*;
import org.sitracel.formation.modelvalidator.demande.*;

public class SitracelFormationModelValidatorFactory implements IModelValidatorFactory {

    @Override
    public ModelValidator newModelValidatorInstance(String className) {
        if (className.endsWith("SitracelModelValidatorFormationCatalogue")) return new SitracelModelValidatorFormationCatalogue();
        if (className.endsWith("SitracelModelValidatorFormationModule")) return new SitracelModelValidatorFormationModule();
        if (className.endsWith("SitracelModelValidatorFormationProgramme")) return new SitracelModelValidatorFormationProgramme();
        if (className.endsWith("SitracelModelValidatorFormationSession")) return new SitracelModelValidatorFormationSession();
        if (className.endsWith("SitracelModelValidatorFormationPlanning")) return new SitracelModelValidatorFormationPlanning();
        if (className.endsWith("SitracelModelValidatorFormationPlanningLigne")) return new SitracelModelValidatorFormationPlanningLigne();
        if (className.endsWith("SitracelModelValidatorFormationParticipant")) return new SitracelModelValidatorFormationParticipant();
        if (className.endsWith("SitracelModelValidatorFormationDemande")) return new SitracelModelValidatorFormationDemande();
        return null;
    }
}
