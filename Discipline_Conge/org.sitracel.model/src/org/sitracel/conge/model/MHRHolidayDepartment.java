package org.sitracel.conge.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRHolidayDepartment extends X_HR_Holiday_Department{

	private static final long serialVersionUID = 2694325345436050446L;

	public MHRHolidayDepartment(Properties ctx, int HR_Holiday_Department_ID, String trxName) {
		super(ctx, HR_Holiday_Department_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRHolidayDepartment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
