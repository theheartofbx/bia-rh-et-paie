package org.sitracel.recrutement.modelvalidator.offreemploi;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.modelvalidator.ModelValidatorControllerRecrutement;

public class SitracelModelValidatorOffreEmploi implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHROffreEmploi.Table_Name, this);
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
        MHROffreEmploi offreEmploi = (MHROffreEmploi) po;
        // TYPE_AFTER_NEW : le record est sauvé en base — notification possible
        if (type == ModelValidator.TYPE_AFTER_NEW) {
            ModelValidatorControllerRecrutement.creationOffreEmploi(offreEmploi);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
