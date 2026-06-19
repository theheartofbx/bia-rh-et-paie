package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 * Modèle de l'affectation d'un employé à une mission.
 *
 * La colonne IsAnnulee a été ajoutée en base le 19/06/2026
 * et régénérée via Model Generator.
 * Elle permet de distinguer :
 *   - Suppression (DELETE)     → MISSION_EMPLOYEE_REMOVED
 *   - Annulation (IsAnnulee=Y) → MISSION_EMPLOYEE_CANCELLED
 */
public class MHRMissionAffectation extends X_HR_Mission_Affectation {

    private static final long serialVersionUID = 5684311164629344418L;

    public MHRMissionAffectation(Properties ctx,
                                  int HR_Mission_Affectation_ID,
                                  String trxName) {
        super(ctx, HR_Mission_Affectation_ID, trxName);
    }

    public MHRMissionAffectation(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
