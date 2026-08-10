package org.sitracel.formation.modelvalidator.catalogue;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationCatalogue implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationCatalogue", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_DELETE) {
            int nbSessions = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationSession WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
                po.get_ID());
            if (nbSessions > 0)
                return "Impossible de supprimer : " + nbSessions + " session(s) existent pour cette formation.";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
