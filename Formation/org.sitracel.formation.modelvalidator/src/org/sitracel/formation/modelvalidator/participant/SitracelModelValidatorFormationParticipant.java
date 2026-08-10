package org.sitracel.formation.modelvalidator.participant;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationParticipant implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationParticipant", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            if (planningID > 0) {
                String isOk = DB.getSQLValueStringEx(null,
                    "SELECT IsOk FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", planningID);
                if (!"Y".equals(isOk))
                    return "Impossible d'inscrire : le planning n'est pas encore terminé.";
            }
            int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");
            if (sessionID > 0) {
                int nbPlaces = DB.getSQLValueEx(null,
                    "SELECT COALESCE(Nombre_Places, 0) FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                if (nbPlaces > 0) {
                    int nbPart = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'", sessionID);
                    if (nbPart >= nbPlaces)
                        return "Toutes les places sont prises (" + nbPlaces + "/" + nbPlaces + ").";
                }
            }
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
