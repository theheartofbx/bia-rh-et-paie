package org.sitracel.recrutement.process.controller;

import java.sql.Timestamp;

import org.compiere.util.Env;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.model.MCBPartner;
import org.sitracel.recrutement.model.MHROffreEmploi;
import org.sitracel.recrutement.model.MHROffreTestEvaluation;
import org.sitracel.recrutement.model.MHRSessionRecrutement;

public class ProcessControllerRecrutement {

	public static void validerOffreEmploi(Integer offreEmploiID, Integer adUserID) {
		if(offreEmploiID!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHROffreEmploi offreEmploi = new MHROffreEmploi(Env.getCtx(), offreEmploiID, null);
			if(bi!=null && offreEmploi!=null) {
				offreEmploi.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				offreEmploi.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				offreEmploi.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				offreEmploi.setIsValidee(true);
				offreEmploi.setIsRejetee(false);
				offreEmploi.setDate_Validation(new Timestamp(System.currentTimeMillis()));
				offreEmploi.setDate_Rejet(null);
				offreEmploi.save();
			}
		}
	}

	public static void rejeterOffreEmploi(Integer offreEmploiID, Integer adUserID) {
		if(offreEmploiID!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHROffreEmploi offreEmploi = new MHROffreEmploi(Env.getCtx(), offreEmploiID, null);
			if(bi!=null && offreEmploi!=null) {
				offreEmploi.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				offreEmploi.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				offreEmploi.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				offreEmploi.setIsValidee(false);
				offreEmploi.setIsRejetee(true);
				offreEmploi.setDate_Validation(null);
				offreEmploi.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
				offreEmploi.save();
			}
		}
	}

	public static void validerSessionRecrutement(Integer sessionRecrutementID , Integer adUserID) {
		if(sessionRecrutementID!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHRSessionRecrutement sessionRecrutement = new MHRSessionRecrutement(Env.getCtx(), sessionRecrutementID, null);
			if(bi!=null && sessionRecrutement!=null) {
				sessionRecrutement.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				sessionRecrutement.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				sessionRecrutement.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				sessionRecrutement.setIsValidee(true);
				sessionRecrutement.setIsRejetee(false);
				sessionRecrutement.setDate_Validation(new Timestamp(System.currentTimeMillis()));
				sessionRecrutement.setDate_Rejet(null);
				sessionRecrutement.save();
			}
		}
	}

	public static void rejeterSessionRecrutement(Integer sessionRecrutementID , Integer adUserID) {
		if(sessionRecrutementID!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHRSessionRecrutement sessionRecrutement = new MHRSessionRecrutement(Env.getCtx(), sessionRecrutementID, null);
			if(bi!=null && sessionRecrutement!=null) {
				sessionRecrutement.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				sessionRecrutement.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				sessionRecrutement.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				sessionRecrutement.setIsValidee(false);
				sessionRecrutement.setIsRejetee(true);
				sessionRecrutement.setDate_Validation(null);
				sessionRecrutement.setDate_Rejet(new Timestamp(System.currentTimeMillis()));
				sessionRecrutement.save();
			}
		}
	}

	public static void validerTestEvaluation(Integer testEvaluationID , Integer adUserID) {
		if(testEvaluationID!=null && adUserID!=null) {
			BeanIdentifiant bi = MCBPartner.getIdentifiant(adUserID, null);
			MHROffreTestEvaluation testEvaluation = new MHROffreTestEvaluation(Env.getCtx(), testEvaluationID, null);
			if(bi!=null && testEvaluation!=null) {
				testEvaluation.setValide_Rejete_Par_Nom_ID(bi.getNumEmploye());
				testEvaluation.setValide_Rejete_Par_Matricule(bi.getMatriculeEmploye());
				testEvaluation.setValide_Rejete_Par_Poste_ID(bi.getNumeroPoste());
				testEvaluation.setIsValidee(true);
				testEvaluation.setDate_Validation(new Timestamp(System.currentTimeMillis()));
				testEvaluation.save();
			}
		}
	}
}
