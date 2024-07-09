package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRHoliday extends X_HR_Holiday{
	private static final long serialVersionUID = -468614103206511090L;
	
	public MHRHoliday(Properties ctx, int HR_Holiday_ID, String trxName) {
		super(ctx, HR_Holiday_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRHoliday(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
