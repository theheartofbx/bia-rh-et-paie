package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHREmployeeChildren extends X_HR_Employee_Children{
	private static final long serialVersionUID = 7448052037738596413L;
	
	public MHREmployeeChildren(Properties ctx, int HR_Employee_Children_ID, String trxName) {
		super(ctx, HR_Employee_Children_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHREmployeeChildren(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
