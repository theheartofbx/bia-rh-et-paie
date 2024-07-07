package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRPublicHoliday extends X_HR_Public_Holiday{
	private static final long serialVersionUID = 2025386280248483837L;
	
	public MHRPublicHoliday(Properties ctx, int HR_Public_Holiday_ID, String trxName) {
		super(ctx, HR_Public_Holiday_ID, trxName);
		// TODO Auto-generated constructor stub
	}
	public MHRPublicHoliday(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}


}
