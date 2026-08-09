package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationPlanning extends X_HR_FormationPlanning {
    public MHRFormationPlanning(Properties ctx, int HR_FormationPlanning_ID, String trxName) {
        super(ctx, HR_FormationPlanning_ID, trxName);
    }
    public MHRFormationPlanning(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
