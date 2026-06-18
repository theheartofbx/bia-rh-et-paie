package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRTypeMission extends X_HR_Type_Mission{
	private static final long serialVersionUID = -4904611584427384455L;

	public MHRTypeMission(Properties ctx, int HR_Type_Mission_ID, String trxName) {
		super(ctx, HR_Type_Mission_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRTypeMission(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
