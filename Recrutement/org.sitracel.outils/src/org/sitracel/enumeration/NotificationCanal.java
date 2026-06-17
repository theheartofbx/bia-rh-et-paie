package org.sitracel.enumeration;

import java.util.Arrays;

import org.compiere.util.Env;
import org.sitracel.notification.model.MHRNotificationCanal;

public enum NotificationCanal {

    EMAIL("EMAIL"),
    SMS("SMS"),
    WHATSAPP("WHATSAPP");

    private final String value;

    NotificationCanal(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /* ==========================
     * RESOLUTION DB → ENUM
     * ========================== */

    public static NotificationCanal fromId(int hrNotificationCanalId) {

        if (hrNotificationCanalId <= 0) {
            return null;
        }

        MHRNotificationCanal canal =
            new MHRNotificationCanal(
                Env.getCtx(),
                hrNotificationCanalId,
                null
            );

        if (canal.get_ID() <= 0) {
            return null;
        }

        return fromValue(canal.getName());
    }

    public static NotificationCanal fromValue(String value) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
