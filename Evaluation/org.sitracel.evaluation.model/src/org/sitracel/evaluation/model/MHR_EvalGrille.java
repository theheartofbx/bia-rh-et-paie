package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalGrille extends X_HR_EvalGrille {
    private static final long serialVersionUID = 1L;

    public MHR_EvalGrille(Properties ctx, int HR_EvalGrille_ID, String trxName) {
        super(ctx, HR_EvalGrille_ID, trxName);
    }

    public MHR_EvalGrille(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
