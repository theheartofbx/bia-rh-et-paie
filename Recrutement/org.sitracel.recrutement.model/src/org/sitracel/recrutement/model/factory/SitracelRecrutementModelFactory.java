package org.sitracel.recrutement.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
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

public class SitracelRecrutementModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name))
            return MHRCandidatEvaluation.class;
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name))
            return MHRCandidatExperience.class;
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name))
            return MHRCandidatFormation.class;
        if (tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name))
            return MHRCandidature.class;
        if (tableName.equalsIgnoreCase(I_HR_Competences.Table_Name))
            return MHRCompetences.class;
        if (tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name))
            return MHREntreprise.class;
        if (tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name))
            return MHREtablissement.class;
        if (tableName.equalsIgnoreCase(I_HR_Experience.Table_Name))
            return MHRExperience.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name))
            return MHROffreCompetences.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name))
            return MHROffreCritereEvaluation.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name))
            return MHROffreEmploi.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name))
            return MHROffreExperience.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name))
            return MHROffreNiveauEtude.class;
        if (tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name))
            return MHROffreTestEvaluation.class;
        if (tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name))
            return MHRPertinence.class;
        if (tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name))
            return MHRSessionRecrutement.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name))
            return MHRTypeEtablissement.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name))
            return new MHRCandidatEvaluation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name))
            return new MHRCandidatExperience(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name))
            return new MHRCandidatFormation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name))
            return new MHRCandidature(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Competences.Table_Name))
            return new MHRCompetences(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name))
            return new MHREntreprise(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name))
            return new MHREtablissement(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Experience.Table_Name))
            return new MHRExperience(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name))
            return new MHROffreCompetences(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name))
            return new MHROffreCritereEvaluation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name))
            return new MHROffreEmploi(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name))
            return new MHROffreExperience(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name))
            return new MHROffreNiveauEtude(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name))
            return new MHROffreTestEvaluation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name))
            return new MHRPertinence(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name))
            return new MHRSessionRecrutement(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name))
            return new MHRTypeEtablissement(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_CandidatEvaluation.Table_Name))
            return new MHRCandidatEvaluation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Expérience.Table_Name))
            return new MHRCandidatExperience(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidat_Formation.Table_Name))
            return new MHRCandidatFormation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Candidature.Table_Name))
            return new MHRCandidature(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Competences.Table_Name))
            return new MHRCompetences(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Entreprise.Table_Name))
            return new MHREntreprise(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Etablissement.Table_Name))
            return new MHREtablissement(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Experience.Table_Name))
            return new MHRExperience(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreCompetences.Table_Name))
            return new MHROffreCompetences(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreCritereEvaluation.Table_Name))
            return new MHROffreCritereEvaluation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreEmploi.Table_Name))
            return new MHROffreEmploi(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreExperience.Table_Name))
            return new MHROffreExperience(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreNiveauEtude.Table_Name))
            return new MHROffreNiveauEtude(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_OffreTestEvaluation.Table_Name))
            return new MHROffreTestEvaluation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Pertinence.Table_Name))
            return new MHRPertinence(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_SessionRecrutement.Table_Name))
            return new MHRSessionRecrutement(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeEtablissement.Table_Name))
            return new MHRTypeEtablissement(Env.getCtx(), rs, trxName);
        return null;
    }
}
