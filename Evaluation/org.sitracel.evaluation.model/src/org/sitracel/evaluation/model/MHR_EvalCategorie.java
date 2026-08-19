package org.sitracel.evaluation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHR_EvalCategorie extends X_HR_EvalCategorie {
    private static final long serialVersionUID = 1L;

    public MHR_EvalCategorie(Properties ctx, int HR_EvalCategorie_ID, String trxName) {
        super(ctx, HR_EvalCategorie_ID, trxName);
    }

    public MHR_EvalCategorie(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
