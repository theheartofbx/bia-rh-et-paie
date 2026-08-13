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
            return beforeSave(po);
        }
        if (type == TYPE_BEFORE_DELETE) {
            return beforeDelete(po);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    private String beforeSave(PO po) {
        int programmeId = (Integer) po.get_Value("HR_StageProgramme_ID");
        int objectifId = (Integer) po.get_Value("HR_StageObjectif_ID");
        int ligneId = po.get_ID();

        // Garde-fou 1 : Anti-doublon objectif dans le même programme
        int count = DB.getSQLValue(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageProgrammeLigne " +
            "WHERE HR_StageProgramme_ID = ? AND HR_StageObjectif_ID = ? AND HR_StageProgrammeLigne_ID != ?",
            programmeId, objectifId, ligneId);
        if (count > 0) {
            return "Cet objectif est déjà dans ce programme.";
        }

        // Garde-fou 2 : Empêcher modification si un stage a déjà généré le planning depuis ce programme
        int stagesGeneres = DB.getSQLValue(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi s JOIN HR_Stage st ON st.HR_Stage_ID = s.HR_Stage_ID " +
            "WHERE st.HR_StageProgramme_ID = ? AND st.IsActive = 'Y'",
            programmeId);
        if (stagesGeneres > 0 && !po.is_new()) {
            return "Ce programme est déjà utilisé par un stage avec un planning généré. Impossible de le modifier.";
        }

        return null;
    }

    private String beforeDelete(PO po) {
        int programmeId = (Integer) po.get_Value("HR_StageProgramme_ID");

        // Empêcher suppression si le programme est utilisé dans un stage avec planning
        int stagesGeneres = DB.getSQLValue(po.get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi s JOIN HR_Stage st ON st.HR_Stage_ID = s.HR_Stage_ID " +
            "WHERE st.HR_StageProgramme_ID = ? AND st.IsActive = 'Y'",
            programmeId);
        if (stagesGeneres > 0) {
            return "Impossible de supprimer : ce programme est utilisé par un stage avec un planning généré.";
        }

        return null;
    }
}
