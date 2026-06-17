package org.sitracel.mission.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAppreciation extends X_HR_Appreciation{
	private static final long serialVersionUID = 156886713211551732L;

	public MHRAppreciation(Properties ctx, int HR_Appreciation_ID, String trxName) {
		super(ctx, HR_Appreciation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRAppreciation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
