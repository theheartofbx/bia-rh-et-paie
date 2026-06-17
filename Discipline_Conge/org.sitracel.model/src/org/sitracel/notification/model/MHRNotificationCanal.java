package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationCanal extends X_HR_NotificationCanal{
	private static final long serialVersionUID = -2685218551478611620L;

	public MHRNotificationCanal(Properties ctx, int HR_NotificationCanal_ID, String trxName) {
		super(ctx, HR_NotificationCanal_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationCanal(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
