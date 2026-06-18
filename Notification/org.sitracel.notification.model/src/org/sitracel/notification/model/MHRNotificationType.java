package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationType extends X_HR_NotificationType{
	private static final long serialVersionUID = 5812878820120839261L;

	public MHRNotificationType(Properties ctx, int HR_NotificationType_ID, String trxName) {
		super(ctx, HR_NotificationType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
