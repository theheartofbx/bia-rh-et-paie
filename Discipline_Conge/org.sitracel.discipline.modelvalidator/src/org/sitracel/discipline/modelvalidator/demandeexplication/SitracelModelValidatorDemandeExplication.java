package org.sitracel.discipline.modelvalidator.demandeexplication;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.modelvalidator.service.DisciplineValidatorService;

public class SitracelModelValidatorDemandeExplication implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRDemandeExplication.Table_Name, this);
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
        DisciplineValidatorService.demandeExplication(
            (MHRDemandeExplication) po, type);
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
