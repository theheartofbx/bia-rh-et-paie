package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalStatut extends X_HR_EvalStatut {
    private static final long serialVersionUID = 1L;

    public MHR_EvalStatut(Properties ctx, int HR_EvalStatut_ID, String trxName) {
        super(ctx, HR_EvalStatut_ID, trxName);
    }

    public MHR_EvalStatut(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
