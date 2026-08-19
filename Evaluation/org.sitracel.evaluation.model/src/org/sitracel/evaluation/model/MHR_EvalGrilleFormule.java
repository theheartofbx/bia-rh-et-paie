package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalGrilleFormule extends X_HR_EvalGrilleFormule {
    private static final long serialVersionUID = 1L;

    public MHR_EvalGrilleFormule(Properties ctx, int HR_EvalGrilleFormule_ID, String trxName) {
        super(ctx, HR_EvalGrilleFormule_ID, trxName);
    }

    public MHR_EvalGrilleFormule(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
