package org.sitracel.formation.modelvalidator.module;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationModule implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationModule", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_DELETE) {
            int nbProgrammes = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationModule_ID=? AND IsActive='Y'",
                po.get_ID());
            if (nbProgrammes > 0)
                return "Impossible de supprimer : ce module est utilisé dans " + nbProgrammes + " programme(s).";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
