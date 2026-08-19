package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalGrilleLigne extends X_HR_EvalGrilleLigne {
    private static final long serialVersionUID = 1L;

    public MHR_EvalGrilleLigne(Properties ctx, int HR_EvalGrilleLigne_ID, String trxName) {
        super(ctx, HR_EvalGrilleLigne_ID, trxName);
    }

    public MHR_EvalGrilleLigne(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
