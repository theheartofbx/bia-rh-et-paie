package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalAppreciation extends X_HR_EvalAppreciation {
    private static final long serialVersionUID = 1L;

    public MHR_EvalAppreciation(Properties ctx, int HR_EvalAppreciation_ID, String trxName) {
        super(ctx, HR_EvalAppreciation_ID, trxName);
    }

    public MHR_EvalAppreciation(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
