package org.sitracel.formation.modelvalidator.demande;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SitracelModelValidatorFormationDemande implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationDemande", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW) {
            int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");
            if (bpartnerID <= 0) {
                int userID = Env.getAD_User_ID(Env.getCtx());
                int bpFromUser = DB.getSQLValueEx(null,
                    "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?", userID);
                if (bpFromUser > 0) po.set_ValueOfColumn("C_BPartner_ID", bpFromUser);
            }
            int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");
            if (sessionID > 0) {
                String isValidee = DB.getSQLValueStringEx(null,
                    "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                if (!"Y".equals(isValidee))
                    return "Impossible : la session n'est pas encore validée.";
            }
        }
        if (type == TYPE_BEFORE_DELETE) {
            int nbPart = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationDemande_ID=? AND IsActive='Y'",
                po.get_ID());
            if (nbPart > 0)
                return "Impossible de supprimer : un participant a été créé depuis cette demande.";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
