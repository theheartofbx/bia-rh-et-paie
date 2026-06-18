package org.sitracel.notification.process.notifier;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.notification.model.MHRNotificationTemplate;

/**
 * Charge le template d'une notification depuis la base de données.
 *
 * Stratégie de recherche :
 *   1. Template exact pour la langue demandée (ex: fr_FR)
 *   2. Template sans langue (AD_Language IS NULL)
 *   3. N'importe quel template actif pour ce type
 */
public final class HRNotificationTemplateUtil {

    private HRNotificationTemplateUtil() {}

    public static MHRNotificationTemplate getTemplate(
            int notificationTypeId,
            String adLanguage,
            String trxName
    ) {
        if (notificationTypeId <= 0) return null;

        // Recherche par langue exacte
        int templateId = DB.getSQLValueEx(trxName,
            "SELECT HR_NotificationTemplate_ID "
            + "FROM adempiere.HR_NotificationTemplate "
            + "WHERE HR_NotificationType_ID = ? "
            + "AND IsActive = 'Y' "
            + "AND (AD_Language = ? OR AD_Language IS NULL) "
            + "ORDER BY CASE WHEN AD_Language = ? THEN 0 ELSE 1 END "
            + "LIMIT 1",
            notificationTypeId, adLanguage, adLanguage
        );

        if (templateId <= 0) {
            // Fallback : n'importe quel template actif
            templateId = DB.getSQLValueEx(trxName,
                "SELECT HR_NotificationTemplate_ID "
                + "FROM adempiere.HR_NotificationTemplate "
                + "WHERE HR_NotificationType_ID = ? "
                + "AND IsActive = 'Y' "
                + "LIMIT 1",
                notificationTypeId
            );
        }

        if (templateId <= 0) return null;

        return new MHRNotificationTemplate(Env.getCtx(), templateId, trxName);
    }
}
