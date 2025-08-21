package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREmployeeJobf extends X_HR_EmployeeJob{
	private static final long serialVersionUID = 1485708938240840905L;

	public MHREmployeeJobf(Properties ctx, int HR_EmployeeJob_ID, String trxName) {
		super(ctx, HR_EmployeeJob_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHREmployeeJobf(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
