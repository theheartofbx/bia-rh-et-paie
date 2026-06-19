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

/**
 * ModelValidator sur MHRMissionValidation.
 *
 * Recalcule l'état de la mission parente après chaque vote de validation,
 * et notifie si la mission vient d'être validée ou rejetée.
 *
 * CORRECTION BUG : les anciens états (isValidee, isRejetee) sont capturés
 * AVANT le recalcul, car get_ValueOld() ne fonctionne pas sur un PO
 * rechargé depuis la base.
 */
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

        if (type == TYPE_AFTER_NEW
         || type == TYPE_AFTER_CHANGE
         || type == TYPE_AFTER_DELETE) {

            if (missionId > 0) {

                // ✅ CORRECTION : charger AVANT recalcul pour capturer l'ancien état
                MHRMission missionAvant = new MHRMission(
                    Env.getCtx(), missionId, mv.get_TrxName());

                boolean etaitValidee = missionAvant != null && missionAvant.isValidee();
                boolean etaitRejetee = missionAvant != null && missionAvant.isRejetee();

                // Recalcul de l'état
                ControlerMission.recalculerEtatMission(missionId, mv.get_TrxName());

                // ✅ Recharger APRÈS pour connaître le nouvel état
                MHRMission missionApres = new MHRMission(Env.getCtx(), missionId, null);
                if (missionApres == null) return null;

                // Détecter la transition et notifier
                if (missionApres.isValidee() && !etaitValidee) {
                    NotificationControler.notify(
                        NotificationEvent.MISSION_VALIDATED, missionApres);
                } else if (missionApres.isRejetee() && !etaitRejetee) {
                    NotificationControler.notify(
                        NotificationEvent.MISSION_REJECTED, missionApres);
                }
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
