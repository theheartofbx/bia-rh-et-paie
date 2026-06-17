package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRDestinataireRole extends X_HR_DestinataireRole{
	private static final long serialVersionUID = -4638487694470852974L;

	public MHRDestinataireRole(Properties ctx, int HR_DestinataireRole_ID, String trxName) {
		super(ctx, HR_DestinataireRole_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRDestinataireRole(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
