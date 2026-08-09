package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationPlanningLigne extends X_HR_FormationPlanningLigne {
    public MHRFormationPlanningLigne(Properties ctx, int HR_FormationPlanningLigne_ID, String trxName) {
        super(ctx, HR_FormationPlanningLigne_ID, trxName);
    }
    public MHRFormationPlanningLigne(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
