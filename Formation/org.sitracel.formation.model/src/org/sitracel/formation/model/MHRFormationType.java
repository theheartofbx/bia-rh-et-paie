package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationType extends X_HR_FormationType {
    public MHRFormationType(Properties ctx, int HR_FormationType_ID, String trxName) {
        super(ctx, HR_FormationType_ID, trxName);
    }
    public MHRFormationType(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
