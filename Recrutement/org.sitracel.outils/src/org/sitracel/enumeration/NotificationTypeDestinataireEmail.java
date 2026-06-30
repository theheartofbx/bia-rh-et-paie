package org.sitracel.enumeration;

import java.util.Arrays;

public enum NotificationTypeDestinataireEmail {

    TO("TO", 1),
    CC("CC", 2),
    BCC("BCC", 3);

    private final String value;
    private final int priority;

    NotificationTypeDestinataireEmail(String value, int priority) {
        this.value = value;
        this.priority = priority;
    }

    public String getValue() {
        return value;
    }

    public int getPriority() {
        return priority;
    }


    /**
     * Résout un type destinataire depuis son ID en base (HR_DestinataireType).
     * Requête SQL directe — pas de dépendance vers notification.model.
     */
    public static NotificationTypeDestinataireEmail fromId(int hrDestinataireTypeId) {
        if (hrDestinataireTypeId <= 0) return null;
        String sql = "SELECT Name FROM HR_DestinataireType WHERE HR_DestinataireType_ID = ?";
        String name = org.compiere.util.DB.getSQLValueString(null, sql, hrDestinataireTypeId);
        return fromValue(name);
    }

    public static NotificationTypeDestinataireEmail fromValue(String value) {
        if (value == null || value.isBlank()) return null;
        return Arrays.stream(values())
                .filter(t -> t.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
