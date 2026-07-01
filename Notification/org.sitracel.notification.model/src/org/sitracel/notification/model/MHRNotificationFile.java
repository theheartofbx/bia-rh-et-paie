package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MHRNotificationFile extends X_HR_NotificationFile{
private static final long serialVersionUID = 1L;

public MHRNotificationFile(Properties ctx, int HR_NotificationFile_ID, String trxName) {
super(ctx, HR_NotificationFile_ID, trxName);
// TODO Auto-generated constructor stub
}

public MHRNotificationFile(Properties ctx, ResultSet rs, String trxName) {
super(ctx, rs, trxName);
// TODO Auto-generated constructor stub
}

}
