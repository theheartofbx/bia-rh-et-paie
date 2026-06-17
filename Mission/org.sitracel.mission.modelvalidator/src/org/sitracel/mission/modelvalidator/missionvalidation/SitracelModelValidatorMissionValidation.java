package org.sitracel.mission.modelvalidator.missionvalidation;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.mission.callout.ControlerMission;
import org.sitracel.mission.model.MHRMissionValidation;

public class SitracelModelValidatorMissionValidation implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		engine.addModelChange(MHRMissionValidation.Table_Name, this);
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
		
		if (!(po instanceof MHRMissionValidation)) {
            return null;
        }

        MHRMissionValidation mv = (MHRMissionValidation) po;

        // AFTER events uniquement
        if (type == TYPE_AFTER_NEW
         || type == TYPE_AFTER_CHANGE
         || type == TYPE_AFTER_DELETE) {

            int missionId = mv.getHR_Mission_ID();

            // Mission peut être 0 en suppression
            if (missionId > 0) {
                ControlerMission.recalculerEtatMission(
                    missionId,
                    mv.get_TrxName()
                );
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
