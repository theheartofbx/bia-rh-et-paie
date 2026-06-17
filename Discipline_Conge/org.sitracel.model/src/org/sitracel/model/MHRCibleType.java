package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRCibleType extends X_HR_CibleType{
	private static final long serialVersionUID = -6185578990815141L;

	public MHRCibleType(Properties ctx, int HR_CibleType_ID, String trxName) {
		super(ctx, HR_CibleType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRCibleType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
