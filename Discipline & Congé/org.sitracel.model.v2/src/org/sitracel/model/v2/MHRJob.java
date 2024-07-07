package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRJob extends X_HR_Job{

	private static final long serialVersionUID = -8368391218136651345L;

	public MHRJob(Properties ctx, int HR_Job_ID, String trxName) {
		super(ctx, HR_Job_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRJob(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
