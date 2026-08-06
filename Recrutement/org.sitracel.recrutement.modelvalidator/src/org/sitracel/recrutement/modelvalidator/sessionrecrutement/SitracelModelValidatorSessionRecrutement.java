package org.sitracel.recrutement.modelvalidator.sessionrecrutement;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHRSessionRecrutement;
import org.sitracel.recrutement.modelvalidator.service.RecrutementValidatorService;

public class SitracelModelValidatorSessionRecrutement implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRSessionRecrutement.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        MHRSessionRecrutement session = (MHRSessionRecrutement) po;
        if (type == ModelValidator.TYPE_BEFORE_NEW) {
            String erreur = RecrutementValidatorService.verifierDatesSession(session);
            if (erreur != null) return erreur;
            RecrutementValidatorService.creationSessionRecrutement(session);
        }
        if (type == ModelValidator.TYPE_BEFORE_CHANGE) {
            String erreur = RecrutementValidatorService.verifierDatesSession(session);
            if (erreur != null) return erreur;
            erreur = RecrutementValidatorService.verifierChangementTestSession(session);
            if (erreur != null) return erreur;
        }
        if (type == ModelValidator.TYPE_BEFORE_DELETE) {
            String erreur = RecrutementValidatorService.verifierSuppressionSession(session);
            if (erreur != null) return erreur;
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
