package org.sitracel.recrutement.modelvalidator.candidature;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.modelvalidator.service.RecrutementValidatorService;

public class SitracelModelValidatorCandidature implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRCandidature.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        MHRCandidature candidature = (MHRCandidature) po;
        if (type == ModelValidator.TYPE_BEFORE_NEW) {
            String erreur = RecrutementValidatorService.verifierCandidature(candidature);
            if (erreur != null) return erreur;
            RecrutementValidatorService.avantCreationCandidature(candidature);
        }
        if (type == ModelValidator.TYPE_AFTER_NEW) {
            RecrutementValidatorService.apresCreationCandidature(candidature);
        }
        if (type == ModelValidator.TYPE_BEFORE_DELETE) {
            RecrutementValidatorService.suppressionCandidature(candidature);
        }
        if (type == ModelValidator.TYPE_AFTER_DELETE) {
            RecrutementValidatorService.recalculerApresSuppressionCandidature(candidature);
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
