package org.sitracel.conge.modelvalidator.conge;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.modelvalidator.SitracelCongeGeneralModelValidator;

public class SitracelModelValidatorConge implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRHoliday.Table_Name, this);
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
        if (po != null && po.get_TableName().equalsIgnoreCase(MHRHoliday.Table_Name)) {
            return SitracelCongeGeneralModelValidator.conge(po, type);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
