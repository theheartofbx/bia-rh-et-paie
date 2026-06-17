package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MADRole extends X_AD_Role{
	private static final long serialVersionUID = 892004141771792822L;

	public MADRole(Properties ctx, int AD_Role_ID, String trxName) {
		super(ctx, AD_Role_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MADRole(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
