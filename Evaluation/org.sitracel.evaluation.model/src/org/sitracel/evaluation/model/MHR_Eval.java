package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_Eval extends X_HR_Eval {
    private static final long serialVersionUID = 1L;

    public MHR_Eval(Properties ctx, int HR_Eval_ID, String trxName) {
        super(ctx, HR_Eval_ID, trxName);
    }

    public MHR_Eval(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
