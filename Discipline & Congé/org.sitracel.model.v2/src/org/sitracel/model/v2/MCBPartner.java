package org.sitracel.model.v2;

import java.sql.ResultSet;
import java.util.Properties;

public class MCBPartner extends X_C_BPartner{
	private static final long serialVersionUID = 1610077844542696959L;

	public MCBPartner(Properties ctx, int C_BPartner_ID, String trxName) {
		super(ctx, C_BPartner_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MCBPartner(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
}
