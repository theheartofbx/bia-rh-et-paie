package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationSession extends X_HR_FormationSession {
    public MHRFormationSession(Properties ctx, int HR_FormationSession_ID, String trxName) {
        super(ctx, HR_FormationSession_ID, trxName);
    }
    public MHRFormationSession(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
