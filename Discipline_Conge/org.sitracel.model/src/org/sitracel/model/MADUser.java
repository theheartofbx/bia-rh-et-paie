package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MADUser extends X_AD_User{
	private static final long serialVersionUID = 8831579773165178797L;

	public MADUser(Properties ctx, int AD_User_ID, String trxName) {
		super(ctx, AD_User_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MADUser(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
