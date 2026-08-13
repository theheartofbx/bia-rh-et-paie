package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageSuivi extends X_HR_StageSuivi {
    private static final long serialVersionUID = 1L;

    public MHR_StageSuivi(Properties ctx, int HR_StageSuivi_ID, String trxName) {
        super(ctx, HR_StageSuivi_ID, trxName);
    }

    public MHR_StageSuivi(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
