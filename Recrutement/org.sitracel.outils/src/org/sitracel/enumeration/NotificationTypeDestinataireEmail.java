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

    public static NotificationTypeDestinataireEmail fromValue(String value) {
        if (value == null || value.isBlank()) return null;
        return Arrays.stream(values())
                .filter(t -> t.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
