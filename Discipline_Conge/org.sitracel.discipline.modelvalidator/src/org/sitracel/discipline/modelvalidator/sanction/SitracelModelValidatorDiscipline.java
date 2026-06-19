package org.sitracel.discipline.modelvalidator.sanction;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.modelvalidator.service.DisciplineValidatorService;

public class SitracelModelValidatorDiscipline implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRPunishment.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() {
        return 0;
    }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
        return null;
    }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (!(po instanceof MHRPunishment)) {
            CLogger.get().severe("Objet non attendu : " + po.getClass().getName());
            return null;
        }
        try {
            DisciplineValidatorService.discipline((MHRPunishment) po, type);
        } catch (Exception e) {
            CLogger.get().severe("Erreur discipline() : " + e.getMessage());
            throw e;
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
