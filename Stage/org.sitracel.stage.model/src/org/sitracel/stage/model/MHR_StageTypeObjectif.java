package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageTypeObjectif extends X_HR_StageTypeObjectif {
    private static final long serialVersionUID = 1L;

    public MHR_StageTypeObjectif(Properties ctx, int HR_StageTypeObjectif_ID, String trxName) {
        super(ctx, HR_StageTypeObjectif_ID, trxName);
    }

    public MHR_StageTypeObjectif(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
