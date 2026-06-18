package org.sitracel.enumeration;

/**
 * Tous les événements métier déclenchant une notification.
 * Le code = clé dans HR_NotificationType.Value en base.
 */
public enum NotificationEvent {

    /* MISSIONS */
    MISSION_CREATED("MISSION_CREATED", "Mission - Création"),
    MISSION_VALIDATED("MISSION_VALIDATED", "Mission - Validation"),
    MISSION_REJECTED("MISSION_REJECTED", "Mission - Rejet"),
    MISSION_EMPLOYEE_ASSIGNED("MISSION_EMPLOYEE_ASSIGNED", "Mission - Affectation"),
    MISSION_EMPLOYEE_UPDATED("MISSION_EMPLOYEE_UPDATED", "Mission - Modification affectation"),
    MISSION_EMPLOYEE_REMOVED("MISSION_EMPLOYEE_REMOVED", "Mission - Suppression affectation"),
    MISSION_EMPLOYEE_CANCELLED("MISSION_EMPLOYEE_CANCELLED", "Mission - Annulation affectation"),

    /* DEMANDE D'EXPLICATION */
    DEMANDE_EXPLICATION_CREATED("DEMANDE_EXPLICATION_CREATED", "Demande explication - Création"),
    DEMANDE_EXPLICATION_REPLIED("DEMANDE_EXPLICATION_REPLIED", "Demande explication - Réponse"),

    /* SANCTIONS */
    SANCTION_CREATED("SANCTION_CREATED", "Sanction - Création"),
    SANCTION_APPROVED("SANCTION_APPROVED", "Sanction - Approbation"),
    SANCTION_DISAPPROVED("SANCTION_DISAPPROVED", "Sanction - Désapprobation"),
    SANCTION_VALIDATED("SANCTION_VALIDATED", "Sanction - Validation"),
    SANCTION_REJECTED("SANCTION_REJECTED", "Sanction - Rejet"),

    /* CONGÉS */
    HOLIDAY_CREATED("HOLIDAY_CREATED", "Congé - Création"),
    HOLIDAY_APPROVED("HOLIDAY_APPROVED", "Congé - Approbation"),
    HOLIDAY_DISAPPROVED("HOLIDAY_DISAPPROVED", "Congé - Désapprobation"),
    HOLIDAY_VALIDATED("HOLIDAY_VALIDATED", "Congé - Validation"),
    HOLIDAY_REJECTED("HOLIDAY_REJECTED", "Congé - Rejet");

    /*
     * RECRUTEMENT — décommenter quand le module sera branché :
     * OFFRE_EMPLOI_PUBLIEE("OFFRE_EMPLOI_PUBLIEE", "Offre d'emploi - Publication"),
     * CANDIDATURE_RECUE("CANDIDATURE_RECUE", "Candidature - Réception"),
     * CANDIDATURE_RETENUE("CANDIDATURE_RETENUE", "Candidature - Retenue"),
     * CANDIDATURE_REJETEE("CANDIDATURE_REJETEE", "Candidature - Rejet"),
     */

    private final String code;
    private final String notificationTypeName;

    NotificationEvent(String code, String notificationTypeName) {
        this.code = code;
        this.notificationTypeName = notificationTypeName;
    }

    public String getCode() { return code; }
    public String getNotificationTypeName() { return notificationTypeName; }
}
