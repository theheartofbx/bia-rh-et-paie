package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationModule extends X_HR_FormationModule {
    public MHRFormationModule(Properties ctx, int HR_FormationModule_ID, String trxName) {
        super(ctx, HR_FormationModule_ID, trxName);
    }
    public MHRFormationModule(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
