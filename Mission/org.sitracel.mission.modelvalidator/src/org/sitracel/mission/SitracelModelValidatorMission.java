package org.sitracel.mission;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.notification.NotificationControler;

/**
 * ModelValidator sur MHRMission et MHRMissionAffectation.
 *
 * Événements gérés :
 *   MHRMission :
 *     TYPE_AFTER_NEW    → MISSION_CREATED
 *
 *   MHRMissionAffectation :
 *     TYPE_AFTER_NEW    → MISSION_EMPLOYEE_ASSIGNED
 *     TYPE_AFTER_CHANGE → MISSION_EMPLOYEE_UPDATED
 *                         ou MISSION_EMPLOYEE_CANCELLED si IsAnnulee vient de passer à Y
 *     TYPE_BEFORE_DELETE → MISSION_EMPLOYEE_REMOVED
 *
 * La distinction CANCELLED vs REMOVED :
 *   - REMOVED  : l'affectation est supprimée physiquement (DELETE)
 *   - CANCELLED : IsAnnulee passe de N à Y (annulation logique, ligne conservée)
 */
public class SitracelModelValidatorMission implements ModelValidator {

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        engine.addModelChange(MHRMission.Table_Name, this);
        engine.addModelChange(MHRMissionAffectation.Table_Name, this);
    }

    @Override
    public int getAD_Client_ID() { return 0; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        // ── MISSION ──────────────────────────────────────────────────────────
        if (po instanceof MHRMission) {
            if (type == TYPE_AFTER_NEW) {
                NotificationControler.notify(NotificationEvent.MISSION_CREATED, po);
            }
        }

        // ── AFFECTATION MISSION ───────────────────────────────────────────────
        if (po instanceof MHRMissionAffectation) {
            MHRMissionAffectation affectation = (MHRMissionAffectation) po;

            // Nouvelle affectation
            if (type == TYPE_AFTER_NEW) {
                NotificationControler.notify(
                    NotificationEvent.MISSION_EMPLOYEE_ASSIGNED, po);
            }

            // Modification — distinguer mise à jour vs annulation
            if (type == TYPE_AFTER_CHANGE) {
                boolean nouvelleAnnulation = affectation.isAnnulee();
                boolean ancienneAnnulation = getBooleanOld(po,
                    MHRMissionAffectation.COLUMNNAME_IsAnnulee);

                if (nouvelleAnnulation && !ancienneAnnulation) {
                    // IsAnnulee vient de passer à Y → annulation logique
                    NotificationControler.notify(
                        NotificationEvent.MISSION_EMPLOYEE_CANCELLED, po);
                } else {
                    // Autre modification
                    NotificationControler.notify(
                        NotificationEvent.MISSION_EMPLOYEE_UPDATED, po);
                }
            }

            // Suppression physique
            if (type == TYPE_BEFORE_DELETE) {
                NotificationControler.notify(
                    NotificationEvent.MISSION_EMPLOYEE_REMOVED, po);
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }

    // =========================================================================
    // UTILITAIRE
    // =========================================================================

    private static boolean getBooleanOld(PO po, String columnName) {
        Object oldValue = po.get_ValueOld(columnName);
        if (oldValue instanceof Boolean) return (Boolean) oldValue;
        if (oldValue instanceof String)  return "Y".equalsIgnoreCase((String) oldValue);
        return false;
    }
}
