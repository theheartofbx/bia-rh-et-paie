package org.sitracel.notification.template;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.util.Env;
import org.sitracel.notification.NotificationSqlControler;
import org.sitracel.notification.model.MHRNotificationTemplate;

public class NotificationTemplateService {

    private static final Map<Integer, Integer> CACHE =
            new ConcurrentHashMap<Integer, Integer>();

    public static MHRNotificationTemplate getByNotificationType(
            int notificationTypeId,
            String trxName
    ) {

        if (notificationTypeId <= 0) {
            return null;
        }

        Integer templateId = CACHE.get(notificationTypeId);

        if (templateId == null) {

            templateId =
                NotificationSqlControler
                    .loadTemplateIdByNotificationType(
                        notificationTypeId
                    );

            if (templateId == null || templateId <= 0) {
                return null;
            }

            CACHE.put(notificationTypeId, templateId);
        }

        MHRNotificationTemplate template =
                new MHRNotificationTemplate(
                        Env.getCtx(),
                        templateId,
                        trxName
                );

        return template.isActive() ? template : null;
    }
}
