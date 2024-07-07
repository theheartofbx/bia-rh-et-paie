package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREmployeeJob extends X_HR_EmployeeJob{
	private static final long serialVersionUID = 1485708938240840905L;

	public MHREmployeeJob(Properties ctx, int HR_EmployeeJob_ID, String trxName) {
		super(ctx, HR_EmployeeJob_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREmployeeJob(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
