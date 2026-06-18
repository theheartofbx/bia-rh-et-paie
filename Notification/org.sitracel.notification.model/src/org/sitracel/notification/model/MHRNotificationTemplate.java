package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationTemplate extends X_HR_NotificationTemplate{
	private static final long serialVersionUID = -2731526838932179218L;

	public MHRNotificationTemplate(Properties ctx, int HR_NotificationTemplate_ID, String trxName) {
		super(ctx, HR_NotificationTemplate_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationTemplate(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
