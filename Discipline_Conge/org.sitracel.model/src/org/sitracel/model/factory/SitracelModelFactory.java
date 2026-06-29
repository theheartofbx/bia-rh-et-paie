package org.sitracel.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Absence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Absence_Compensation;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Autorisation_Absence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Autorisation_Conge;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Employee_Children;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Holiday;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Holiday_Department;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Public_Holiday;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Type_Absence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.I_HR_Type_Conge;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRAbsence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRAbsenceCompensation;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRAutorisationAbsence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRAutorisationConge;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHREmployeeChildren;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRHoliday;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRHolidayDepartment;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRPublicHoliday;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRTypeAbsence;
// COMMENTÉ — attend Model Generator: import org.sitracel.conge.model.MHRTypeConge;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Delai_Reponse;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Demande_Explication;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Dossier_Disciplinaire;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Duree_Sanction;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Punishment;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_Sanction_Autorisation;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.I_HR_TypeSanction;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRDelaiReponse;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRDemandeExplication;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRDossierDisciplinaire;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRDureeSanction;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRPunishment;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRSanctionAutorisation;
// COMMENTÉ — attend Model Generator: import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.model.I_HR_Ampliation;
import org.sitracel.organigramme.model.I_HR_Categorie_Responsabilite;
import org.sitracel.model.I_HR_EmployeeJob;
import org.sitracel.organigramme.model.I_HR_Organigramme;
import org.sitracel.model.I_HR_Parametre_Numerique;
import org.sitracel.model.MHRAmpliation;
// COMMENTÉ — attend Model Generator: import org.sitracel.model.MHRCategorieResponsabilite;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.organigramme.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;
// COMMENTÉ — attend Model Generator: import org.sitracel.paie.model.I_HR_Calcul_Paie;
// COMMENTÉ — attend Model Generator: import org.sitracel.paie.model.I_HR_DetailIndemniteBrutConge;
// COMMENTÉ — attend Model Generator: import org.sitracel.paie.model.MHRCalculPaie;
// COMMENTÉ — attend Model Generator: import org.sitracel.paie.model.MHRDetailIndemniteBrutConge;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_CandidatEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Candidat_Expérience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Candidat_Formation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Candidature;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Competences;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Entreprise;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Etablissement;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Experience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreCompetences;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreCritereEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreEmploi;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreExperience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreNiveauEtude;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_OffreTestEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_Pertinence;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_SessionRecrutement;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.I_HR_TypeEtablissement;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRCandidatEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRCandidatExperience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRCandidatFormation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRCandidature;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRCompetences;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHREntreprise;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHREtablissement;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRExperience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreCompetences;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreCritereEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreEmploi;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreExperience;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreNiveauEtude;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHROffreTestEvaluation;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRPertinence;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRSessionRecrutement;
// COMMENTÉ — attend Model Generator: import org.sitracel.recrutement.model.MHRTypeEtablissement;

public class SitracelModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name)) {
			return MHRAmpliation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name)) {
			return MHROrganigramme.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name)) {
			return MHREmployeeJob.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRCategorieResponsabilite.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return MHRParametreNumerique.class;
		}

// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRAbsence.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRAbsenceCompensation.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRTypeAbsence.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRHoliday.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRTypeConge.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRAutorisationAbsence.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRAutorisationConge.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRPublicHoliday.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHREmployeeChildren.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Holiday_Department.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRHolidayDepartment.class;
		}


// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRDemandeExplication.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRPunishment.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRTypeSanction.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRDureeSanction.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRDelaiReponse.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRSanctionAutorisation.class;
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRDossierDisciplinaire.class;
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRCandidatExperience.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRCandidatFormation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRCandidatEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRCandidature.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Competences.Table_Name)) {
			return MHRCompetences.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name)) {
			return MHREntreprise.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name)) {
			return MHREtablissement.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Experience.Table_Name)) {
			return MHRExperience.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name)) {
			return MHROffreCompetences.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHROffreCritereEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHROffreEmploi.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return MHROffreExperience.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return MHROffreNiveauEtude.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHROffreTestEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return MHRPertinence.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return MHRSessionRecrutement.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name)) {
			return MHRTypeEtablissement.class;
		}

		if(tableName.equalsIgnoreCase(I_HR_DetailIndemniteBrutConge.Table_Name)) {
			return MHRDetailIndemniteBrutConge.class;
		}


		if(tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name)) {
			return MHRCalculPaie.class;
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), Record_ID, trxName);
		}


// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAbsence(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAbsenceCompensation(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeAbsence(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRHoliday(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeConge(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAutorisationAbsence(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAutorisationConge(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRPublicHoliday(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHREmployeeChildren(Env.getCtx(), Record_ID, trxName);
		}


// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDemandeExplication(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRPunishment(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeSanction(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDureeSanction(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDelaiReponse(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDossierDisciplinaire(Env.getCtx(), Record_ID, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRSanctionAutorisation(Env.getCtx(), Record_ID, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatFormation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidature(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Competences.Table_Name)) {
			return new MHRCompetences(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name)) {
			return new MHREntreprise(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name)) {
			return new MHREtablissement(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Experience.Table_Name)) {
			return new MHRExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name)) {
			return new MHROffreCompetences(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreCritereEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreEmploi(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreTestEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRSessionRecrutement(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name)) {
			return new MHRTypeEtablissement(Env.getCtx(), Record_ID, trxName);
		}

		if(tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name)) {
			return new MHRCalculPaie(Env.getCtx(), Record_ID, trxName);
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
		}


// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAbsence(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAbsenceCompensation(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeAbsence(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRHoliday(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeConge(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAutorisationAbsence(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRAutorisationConge(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRPublicHoliday(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHREmployeeChildren(Env.getCtx(), rs, trxName);
		}


// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDemandeExplication(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRPunishment(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRTypeSanction(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDureeSanction(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDelaiReponse(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRDossierDisciplinaire(Env.getCtx(), rs, trxName);
		}
// COMMENTÉ — attend Model Generator: 		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRSanctionAutorisation(Env.getCtx(), rs, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatFormation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidatEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRCandidature(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Competences.Table_Name)) {
			return new MHRCompetences(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name)) {
			return new MHREntreprise(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name)) {
			return new MHREtablissement(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Experience.Table_Name)) {
			return new MHRExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name)) {
			return new MHROffreCompetences(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreCritereEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreEmploi(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHROffreTestEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
// COMMENTÉ — attend Model Generator: 			return new MHRSessionRecrutement(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name)) {
			return new MHRTypeEtablissement(Env.getCtx(), rs, trxName);
		}

		if(tableName.equalsIgnoreCase(I_HR_Calcul_Paie.Table_Name)) {
			return new MHRCalculPaie(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
