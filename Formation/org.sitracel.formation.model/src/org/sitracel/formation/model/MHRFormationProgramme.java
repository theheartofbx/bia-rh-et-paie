package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationProgramme extends X_HR_FormationProgramme {
    public MHRFormationProgramme(Properties ctx, int HR_FormationProgramme_ID, String trxName) {
        super(ctx, HR_FormationProgramme_ID, trxName);
    }
    public MHRFormationProgramme(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
