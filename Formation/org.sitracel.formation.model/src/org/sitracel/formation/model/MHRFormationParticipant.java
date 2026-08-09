package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationParticipant extends X_HR_FormationParticipant {
    public MHRFormationParticipant(Properties ctx, int HR_FormationParticipant_ID, String trxName) {
        super(ctx, HR_FormationParticipant_ID, trxName);
    }
    public MHRFormationParticipant(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
