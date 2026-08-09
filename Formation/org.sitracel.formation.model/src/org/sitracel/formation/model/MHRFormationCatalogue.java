package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationCatalogue extends X_HR_FormationCatalogue {
    public MHRFormationCatalogue(Properties ctx, int HR_FormationCatalogue_ID, String trxName) {
        super(ctx, HR_FormationCatalogue_ID, trxName);
    }
    public MHRFormationCatalogue(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
