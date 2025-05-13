package org.sitracel.paie.modelvalidator.CalculPaie;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.modelvalidator.controller.ModelValidatorControllerPaie;

public class SitracelModelValidatorActualiserPoste implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRElementBasePaieEmploye.Table_Name, this);
		
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
		if (MHRElementBasePaieEmploye.Table_Name.equalsIgnoreCase(po.get_TableName())) {
            MHRElementBasePaieEmploye elementBasePaieEmploye = new MHRElementBasePaieEmploye(
                    po.getCtx(), po.get_ID(), po.get_TrxName());
            if (elementBasePaieEmploye != null) {
                ModelValidatorControllerPaie.actualiserPoste(elementBasePaieEmploye, type);
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
