package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalObjectif extends X_HR_EvalObjectif {
    private static final long serialVersionUID = 1L;

    public MHR_EvalObjectif(Properties ctx, int HR_EvalObjectif_ID, String trxName) {
        super(ctx, HR_EvalObjectif_ID, trxName);
    }

    public MHR_EvalObjectif(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
