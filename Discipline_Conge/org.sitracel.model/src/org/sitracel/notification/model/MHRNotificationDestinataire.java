package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationDestinataire extends X_HR_NotificationDestinataire{
	private static final long serialVersionUID = 9055567038518829435L;

	public MHRNotificationDestinataire(Properties ctx, int HR_NotificationDestinataire_ID, String trxName) {
		super(ctx, HR_NotificationDestinataire_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MHRNotificationDestinataire(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
