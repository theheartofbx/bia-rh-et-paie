package org.sitracel.discipline.modelvalidator.demandeexplication;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.modelvalidator.ModelValidatorDisciplineController;

public class SitracelModelValidatorDemandeExplication implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRDemandeExplication.Table_Name, this);
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
		MHRDemandeExplication demandeExplication = (MHRDemandeExplication)po;
		ModelValidatorDisciplineController.demandeExplication(demandeExplication, type);
		
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
