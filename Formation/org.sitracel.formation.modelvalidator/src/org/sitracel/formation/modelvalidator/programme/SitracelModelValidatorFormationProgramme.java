package org.sitracel.formation.modelvalidator.programme;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationProgramme implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationProgramme", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            if (!po.is_new() && po.is_ValueChanged("Nombre_Partie")) {
                int nbLignes = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationProgramme_ID=? AND IsActive='Y'",
                    po.get_ID());
                if (nbLignes > 0)
                    return "Impossible de modifier le nombre de parties : " + nbLignes + " ligne(s) de planning existent déjà.";
            }
        }
        if (type == TYPE_BEFORE_DELETE) {
            int nbLignes = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationProgramme_ID=? AND IsActive='Y'",
                po.get_ID());
            if (nbLignes > 0)
                return "Impossible de supprimer : " + nbLignes + " ligne(s) de planning référencent ce programme.";
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
