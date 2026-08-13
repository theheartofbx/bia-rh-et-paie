package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageProgrammeLigne extends X_HR_StageProgrammeLigne {
    private static final long serialVersionUID = 1L;

    public MHR_StageProgrammeLigne(Properties ctx, int HR_StageProgrammeLigne_ID, String trxName) {
        super(ctx, HR_StageProgrammeLigne_ID, trxName);
    }

    public MHR_StageProgrammeLigne(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
