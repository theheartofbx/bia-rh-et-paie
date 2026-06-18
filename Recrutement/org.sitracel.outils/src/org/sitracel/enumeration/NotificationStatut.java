package org.sitracel.enumeration;

/**
 * Statuts du cycle de vie d'une notification.
 *
 * IMPORTANT : le code doit correspondre exactement au champ Name
 * dans la table HR_NotificationStatut en base de données.
 *
 * Correspondance base :
 *   CREATED   → "Créée"
 *   QUEUED    → "En queue"
 *   SENT      → "Envoyée"
 *   ERROR     → "Erreur"
 *   FAILED    → "Échouée"
 *   READ      → "Lue"
 *   CANCELLED → "Annulée"
 */
public enum NotificationStatut {

    CREATED   ("Créée",     "Notification créée, en attente de traitement"),
    QUEUED    ("En queue",  "Notification prise en charge par le processus"),
    SENT      ("Envoyée",   "Notification envoyée avec succès"),
    ERROR     ("Erreur",    "Échec d'envoi — nouvelle tentative prévue"),
    FAILED    ("Échouée",   "Nombre maximum de tentatives atteint — abandon"),
    READ      ("Lue",       "Notification consultée par le destinataire"),
    CANCELLED ("Annulée",   "Notification annulée manuellement");

    /** Correspond exactement au Name en base HR_NotificationStatut */
    private final String code;
    private final String description;

    NotificationStatut(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode()        { return code; }
    public String getDescription() { return description; }
}
