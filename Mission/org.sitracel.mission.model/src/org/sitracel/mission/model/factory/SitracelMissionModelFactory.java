package org.sitracel.mission.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.mission.model.I_HR_Appreciation;
import org.sitracel.mission.model.I_HR_CategorieTypeObjectif;
import org.sitracel.mission.model.I_HR_Etat_Mission;
import org.sitracel.mission.model.I_HR_EtatObjectifMission;
import org.sitracel.mission.model.I_HR_Mission;
import org.sitracel.mission.model.I_HR_Mission_Affectation;
import org.sitracel.mission.model.I_HR_Mission_Competence;
import org.sitracel.mission.model.I_HR_Mission_Frais;
import org.sitracel.mission.model.I_HR_Mission_Objectif;
import org.sitracel.mission.model.I_HR_Mission_Validation;
import org.sitracel.mission.model.I_HR_Niveau_Importance;
import org.sitracel.mission.model.I_HR_Objectif;
import org.sitracel.mission.model.I_HR_RoleMissionEmploye;
import org.sitracel.mission.model.I_HR_TypeFrais;
import org.sitracel.mission.model.I_HR_Type_Mission;
import org.sitracel.mission.model.I_HR_TypeObjectif;
import org.sitracel.mission.model.I_HR_TypeValidation;
import org.sitracel.mission.model.MHRAppreciation;
import org.sitracel.mission.model.MHRCategorieTypeObjectif;
import org.sitracel.mission.model.MHREtatMission;
import org.sitracel.mission.model.MHREtatObjectifMission;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.mission.model.MHRMissionCompetence;
import org.sitracel.mission.model.MHRMissionFrais;
import org.sitracel.mission.model.MHRMissionObjectif;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.mission.model.MHRNiveauImportance;
import org.sitracel.mission.model.MHRObjectif;
import org.sitracel.mission.model.MHRRoleMissionEmploye;
import org.sitracel.mission.model.MHRTypeFrais;
import org.sitracel.mission.model.MHRTypeMission;
import org.sitracel.mission.model.MHRTypeObjectif;
import org.sitracel.mission.model.MHRTypeValidation;

public class SitracelMissionModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Appreciation.Table_Name))
            return MHRAppreciation.class;
        if (tableName.equalsIgnoreCase(I_HR_CategorieTypeObjectif.Table_Name))
            return MHRCategorieTypeObjectif.class;
        if (tableName.equalsIgnoreCase(I_HR_Etat_Mission.Table_Name))
            return MHREtatMission.class;
        if (tableName.equalsIgnoreCase(I_HR_EtatObjectifMission.Table_Name))
            return MHREtatObjectifMission.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission.Table_Name))
            return MHRMission.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission_Affectation.Table_Name))
            return MHRMissionAffectation.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission_Competence.Table_Name))
            return MHRMissionCompetence.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission_Frais.Table_Name))
            return MHRMissionFrais.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission_Objectif.Table_Name))
            return MHRMissionObjectif.class;
        if (tableName.equalsIgnoreCase(I_HR_Mission_Validation.Table_Name))
            return MHRMissionValidation.class;
        if (tableName.equalsIgnoreCase(I_HR_Niveau_Importance.Table_Name))
            return MHRNiveauImportance.class;
        if (tableName.equalsIgnoreCase(I_HR_Objectif.Table_Name))
            return MHRObjectif.class;
        if (tableName.equalsIgnoreCase(I_HR_RoleMissionEmploye.Table_Name))
            return MHRRoleMissionEmploye.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeFrais.Table_Name))
            return MHRTypeFrais.class;
        if (tableName.equalsIgnoreCase(I_HR_Type_Mission.Table_Name))
            return MHRTypeMission.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeObjectif.Table_Name))
            return MHRTypeObjectif.class;
        if (tableName.equalsIgnoreCase(I_HR_TypeValidation.Table_Name))
            return MHRTypeValidation.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Appreciation.Table_Name))
            return new MHRAppreciation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_CategorieTypeObjectif.Table_Name))
            return new MHRCategorieTypeObjectif(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Etat_Mission.Table_Name))
            return new MHREtatMission(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_EtatObjectifMission.Table_Name))
            return new MHREtatObjectifMission(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission.Table_Name))
            return new MHRMission(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Affectation.Table_Name))
            return new MHRMissionAffectation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Competence.Table_Name))
            return new MHRMissionCompetence(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Frais.Table_Name))
            return new MHRMissionFrais(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Objectif.Table_Name))
            return new MHRMissionObjectif(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Validation.Table_Name))
            return new MHRMissionValidation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Niveau_Importance.Table_Name))
            return new MHRNiveauImportance(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Objectif.Table_Name))
            return new MHRObjectif(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_RoleMissionEmploye.Table_Name))
            return new MHRRoleMissionEmploye(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeFrais.Table_Name))
            return new MHRTypeFrais(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Mission.Table_Name))
            return new MHRTypeMission(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeObjectif.Table_Name))
            return new MHRTypeObjectif(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeValidation.Table_Name))
            return new MHRTypeValidation(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Appreciation.Table_Name))
            return new MHRAppreciation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_CategorieTypeObjectif.Table_Name))
            return new MHRCategorieTypeObjectif(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Etat_Mission.Table_Name))
            return new MHREtatMission(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_EtatObjectifMission.Table_Name))
            return new MHREtatObjectifMission(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission.Table_Name))
            return new MHRMission(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Affectation.Table_Name))
            return new MHRMissionAffectation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Competence.Table_Name))
            return new MHRMissionCompetence(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Frais.Table_Name))
            return new MHRMissionFrais(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Objectif.Table_Name))
            return new MHRMissionObjectif(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Mission_Validation.Table_Name))
            return new MHRMissionValidation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Niveau_Importance.Table_Name))
            return new MHRNiveauImportance(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Objectif.Table_Name))
            return new MHRObjectif(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_RoleMissionEmploye.Table_Name))
            return new MHRRoleMissionEmploye(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeFrais.Table_Name))
            return new MHRTypeFrais(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Mission.Table_Name))
            return new MHRTypeMission(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeObjectif.Table_Name))
            return new MHRTypeObjectif(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_TypeValidation.Table_Name))
            return new MHRTypeValidation(Env.getCtx(), rs, trxName);
        return null;
    }
}
