package org.sitracel.enumeration;

import java.util.Arrays;

public enum NotificationCible {

    EMPLOYE("EMPLOYE"),
    SUPERIEUR("SUPERIEUR"),
    EMETTEUR("EMETTEUR"),
    ROLE("ROLE"),
    CATEGORIE_RESP("CATEGORIE_RESP");

    private final String value;

    NotificationCible(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    /* ==========================
     * FACTORY SAFE
     * ========================== */

    public static NotificationCible fromName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        return Arrays.stream(values())
                .filter(v -> v.value.equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
