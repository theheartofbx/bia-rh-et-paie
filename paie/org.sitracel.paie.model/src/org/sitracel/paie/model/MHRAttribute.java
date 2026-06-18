package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRAttribute extends X_HR_Attribute{
	private static final long serialVersionUID = 2084248259956496925L;

	public MHRAttribute(Properties ctx, int HR_Attribute_ID, String trxName) {
		super(ctx, HR_Attribute_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRAttribute(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
