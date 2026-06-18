package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;

/**
 * Modèle des destinataires d'une notification.
 *
 * Chaque notification peut avoir plusieurs destinataires
 * (TO, CC, BCC) avec leurs adresses email.
 */
public class MHRNotificationDestinataire extends X_HR_NotificationDestinataire {

    private static final long serialVersionUID = 9055567038518829435L;

    public MHRNotificationDestinataire(
            Properties ctx, int HR_NotificationDestinataire_ID, String trxName) {
        super(ctx, HR_NotificationDestinataire_ID, trxName);
    }

    public MHRNotificationDestinataire(
            Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    // =========================================================================
    // MÉTHODES STATIQUES
    // =========================================================================

    /**
     * Retourne tous les destinataires d'une notification donnée.
     *
     * @param notificationId ID de la notification mère
     * @param trxName        Nom de la transaction
     * @return Liste des destinataires
     */
    public static List<MHRNotificationDestinataire> getByNotification(
            int notificationId, String trxName) {

        List<MHRNotificationDestinataire> result = new ArrayList<>();

        if (notificationId <= 0) return result;

        String sql =
            "SELECT HR_NotificationDestinataire_ID "
            + "FROM adempiere.HR_NotificationDestinataire "
            + "WHERE HR_Notification_ID = ? "
            + "AND IsActive = 'Y' "
            + "ORDER BY HR_NotificationDestinataire_ID";

        List<Integer> ids = new ArrayList<>();
        DB.query(sql, new Object[]{notificationId}, rs -> {
            try {
                ids.add(rs.getInt(1));
            } catch (Exception ignored) {}
        }, trxName);

        for (int id : ids) {
            result.add(new MHRNotificationDestinataire(
                org.compiere.util.Env.getCtx(), id, trxName
            ));
        }

        return result;
    }
}
