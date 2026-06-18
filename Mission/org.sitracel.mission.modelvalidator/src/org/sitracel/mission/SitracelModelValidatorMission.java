package org.sitracel.mission;

import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.mission.model.MHRMission;
import org.sitracel.mission.model.MHRMissionAffectation;
import org.sitracel.notification.NotificationControler;

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

        // ── MISSION ──────────────────────────────────────────────
        if (po instanceof MHRMission) {
            if (type == TYPE_AFTER_NEW) {
                NotificationControler.notify(NotificationEvent.MISSION_CREATED, po);
            }
        }

        // ── AFFECTATION MISSION ───────────────────────────────────
        if (po instanceof MHRMissionAffectation) {
            if (type == TYPE_AFTER_NEW) {
                NotificationControler.notify(
                    NotificationEvent.MISSION_EMPLOYEE_ASSIGNED, po);
            }
            if (type == TYPE_AFTER_CHANGE) {
                NotificationControler.notify(
                    NotificationEvent.MISSION_EMPLOYEE_UPDATED, po);
            }
            if (type == TYPE_BEFORE_DELETE) {
                NotificationControler.notify(
                    NotificationEvent.MISSION_EMPLOYEE_REMOVED, po);
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
