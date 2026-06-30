package org.sitracel.enumeration;

import java.util.Arrays;

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


    /**
     * Résout un canal depuis son ID en base (HR_NotificationCanal).
     * Requête SQL directe — pas de dépendance vers notification.model.
     */
    public static NotificationCanal fromId(int hrNotificationCanalId) {
        if (hrNotificationCanalId <= 0) return null;
        String sql = "SELECT Name FROM HR_NotificationCanal WHERE HR_NotificationCanal_ID = ?";
        String name = org.compiere.util.DB.getSQLValueString(null, sql, hrNotificationCanalId);
        return fromValue(name);
    }

    public static NotificationCanal fromValue(String value) {
        if (value == null || value.isBlank()) return null;
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
