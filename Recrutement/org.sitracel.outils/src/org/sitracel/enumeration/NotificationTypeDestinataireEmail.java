package org.sitracel.enumeration;

import java.util.Arrays;

import org.compiere.util.Env;
import org.sitracel.notification.model.MHRDestinataireType;

public enum NotificationTypeDestinataireEmail {

    TO("TO", 1),     // action requise
    CC("CC", 2),     // informatif
    BCC("BCC", 3);   // invisible

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

    /* ==========================
     * RESOLUTION DB → ENUM
     * ========================== */

    public static NotificationTypeDestinataireEmail fromId(
            int hrDestinataireTypeId
    ) {

        if (hrDestinataireTypeId <= 0) {
            return null;
        }

        MHRDestinataireType type =
            new MHRDestinataireType(
                Env.getCtx(),
                hrDestinataireTypeId,
                null
            );

        if (type.get_ID() <= 0) {
            return null;
        }

        return fromValue(type.getName());
    }

    public static NotificationTypeDestinataireEmail fromValue(
            String value
    ) {

        if (value == null || value.isBlank()) {
            return null;
        }

        return Arrays.stream(values())
                .filter(t -> t.value.equalsIgnoreCase(value))
                .findFirst()
                .orElse(null);
    }
}
