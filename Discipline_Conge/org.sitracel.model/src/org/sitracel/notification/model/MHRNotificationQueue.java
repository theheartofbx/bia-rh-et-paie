package org.sitracel.notification.model;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Modèle de la file d'attente des notifications.
 *
 * Une entrée par notification à envoyer.
 * Le processus SitracelProcessNotifier traite toutes les entrées
 * au statut "Créée" (CREATED).
 */
public class MHRNotificationQueue extends X_HR_NotificationQueue {

    private static final long serialVersionUID = 1543037532339938333L;

    public MHRNotificationQueue(
            Properties ctx, int HR_NotificationQueue_ID, String trxName) {
        super(ctx, HR_NotificationQueue_ID, trxName);
    }

    public MHRNotificationQueue(
            Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    // =========================================================================
    // MÉTHODES STATIQUES
    // =========================================================================

    /**
     * Retourne toutes les notifications en attente d'envoi (statut "Créée").
     * Limitées à 100 par exécution pour éviter les surcharges.
     *
     * @param ctx     Contexte iDempiere
     * @param trxName Nom de la transaction
     * @return Liste des entrées de queue à traiter
     */
    public static List<MHRNotificationQueue> getNew(
            Properties ctx, String trxName) {

        List<MHRNotificationQueue> result = new ArrayList<>();

        // Statut "Créée" = ID 1000000 (inséré lors de l'initialisation)
        // On passe par le name pour être robuste
        String sql =
            "SELECT q.HR_NotificationQueue_ID "
            + "FROM adempiere.HR_NotificationQueue q "
            + "JOIN adempiere.HR_NotificationStatut s "
            + "  ON s.HR_NotificationStatut_ID = q.HR_NotificationStatut_ID "
            + "WHERE s.Name = 'Créée' "
            + "AND q.IsActive = 'Y' "
            + "ORDER BY q.Created ASC "
            + "LIMIT 100";

        List<Integer> ids = new ArrayList<>();
        DB.query(sql, null, rs -> {
            try {
                ids.add(rs.getInt(1));
            } catch (Exception ignored) {}
        }, trxName);

        for (int id : ids) {
            result.add(new MHRNotificationQueue(ctx, id, trxName));
        }

        return result;
    }

    /**
     * Raccourcis pour la gestion du statut.
     * Utilisés par SitracelProcessNotifier après chaque tentative d'envoi.
     */
    public void setStatus(int statutId) {
        setHR_NotificationStatut_ID(statutId);
    }

    public void setErrorMsg(String msg) {
        // Stocké dans le champ Message de la queue
        if (msg != null && msg.length() > 2000) {
            msg = msg.substring(0, 2000);
        }
        setMessage(msg);
    }

    // Alias pour la compatibilité avec SitracelProcessNotifier
    public MHRNotification getHR_Notification() {
        int id = getHR_Notification_ID();
        if (id <= 0) return null;
        return new MHRNotification(Env.getCtx(), id, get_TrxName());
    }
}
