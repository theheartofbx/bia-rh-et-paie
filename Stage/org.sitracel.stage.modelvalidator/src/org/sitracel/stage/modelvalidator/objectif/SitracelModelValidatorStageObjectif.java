package org.sitracel.stage.modelvalidator.objectif;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.stage.model.I_HR_StageObjectif;

public class SitracelModelValidatorStageObjectif implements ModelValidator {

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_StageObjectif.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_DELETE) {
            int count = DB.getSQLValue(po.get_TrxName(),
                "SELECT COUNT(*) FROM HR_StageProgrammeLigne WHERE HR_StageObjectif_ID = ? AND IsActive = 'Y'",
                po.get_ID());
            if (count > 0) {
                return "Impossible de supprimer cet objectif : il est utilisé dans " + count + " programme(s).";
            }
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
