package org.sitracel.contrat.modelvalidator;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;
import org.sitracel.contrat.modelvalidator.service.ContratValidatorService;

public class SitracelModelValidatorContrat implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRContrat.Table_Name, this);
        engine.addModelChange(MHRAffectation.Table_Name, this);
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
        if (po == null) return null;

        if (po instanceof MHRContrat) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
                return ContratValidatorService.validerContrat((MHRContrat) po);
            }
            if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) {
                ContratValidatorService.genererName((MHRContrat) po);
            }
        }

        if (po instanceof MHRAffectation) {
            if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
                return ContratValidatorService.validerAffectation((MHRAffectation) po);
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) {
        return null;
    }
}
