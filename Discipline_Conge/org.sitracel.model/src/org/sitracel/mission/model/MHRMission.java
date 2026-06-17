package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRMission extends X_HR_Mission{
	private static final long serialVersionUID = -3016002170748274711L;

	public MHRMission(Properties ctx, int HR_Mission_ID, String trxName) {
		super(ctx, HR_Mission_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRMission(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
