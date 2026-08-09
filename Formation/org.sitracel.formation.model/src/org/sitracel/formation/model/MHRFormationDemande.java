package org.sitracel.formation.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRFormationDemande extends X_HR_FormationDemande {
    public MHRFormationDemande(Properties ctx, int HR_FormationDemande_ID, String trxName) {
        super(ctx, HR_FormationDemande_ID, trxName);
    }
    public MHRFormationDemande(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }
}
