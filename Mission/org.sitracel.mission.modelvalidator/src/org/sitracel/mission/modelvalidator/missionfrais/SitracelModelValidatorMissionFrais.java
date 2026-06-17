package org.sitracel.mission.modelvalidator.missionfrais;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.mission.callout.ControlerMission;
import org.sitracel.mission.model.MHRMissionFrais;

public class SitracelModelValidatorMissionFrais implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRMissionFrais.Table_Name, this);
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
		if (po == null) {
	        return null;
	    }
		
		if (!(po instanceof MHRMissionFrais)) {
            return null;
        }

        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            ControlerMission.majValidationRejet((MHRMissionFrais) po);
        }
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
