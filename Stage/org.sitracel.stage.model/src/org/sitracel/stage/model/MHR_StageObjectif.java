package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageObjectif extends X_HR_StageObjectif {
    private static final long serialVersionUID = 1L;

    public MHR_StageObjectif(Properties ctx, int HR_StageObjectif_ID, String trxName) {
        super(ctx, HR_StageObjectif_ID, trxName);
    }

    public MHR_StageObjectif(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
