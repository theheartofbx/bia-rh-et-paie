package org.sitracel.notification;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.util.Env;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.model.MHRNotificationType;

public class NotificationTypeService {

    private static final Map<String, Integer> CACHE =
            new ConcurrentHashMap<String, Integer>();

    public static MHRNotificationType getByEvent(
            NotificationEvent event,
            String trxName
    ) {

        if (event == null) {
            return null;
        }

        Integer id = CACHE.get(event.name());

        if (id == null) {
            id = NotificationSqlControler
                    .loadNotificationTypeFromDB(event.name());

            if (id == null || id <= 0) {
                return null;
            }

            CACHE.put(event.name(), id);
        }

        MHRNotificationType type =
                new MHRNotificationType(
                        Env.getCtx(),
                        id,
                        trxName
                );

        return type.getName() != null ? type : null;
    }
}

