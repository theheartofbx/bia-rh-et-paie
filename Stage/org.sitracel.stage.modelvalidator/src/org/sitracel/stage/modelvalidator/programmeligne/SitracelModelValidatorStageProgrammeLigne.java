package org.sitracel.stage.modelvalidator.programmeligne;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.sitracel.stage.model.I_HR_StageProgrammeLigne;

public class SitracelModelValidatorStageProgrammeLigne implements ModelValidator {

    private int m_AD_Client_ID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) m_AD_Client_ID = client.getAD_Client_ID();
        engine.addModelChange(I_HR_StageProgrammeLigne.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return m_AD_Client_ID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            int programmeId = (Integer) po.get_Value("HR_StageProgramme_ID");
            int objectifId = (Integer) po.get_Value("HR_StageObjectif_ID");
            int ligneId = po.get_ID();

            int count = DB.getSQLValue(po.get_TrxName(),
                "SELECT COUNT(*) FROM HR_StageProgrammeLigne " +
                "WHERE HR_StageProgramme_ID = ? AND HR_StageObjectif_ID = ? AND HR_StageProgrammeLigne_ID != ?",
                programmeId, objectifId, ligneId);

            if (count > 0) {
                return "Cet objectif est déjà dans ce programme.";
            }
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
