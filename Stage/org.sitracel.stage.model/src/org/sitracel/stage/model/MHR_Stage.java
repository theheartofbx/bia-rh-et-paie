package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_Stage extends X_HR_Stage {
    private static final long serialVersionUID = 1L;

    public MHR_Stage(Properties ctx, int HR_Stage_ID, String trxName) {
        super(ctx, HR_Stage_ID, trxName);
    }

    public MHR_Stage(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
