package org.sitracel.recrutement.modelvalidator.testevaluation;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.modelvalidator.service.RecrutementValidatorService;

public class SitracelModelValidatorTestEvaluation implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHROffreTestEvaluation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        MHROffreTestEvaluation test = (MHROffreTestEvaluation) po;
        if (type == ModelValidator.TYPE_BEFORE_NEW) {
            RecrutementValidatorService.creationTestEvaluation(test);
        }
        if (type == ModelValidator.TYPE_BEFORE_DELETE) {
            String erreur = RecrutementValidatorService.verifierSuppressionTest(test);
            if (erreur != null) return erreur;
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
