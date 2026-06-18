package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationHistorique extends X_HR_NotificationHistorique{
	private static final long serialVersionUID = 259528097878844061L;

	public MHRNotificationHistorique(Properties ctx, int HR_NotificationHistorique_ID, String trxName) {
		super(ctx, HR_NotificationHistorique_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationHistorique(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
