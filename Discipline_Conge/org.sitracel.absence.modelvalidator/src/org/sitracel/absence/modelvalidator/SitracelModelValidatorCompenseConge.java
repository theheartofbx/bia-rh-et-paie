package org.sitracel.absence.modelvalidator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.absence.model.MHRAbsenceCompensation;
import org.sitracel.absence.modelvalidator.service.AbsenceValidatorService;

public class SitracelModelValidatorCompenseConge implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRAbsenceCompensation.Table_Name, this);
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
        MHRAbsenceCompensation absenceCompense = (MHRAbsenceCompensation) po;
        AbsenceValidatorService.traiterCompensation(absenceCompense, type);
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
