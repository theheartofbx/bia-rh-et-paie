package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationDomaine extends X_HR_FormationDomaine {
    public MHRFormationDomaine(Properties ctx, int HR_FormationDomaine_ID, String trxName) {
        super(ctx, HR_FormationDomaine_ID, trxName);
    }
    public MHRFormationDomaine(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
