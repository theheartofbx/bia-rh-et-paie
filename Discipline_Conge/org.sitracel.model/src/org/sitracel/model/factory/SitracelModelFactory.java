package org.sitracel.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRAutorisationAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.model.MHRAmpliation;
import org.sitracel.model.MHRCategorieResponsabilite;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;
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

public class SitracelModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return MHRAmpliation.class;
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return MHROrganigramme.class;
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return MHREmployeeJob.class;
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return MHRCategorieResponsabilite.class;
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return MHRParametreNumerique.class;
		}
		
		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return MHRAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return MHRAbsenceCompensation.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return MHRTypeAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return MHRHoliday.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return MHRTypeConge.class;
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return MHRAutorisationAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return MHRAutorisationConge.class;
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return MHRPublicHoliday.class;
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return MHREmployeeChildren.class;
		}
		

		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return MHRDemandeExplication.class;
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return MHRPunishment.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return MHRTypeSanction.class;
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return MHRDureeSanction.class;
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return MHRDelaiReponse.class;
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return MHRSanctionAutorisation.class;
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return MHRDossierDisciplinaire.class;
		}
		

		if(tableName.equalsIgnoreCase(MHRCandidatExperience.Table_Name)) {
			return MHRCandidatExperience.class;
		}
		if(tableName.equalsIgnoreCase(MHRCandidatFormation.Table_Name)) {
			return MHRCandidatFormation.class;
		}
		if(tableName.equalsIgnoreCase(MHRCandidatEvaluation.Table_Name)) {
			return MHRCandidatEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(MHRCandidature.Table_Name)) {
			return MHRCandidature.class;
		}
		if(tableName.equalsIgnoreCase(MHRCompetences.Table_Name)) {
			return MHRCompetences.class;
		}
		if(tableName.equalsIgnoreCase(MHREntreprise.Table_Name)) {
			return MHREntreprise.class;
		}
		if(tableName.equalsIgnoreCase(MHREtablissement.Table_Name)) {
			return MHREtablissement.class;
		}
		if(tableName.equalsIgnoreCase(MHRExperience.Table_Name)) {
			return MHRExperience.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreCompetences.Table_Name)) {
			return MHROffreCompetences.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreCritereEvaluation.Table_Name)) {
			return MHROffreCritereEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreEmploi.Table_Name)) {
			return MHROffreEmploi.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreExperience.Table_Name)) {
			return MHROffreExperience.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreNiveauEtude.Table_Name)) {
			return MHROffreNiveauEtude.class;
		}
		if(tableName.equalsIgnoreCase(MHROffreTestEvaluation.Table_Name)) {
			return MHROffreTestEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(MHRPertinence.Table_Name)) {
			return MHRPertinence.class;
		}
		if(tableName.equalsIgnoreCase(MHRSessionRecrutement.Table_Name)) {
			return MHRSessionRecrutement.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeEtablissement.Table_Name)) {
			return MHRTypeEtablissement.class;
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), Record_ID, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), Record_ID, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), Record_ID, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRCandidatExperience.Table_Name)) {
			return new MHRCandidatExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidatFormation.Table_Name)) {
			return new MHRCandidatFormation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidatEvaluation.Table_Name)) {
			return new MHRCandidatEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidature.Table_Name)) {
			return new MHRCandidature(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCompetences.Table_Name)) {
			return new MHRCompetences(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREntreprise.Table_Name)) {
			return new MHREntreprise(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREtablissement.Table_Name)) {
			return new MHREtablissement(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRExperience.Table_Name)) {
			return new MHRExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreCompetences.Table_Name)) {
			return new MHROffreCompetences(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreCritereEvaluation.Table_Name)) {
			return new MHROffreCritereEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreEmploi.Table_Name)) {
			return new MHROffreEmploi(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreTestEvaluation.Table_Name)) {
			return new MHROffreTestEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSessionRecrutement.Table_Name)) {
			return new MHRSessionRecrutement(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeEtablissement.Table_Name)) {
			return new MHRTypeEtablissement(Env.getCtx(), Record_ID, trxName);
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), rs, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), rs, trxName);
		}
		

		if(tableName.equalsIgnoreCase(MHRCandidatExperience.Table_Name)) {
			return new MHRCandidatExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidatFormation.Table_Name)) {
			return new MHRCandidatFormation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidatEvaluation.Table_Name)) {
			return new MHRCandidatEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCandidature.Table_Name)) {
			return new MHRCandidature(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCompetences.Table_Name)) {
			return new MHRCompetences(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREntreprise.Table_Name)) {
			return new MHREntreprise(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREtablissement.Table_Name)) {
			return new MHREtablissement(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRExperience.Table_Name)) {
			return new MHRExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreCompetences.Table_Name)) {
			return new MHROffreCompetences(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreCritereEvaluation.Table_Name)) {
			return new MHROffreCritereEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreEmploi.Table_Name)) {
			return new MHROffreEmploi(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROffreTestEvaluation.Table_Name)) {
			return new MHROffreTestEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSessionRecrutement.Table_Name)) {
			return new MHRSessionRecrutement(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeEtablissement.Table_Name)) {
			return new MHRTypeEtablissement(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
