package org.sitracel.notification.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.CLogger;
import org.compiere.util.DB;

public class MHRNotificationQueue extends X_HR_NotificationQueue {

    private static final long serialVersionUID = 1543037532339938333L;
    private static final CLogger log = CLogger.getCLogger(MHRNotificationQueue.class);

    public MHRNotificationQueue(Properties ctx, int HR_NotificationQueue_ID, String trxName) {
        super(ctx, HR_NotificationQueue_ID, trxName);
    }

    public MHRNotificationQueue(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    public static List<MHRNotificationQueue> getNew(Properties ctx, String trxName) {

        List<MHRNotificationQueue> result = new ArrayList<>();

        String sql =
            "SELECT q.HR_NotificationQueue_ID "
            + "FROM adempiere.HR_NotificationQueue q "
            + "JOIN adempiere.HR_NotificationStatut s "
            + "  ON s.HR_NotificationStatut_ID = q.HR_NotificationStatut_ID "
            + "WHERE s.Name = 'Créée' "
            + "AND q.IsActive = 'Y' "
            + "ORDER BY q.Created ASC "
            ;

        List<Integer> ids = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            rs = pstmt.executeQuery();
            int maxRows = 0;
            while (rs.next() && maxRows < 100) {
                ids.add(rs.getInt(1));
                maxRows++;
                maxRows++;
            }
        } catch (Exception e) {
            log.warning("MHRNotificationQueue.getNew: " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }

        for (int id : ids) {
            result.add(new MHRNotificationQueue(ctx, id, trxName));
        }

        return result;
    }
}
