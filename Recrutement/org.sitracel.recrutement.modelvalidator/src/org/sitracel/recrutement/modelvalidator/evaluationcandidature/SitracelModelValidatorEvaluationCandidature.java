package org.sitracel.recrutement.modelvalidator.evaluationcandidature;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.modelvalidator.ModelValidatorControllerRecrutement;

public class SitracelModelValidatorEvaluationCandidature implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRCandidatEvaluation.Table_Name, this);
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
		MHRCandidatEvaluation candidatEvaluation = (MHRCandidatEvaluation)po;
		if(type==ModelValidator.TYPE_AFTER_CHANGE) {
			if(candidatEvaluation!=null) {
				MHRCandidature candidature = new MHRCandidature(Env.getCtx(), candidatEvaluation.getHR_Candidature_ID(), null);
				if(candidature!=null) {					
					ModelValidatorControllerRecrutement.actualiserCandidature(candidature.getHR_SessionRecrutement_ID(), candidatEvaluation);
				}				
			}
		}
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
