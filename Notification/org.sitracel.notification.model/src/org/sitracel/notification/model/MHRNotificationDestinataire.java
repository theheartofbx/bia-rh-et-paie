package org.sitracel.notification.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MHRNotificationDestinataire extends X_HR_NotificationDestinataire {

    private static final long serialVersionUID = 9055567038518829435L;
    private static final CLogger log = CLogger.getCLogger(MHRNotificationDestinataire.class);

    public MHRNotificationDestinataire(Properties ctx, int HR_NotificationDestinataire_ID, String trxName) {
        super(ctx, HR_NotificationDestinataire_ID, trxName);
    }

    public MHRNotificationDestinataire(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static List<MHRNotificationDestinataire> getByNotification(int notificationId, String trxName) {

        List<MHRNotificationDestinataire> result = new ArrayList<>();

        if (notificationId <= 0) return result;

        String sql =
            "SELECT HR_NotificationDestinataire_ID "
            + "FROM adempiere.HR_NotificationDestinataire "
            + "WHERE HR_Notification_ID = ? "
            + "AND IsActive = 'Y' "
            + "ORDER BY HR_NotificationDestinataire_ID";

        List<Integer> ids = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, notificationId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                ids.add(rs.getInt(1));
            }
        } catch (Exception e) {
            log.warning("MHRNotificationDestinataire.getByNotification: " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        for (int id : ids) {
            result.add(new MHRNotificationDestinataire(Env.getCtx(), id, trxName));
        }

        return result;
    }
}
