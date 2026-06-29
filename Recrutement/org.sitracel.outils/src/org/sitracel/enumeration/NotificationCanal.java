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

    public static NotificationCanal fromValue(String value) {
        if (value == null || value.isBlank()) return null;
        return Arrays.stream(values())
                .filter(c -> c.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
