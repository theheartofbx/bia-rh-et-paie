package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageProgramme extends X_HR_StageProgramme {
    private static final long serialVersionUID = 1L;

    public MHR_StageProgramme(Properties ctx, int HR_StageProgramme_ID, String trxName) {
        super(ctx, HR_StageProgramme_ID, trxName);
    }

    public MHR_StageProgramme(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
