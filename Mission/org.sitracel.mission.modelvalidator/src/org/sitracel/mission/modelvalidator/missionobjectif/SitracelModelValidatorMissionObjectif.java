package org.sitracel.mission.modelvalidator.missionobjectif;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.mission.model.MHRMissionObjectif;
import org.sitracel.model.MCBPartner;

public class SitracelModelValidatorMissionObjectif implements ModelValidator{

	@Override
	public void initialize(ModelValidationEngine engine, MClient client) {
		// TODO Auto-generated method stub
		 engine.addModelChange(MHRMissionObjectif.Table_Name, this);
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
		
		if (!(po instanceof MHRMissionObjectif)) {
            return null;
        }

        // AFTER uniquement
        if (type != TYPE_AFTER_NEW && type != TYPE_AFTER_CHANGE) {
            return null;
        }

        MHRMissionObjectif objectif = (MHRMissionObjectif) po;

        // Si le score n'a pas changé → on ne fait rien
        if (!objectif.is_ValueChanged(
                MHRMissionObjectif.COLUMNNAME_Score)) {
            return null;
        }

        try {
        	BeanIdentifiant identifiant =
        	        MCBPartner.getIdentifiant(
        	            Env.getAD_User_ID(Env.getCtx()),
        	            objectif.get_TrxName()
        	        );

        	    if (identifiant != null) {

        	        objectif.set_ValueOfColumn(
        	            MHRMissionObjectif.COLUMNNAME_Evalue_Par_Nom_ID,
        	            identifiant.getNumEmploye()
        	        );

        	        objectif.set_ValueOfColumn(
        	            MHRMissionObjectif.COLUMNNAME_Evalue_Par_Poste_ID,
        	            identifiant.getNumeroPoste()
        	        );

        	        objectif.set_ValueOfColumn(
        	            MHRMissionObjectif.COLUMNNAME_Evalue_Par_Matricule,
        	            identifiant.getMatriculeEmploye()
        	        );
        	    }
        } catch (Exception e) {
            throw new AdempiereException(
                "Erreur lors de l'enregistrement des informations de l'évaluateur",
                e
            );
        }
		return null;
	}

	@Override
	public String docValidate(PO po, int timing) {
		// TODO Auto-generated method stub
		return null;
	}

}
