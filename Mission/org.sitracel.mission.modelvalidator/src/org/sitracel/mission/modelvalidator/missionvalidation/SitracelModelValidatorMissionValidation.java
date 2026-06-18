package org.sitracel.mission.modelvalidator.missionvalidation;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.mission.callout.ControlerMission;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.notification.NotificationControler;

public class SitracelModelValidatorMissionValidation implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRMissionValidation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        if (po == null || !(po instanceof MHRMissionValidation)) return null;

        MHRMissionValidation mv = (MHRMissionValidation) po;
        int missionId = mv.getHR_Mission_ID();

        // Recalcul de l'état de la mission après chaque changement
        if (type == TYPE_AFTER_NEW
         || type == TYPE_AFTER_CHANGE
         || type == TYPE_AFTER_DELETE) {

            if (missionId > 0) {
                ControlerMission.recalculerEtatMission(missionId, mv.get_TrxName());

                // Charger la mission pour détecter son nouvel état
                MHRMission mission = new MHRMission(Env.getCtx(), missionId, null);
                if (mission != null) {
                    detecterTransitionEtatMission(mission, po, type);
                }
            }
        }

        return null;
    }

    /**
     * Détecte si la mission vient d'être validée ou rejetée
     * et déclenche la notification correspondante.
     *
     * La validation/rejet passe par MHRMissionValidation qui agrège
     * les votes — ControlerMission.recalculerEtatMission() met à jour
     * isValidee/isRejetee sur la mission parente.
     */
    private void detecterTransitionEtatMission(
            MHRMission mission, PO po, int type) {

        boolean nouvelleValidation = mission.isValidee();
        boolean ancienneValidation = getBooleanOld(mission,
            MHRMission.COLUMNNAME_IsValidee);
        if (nouvelleValidation && !ancienneValidation) {
            NotificationControler.notify(NotificationEvent.MISSION_VALIDATED, mission);
            return;
        }

        boolean nouveauRejet = mission.isRejetee();
        boolean ancienRejet  = getBooleanOld(mission,
            MHRMission.COLUMNNAME_IsRejetee);
        if (nouveauRejet && !ancienRejet) {
            NotificationControler.notify(NotificationEvent.MISSION_REJECTED, mission);
        }
    }

    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) return (Boolean) oldValue;
        if (oldValue instanceof String)  return "Y".equalsIgnoreCase((String) oldValue);
        return false;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
