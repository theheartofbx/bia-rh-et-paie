package org.sitracel.formation.modelvalidator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.formation.modelvalidator.validators.*;

public class SitracelFormationModelValidator implements ModelValidator {

    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationCatalogue", this);
        engine.addModelChange("HR_FormationModule", this);
        engine.addModelChange("HR_FormationProgramme", this);
        engine.addModelChange("HR_FormationSession", this);
        engine.addModelChange("HR_FormationPlanning", this);
        engine.addModelChange("HR_FormationPlanningLigne", this);
        engine.addModelChange("HR_FormationParticipant", this);
        engine.addModelChange("HR_FormationDemande", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        String t = po.get_TableName();

        if ("HR_FormationCatalogue".equals(t)) {
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationCatalogue.beforeDelete(po);
        }

        if ("HR_FormationModule".equals(t)) {
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationModule.beforeDelete(po);
        }

        if ("HR_FormationProgramme".equals(t)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE)
                return SitracelModelValidatorFormationProgramme.beforeSave(po, type == TYPE_BEFORE_NEW);
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationProgramme.beforeDelete(po);
        }

        if ("HR_FormationSession".equals(t)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE)
                return SitracelModelValidatorFormationSession.beforeSave(po, type == TYPE_BEFORE_NEW);
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationSession.beforeDelete(po);
        }

        if ("HR_FormationPlanning".equals(t)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE)
                return SitracelModelValidatorFormationPlanning.beforeSave(po, type == TYPE_BEFORE_NEW);
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationPlanning.beforeDelete(po);
        }

        if ("HR_FormationPlanningLigne".equals(t)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE)
                return SitracelModelValidatorFormationPlanningLigne.beforeSave(po, type == TYPE_BEFORE_NEW);
            if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE)
                return SitracelModelValidatorFormationPlanningLigne.afterSave(po, type == TYPE_AFTER_NEW);
            if (type == TYPE_AFTER_DELETE)
                return SitracelModelValidatorFormationPlanningLigne.afterDelete(po);
        }

        if ("HR_FormationParticipant".equals(t)) {
            if (type == TYPE_BEFORE_NEW) return SitracelModelValidatorFormationParticipant.beforeNew(po);
        }

        if ("HR_FormationDemande".equals(t)) {
            if (type == TYPE_BEFORE_NEW) return SitracelModelValidatorFormationDemande.beforeNew(po);
            if (type == TYPE_BEFORE_DELETE) return SitracelModelValidatorFormationDemandeDelete.beforeDelete(po);
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
