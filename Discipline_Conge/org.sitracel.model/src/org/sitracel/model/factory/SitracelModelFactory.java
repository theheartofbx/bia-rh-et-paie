package org.sitracel.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.I_HR_Absence;
import org.sitracel.conge.model.I_HR_Absence_Compensation;
import org.sitracel.conge.model.I_HR_Autorisation_Absence;
import org.sitracel.conge.model.I_HR_Autorisation_Conge;
import org.sitracel.conge.model.I_HR_Employee_Children;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.I_HR_Holiday_Department;
import org.sitracel.conge.model.I_HR_Public_Holiday;
import org.sitracel.conge.model.I_HR_Type_Absence;
import org.sitracel.conge.model.I_HR_Type_Conge;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRAutorisationAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRHolidayDepartment;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.conge.model.MHRTypeConge;
import org.sitracel.discipline.model.I_HR_Delai_Reponse;
import org.sitracel.discipline.model.I_HR_Demande_Explication;
import org.sitracel.discipline.model.I_HR_Dossier_Disciplinaire;
import org.sitracel.discipline.model.I_HR_Duree_Sanction;
import org.sitracel.discipline.model.I_HR_Punishment;
import org.sitracel.discipline.model.I_HR_Sanction_Autorisation;
import org.sitracel.discipline.model.I_HR_TypeSanction;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;
import org.sitracel.model.I_HR_Ampliation;
import org.sitracel.organigramme.model.I_HR_Categorie_Responsabilite;
import org.sitracel.model.I_HR_EmployeeJob;
import org.sitracel.organigramme.model.I_HR_Organigramme;
import org.sitracel.model.I_HR_Parametre_Numerique;
import org.sitracel.model.MHRAmpliation;
import org.sitracel.model.MHRCategorieResponsabilite;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.organigramme.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;
import org.sitracel.paie.model.I_HR_Calcul_Paie;
import org.sitracel.paie.model.I_HR_DetailIndemniteBrutConge;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRDetailIndemniteBrutConge;
import org.sitracel.recrutement.model.I_HR_CandidatEvaluation;
import org.sitracel.recrutement.model.I_HR_Candidat_Expérience;
import org.sitracel.recrutement.model.I_HR_Candidat_Formation;
import org.sitracel.recrutement.model.I_HR_Candidature;
import org.sitracel.recrutement.model.I_HR_Competences;
import org.sitracel.recrutement.model.I_HR_Entreprise;
import org.sitracel.recrutement.model.I_HR_Etablissement;
import org.sitracel.recrutement.model.I_HR_Experience;
import org.sitracel.recrutement.model.I_HR_OffreCompetences;
import org.sitracel.recrutement.model.I_HR_OffreCritereEvaluation;
import org.sitracel.recrutement.model.I_HR_OffreEmploi;
import org.sitracel.recrutement.model.I_HR_OffreExperience;
import org.sitracel.recrutement.model.I_HR_OffreNiveauEtude;
import org.sitracel.recrutement.model.I_HR_OffreTestEvaluation;
import org.sitracel.recrutement.model.I_HR_Pertinence;
import org.sitracel.recrutement.model.I_HR_SessionRecrutement;
import org.sitracel.recrutement.model.I_HR_TypeEtablissement;
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
			return MHRCategorieResponsabilite.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return MHRParametreNumerique.class;
		}

		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
			return MHRAbsence.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
			return MHRAbsenceCompensation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
			return MHRTypeAbsence.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
			return MHRHoliday.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
			return MHRTypeConge.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
			return MHRAutorisationAbsence.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
			return MHRAutorisationConge.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
			return MHRPublicHoliday.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
			return MHREmployeeChildren.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Holiday_Department.Table_Name)) {
			return MHRHolidayDepartment.class;
		}


		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
			return MHRDemandeExplication.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
			return MHRPunishment.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
			return MHRTypeSanction.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
			return MHRDureeSanction.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
			return MHRDelaiReponse.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
			return MHRSanctionAutorisation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
			return MHRDossierDisciplinaire.class;
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
			return MHRCandidatExperience.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
			return MHRCandidatFormation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
			return MHRCandidatEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
			return MHRCandidature.class;
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
			return MHROffreCritereEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
			return MHROffreEmploi.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return MHROffreExperience.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return MHROffreNiveauEtude.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
			return MHROffreTestEvaluation.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return MHRPertinence.class;
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
			return MHRSessionRecrutement.class;
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
			return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), Record_ID, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), Record_ID, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), Record_ID, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
			return new MHRCandidatExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
			return new MHRCandidatFormation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
			return new MHRCandidatEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
			return new MHRCandidature(Env.getCtx(), Record_ID, trxName);
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
			return new MHROffreCritereEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
			return new MHROffreEmploi(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
			return new MHROffreTestEvaluation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
			return new MHRSessionRecrutement(Env.getCtx(), Record_ID, trxName);
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
			return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Absence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), rs, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Demande_Explication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Punishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_TypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Duree_Sanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Delai_Reponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Dossier_Disciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Sanction_Autorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), rs, trxName);
		}


		if(tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name)) {
			return new MHRCandidatExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name)) {
			return new MHRCandidatFormation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name)) {
			return new MHRCandidatEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name)) {
			return new MHRCandidature(Env.getCtx(), rs, trxName);
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
			return new MHROffreCritereEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name)) {
			return new MHROffreEmploi(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name)) {
			return new MHROffreExperience(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name)) {
			return new MHROffreNiveauEtude(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name)) {
			return new MHROffreTestEvaluation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name)) {
			return new MHRPertinence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name)) {
			return new MHRSessionRecrutement(Env.getCtx(), rs, trxName);
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
