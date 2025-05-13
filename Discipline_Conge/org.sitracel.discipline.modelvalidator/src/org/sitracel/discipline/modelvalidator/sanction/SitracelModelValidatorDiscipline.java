package org.sitracel.discipline.modelvalidator.sanction;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.modelvalidator.ModelValidatorDisciplineController;

public class SitracelModelValidatorDiscipline implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRPunishment.Table_Name, this);
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
	    
	    if (!(po instanceof MHRPunishment)) {
	    	CLogger.get().severe("Objet non attendu dans modelChange : " + po.getClass().getName());
	        return null;
	    }

	    MHRPunishment punishment = (MHRPunishment) po;

	    try {
	        ModelValidatorDisciplineController.discipline(punishment, type);
	    } catch (Exception e) {
	    	CLogger.get().severe("Erreur dans discipline() : " + e.getMessage());
	        e.printStackTrace();
	        throw e;
	    }
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
