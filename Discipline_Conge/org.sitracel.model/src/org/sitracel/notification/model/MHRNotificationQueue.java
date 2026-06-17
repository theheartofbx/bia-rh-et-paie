package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationQueue extends X_HR_NotificationQueue{
	private static final long serialVersionUID = 1543037532339938333L;

	public MHRNotificationQueue(Properties ctx, int HR_NotificationQueue_ID, String trxName) {
		super(ctx, HR_NotificationQueue_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationQueue(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
