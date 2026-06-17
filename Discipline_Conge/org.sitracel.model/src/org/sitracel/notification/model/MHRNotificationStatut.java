package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationStatut extends X_HR_NotificationStatut{
	private static final long serialVersionUID = -278046585801147343L;

	public MHRNotificationStatut(Properties ctx, int HR_NotificationStatut_ID, String trxName) {
		super(ctx, HR_NotificationStatut_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationStatut(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
