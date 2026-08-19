package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalPeriode extends X_HR_EvalPeriode {
    private static final long serialVersionUID = 1L;

    public MHR_EvalPeriode(Properties ctx, int HR_EvalPeriode_ID, String trxName) {
        super(ctx, HR_EvalPeriode_ID, trxName);
    }

    public MHR_EvalPeriode(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
