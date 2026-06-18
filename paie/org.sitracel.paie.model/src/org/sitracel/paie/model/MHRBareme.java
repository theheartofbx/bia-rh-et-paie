package org.sitracel.paie.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRBareme extends X_HR_Bareme{
	private static final long serialVersionUID = -5298566531150007427L;

	public MHRBareme(Properties ctx, int HR_Bareme_ID, String trxName) {
		super(ctx, HR_Bareme_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRBareme(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
