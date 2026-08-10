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
        engine.addModelChange("HR_FormationSession", this);
        engine.addModelChange("HR_FormationPlanningLigne", this);
        engine.addModelChange("HR_FormationParticipant", this);
        engine.addModelChange("HR_FormationDemande", this);
    }

    @Override
    public int getAD_Client_ID() {
        return clientID;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        String tableName = po.get_TableName();

        // HR_FormationSession
        if ("HR_FormationSession".equals(tableName)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
                String msg = SitracelModelValidatorFormationSession.beforeSave(po, type == TYPE_BEFORE_NEW);
                if (msg != null) return msg;
            }
        }

        // HR_FormationPlanningLigne
        if ("HR_FormationPlanningLigne".equals(tableName)) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
                String msg = SitracelModelValidatorFormationPlanningLigne.beforeSave(po, type == TYPE_BEFORE_NEW);
                if (msg != null) return msg;
            }
            if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) {
                String msg = SitracelModelValidatorFormationPlanningLigne.afterSave(po, type == TYPE_BEFORE_NEW);
                if (msg != null) return msg;
            }
        }

        // HR_FormationParticipant
        if ("HR_FormationParticipant".equals(tableName)) {
            if (type == TYPE_BEFORE_NEW) {
                String msg = SitracelModelValidatorFormationParticipant.beforeNew(po);
                if (msg != null) return msg;
            }
        }

        // HR_FormationDemande
        if ("HR_FormationDemande".equals(tableName)) {
            if (type == TYPE_BEFORE_NEW) {
                String msg = SitracelModelValidatorFormationDemande.beforeNew(po);
                if (msg != null) return msg;
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
