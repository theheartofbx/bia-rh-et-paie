package org.sitracel.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MADUserRoles extends X_AD_User_Roles{
	private static final long serialVersionUID = -8008143532665548188L;

	public MADUserRoles(Properties ctx, int AD_User_Roles_ID, String trxName) {
		super(ctx, AD_User_Roles_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MADUserRoles(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
