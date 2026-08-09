package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationSessionStatut extends X_HR_FormationSessionStatut {
    public MHRFormationSessionStatut(Properties ctx, int HR_FormationSessionStatut_ID, String trxName) {
        super(ctx, HR_FormationSessionStatut_ID, trxName);
    }
    public MHRFormationSessionStatut(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
