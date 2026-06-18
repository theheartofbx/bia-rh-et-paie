package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMissionAffectation extends X_HR_Mission_Affectation{
	private static final long serialVersionUID = 5684311164629344418L;

	public MHRMissionAffectation(Properties ctx, int HR_Mission_Affectation_ID, String trxName) {
		super(ctx, HR_Mission_Affectation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMissionAffectation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
