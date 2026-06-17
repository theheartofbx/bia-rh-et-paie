package org.sitracel.enumeration;

public enum NotificationStatut {

    CREATED   ("CREATED",   "Créée"),
    QUEUED    ("QUEUED",    "En file d'attente"),
    SENT      ("SENT",      "Envoyée"),
    FAILED    ("FAILED",    "Erreur"),
    READ      ("READ",      "Lue"),
    CANCELLED ("CANCELLED", "Annulée");

    private final String code;
    private final String label;

    NotificationStatut(String code, String label) {
        this.code = code;
        this.label = label;
    }

    /** Code technique (clé unique DB) */
    public String getCode() {
        return code;
    }

    /** Libellé métier (affichage) */
    public String getLabel() {
        return label;
    }
}
