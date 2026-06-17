package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAmpliation extends X_HR_Ampliation{
	private static final long serialVersionUID = -3745162965177297580L;

	public MHRAmpliation(Properties ctx, int HR_Ampliation_ID, String trxName) {
		super(ctx, HR_Ampliation_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRAmpliation(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
