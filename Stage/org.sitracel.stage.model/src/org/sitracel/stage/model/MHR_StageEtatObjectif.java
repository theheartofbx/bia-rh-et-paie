package org.sitracel.stage.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_StageEtatObjectif extends X_HR_StageEtatObjectif {
    private static final long serialVersionUID = 1L;

    public MHR_StageEtatObjectif(Properties ctx, int HR_StageEtatObjectif_ID, String trxName) {
        super(ctx, HR_StageEtatObjectif_ID, trxName);
    }

    public MHR_StageEtatObjectif(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
