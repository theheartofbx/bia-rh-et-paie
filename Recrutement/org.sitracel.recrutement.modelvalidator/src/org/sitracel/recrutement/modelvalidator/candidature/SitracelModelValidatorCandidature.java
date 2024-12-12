package org.sitracel.recrutement.modelvalidator.candidature;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.modelvalidator.ModelValidatorControllerRecrutement;

public class SitracelModelValidatorCandidature implements ModelValidator{

	private static CLogger log = CLogger.getCLogger (PO.class);
	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRCandidature.Table_Name, this);
	}

	@Override
	public int getAD_Client_ID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String modelChange(PO po, int type) throws Exception {
		// TODO Auto-generated method stub
		MHRCandidature candidature = (MHRCandidature)po;
		if(type==ModelValidator.TYPE_AFTER_NEW) {
			ModelValidatorControllerRecrutement.creationCandidature(candidature);
		}
		if(type==ModelValidator.TYPE_BEFORE_DELETE) {
			ModelValidatorControllerRecrutement.suppressionCandidature(candidature);
		}
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
