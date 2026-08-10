package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationStatutParti extends X_HR_FormationStatutP {
    public MHRFormationStatutParti(Properties ctx, int HR_FormationStatutParti_ID, String trxName) {
        super(ctx, HR_FormationStatutParti_ID, trxName);
    }
    public MHRFormationStatutParti(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
