package org.sitracel.recrutement.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.recrutement.model.MHRCandidatEvaluation;
import org.sitracel.recrutement.model.MHRCandidatExperience;
import org.sitracel.recrutement.model.MHRCandidatFormation;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHRCompetences;
import org.sitracel.recrutement.model.MHREntreprise;
import org.sitracel.recrutement.model.MHREtablissement;
import org.sitracel.recrutement.model.MHRExperience;
import org.sitracel.recrutement.model.MHROffreCompetences;
import org.sitracel.recrutement.model.MHROffreCritereEvaluation;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreExperience;
import org.sitracel.recrutement.model.MHROffreNiveauEtude;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRPertinence;
import org.sitracel.recrutement.model.MHRSessionRecrutement;
import org.sitracel.recrutement.model.MHRTypeEtablissement;

public class SitracelRecrutementModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		return null;
	}

}
