package org.sitracel.notification;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.sitracel.enumeration.NotificationStatut;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;

public class NotificationCacheService {

    private static final Map<String, Integer> CACHE_STATUT =
            new ConcurrentHashMap<String, Integer>();

    private static final Map<String, Integer> CACHE_DEST_TYPE =
            new ConcurrentHashMap<String, Integer>();

    public static int getStatutId(NotificationStatut statut) {

        if (statut == null) {
            return 0;
        }

        Integer id = CACHE_STATUT.get(statut.getCode());

        if (id == null) {
            id = NotificationSqlControler
                    .loadNotificationStatutIdFromDB(
                            statut.getCode()
                    );

            CACHE_STATUT.put(statut.getCode(), id);
        }

        return id;
    }

    public static int getDestinataireTypeId(
            NotificationTypeDestinataireEmail type
    ) {

        if (type == null) {
            return 0;
        }

        Integer id = CACHE_DEST_TYPE.get(type.getValue());

        if (id == null) {
            id = NotificationSqlControler
                    .loadDestinataireTypeFromDB(
                            type.getValue()
                    );

            CACHE_DEST_TYPE.put(type.getValue(), id);
        }

        return id;
    }
}
