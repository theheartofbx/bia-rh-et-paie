package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalLigne extends X_HR_EvalLigne {
    private static final long serialVersionUID = 1L;

    public MHR_EvalLigne(Properties ctx, int HR_EvalLigne_ID, String trxName) {
        super(ctx, HR_EvalLigne_ID, trxName);
    }

    public MHR_EvalLigne(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
