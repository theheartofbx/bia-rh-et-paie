package org.sitracel.mission.callout.mission.missionvalidation;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.mission.callout.service.MissionCalloutRepository;
import org.sitracel.mission.model.MHRMissionValidation;

/**
 * Corrigé : appelait encore l'ancien SqlControlerMission (@Deprecated,
 * maintenant supprimé) alors que MissionCalloutRepository (le remplaçant)
 * existait déjà à côté — deux copies du même code, avec un risque de
 * divergence silencieuse comme celui déjà rencontré dans Discipline.
 */
public class SitracelMissionCalloutMIssionValidation implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        if (value == null) {
            return "";
        }

        boolean isChecked = "Y".equals(value.toString());

        // Si on coche IsValidee → décocher IsRejetee
        if (mField.getColumnName().equals(MHRMissionValidation.COLUMNNAME_IsValidee) && isChecked) {
            mTab.setValue("IsRejetee", "N");
            setTypeValidation(ctx, mTab);
        }

        // Si on coche IsRejetee → décocher IsValidee
        if (mField.getColumnName().equals(MHRMissionValidation.COLUMNNAME_IsRejetee) && isChecked) {
            mTab.setValue("IsValidee", "N");
            setTypeValidation(ctx, mTab);
        }
        return null;
    }

    /**
     * Détermine et positionne HR_TypeValidation_ID
     */
    private void setTypeValidation(Properties ctx, GridTab mTab) {

        int adUserId = Env.getAD_User_ID(ctx);
        Integer hrMissionId = (Integer) mTab.getValue("HR_Mission_ID");

        if (hrMissionId == null || hrMissionId <= 0) {
            return;
        }

        // 1) RH ?
        if (MissionCalloutRepository.isUserRH(adUserId)) {
            Integer typeId = MissionCalloutRepository.getTypeValidationIdByName("RH");
            if (typeId != null) {
                mTab.setValue("HR_TypeValidation_ID", typeId);
            }
            return;
        }

        // 2) Catégorie de responsabilité
        Integer categorie = MissionCalloutRepository.getCategorieResponsabilite(hrMissionId, adUserId);

        if (categorie == null) {
            return;
        }

        if (categorie == 1) {
            Integer typeId = MissionCalloutRepository.getTypeValidationIdByName("N+1");
            if (typeId != null) {
                mTab.setValue("HR_TypeValidation_ID", typeId);
            }
        }

        if (categorie == 2) {
            Integer typeId = MissionCalloutRepository.getTypeValidationIdByName("N+2");
            if (typeId != null) {
                mTab.setValue("HR_TypeValidation_ID", typeId);
            }
        }
    }
}
