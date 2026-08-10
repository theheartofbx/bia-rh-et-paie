package org.sitracel.formation.modelvalidator.planning;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationPlanning implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationPlanning", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_CHANGE) {
            if (po.is_ValueChanged("IsOk")) {
                String newIsOk = (String) po.get_Value("IsOk");
                if ("N".equals(newIsOk)) {
                    int nbPart = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                        po.get_ID());
                    if (nbPart > 0)
                        return "Impossible : " + nbPart + " participant(s) sont assignés à ce planning.";
                }
            }
        }
        if (type == TYPE_BEFORE_DELETE) {
            int nbPart = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                po.get_ID());
            if (nbPart > 0)
                return "Impossible de supprimer : " + nbPart + " participant(s) sont assignés à ce planning.";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
