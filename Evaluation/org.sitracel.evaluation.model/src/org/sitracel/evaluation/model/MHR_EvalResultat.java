package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalResultat extends X_HR_EvalResultat {
    private static final long serialVersionUID = 1L;

    public MHR_EvalResultat(Properties ctx, int HR_EvalResultat_ID, String trxName) {
        super(ctx, HR_EvalResultat_ID, trxName);
    }

    public MHR_EvalResultat(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
