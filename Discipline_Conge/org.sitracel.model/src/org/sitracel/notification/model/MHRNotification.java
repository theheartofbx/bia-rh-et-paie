package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotification extends X_HR_Notification{
	private static final long serialVersionUID = 3915801909186061966L;

	public MHRNotification(Properties ctx, int HR_Notification_ID, String trxName) {
		super(ctx, HR_Notification_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotification(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
