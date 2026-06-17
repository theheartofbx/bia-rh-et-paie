package org.sitracel.bean;

import java.util.Objects;

import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;

public class BeanDestinataire {

    /** Personne cible */
    private final int cBPartnerId;

    /** Valeur du canal (email, téléphone, token, etc.) */
    private final String canalValue;

    /** Type de canal : EMAIL / SMS / WHATSAPP / ... */
    private final NotificationCanal canal;

    /** Rôle logique dans la notification : TO / CC / BCC */
    private final NotificationTypeDestinataireEmail type;

    /* ==========================
     * CONSTRUCTEUR
     * ========================== */

    public BeanDestinataire(
            int cBPartnerId,
            String canalValue,
            NotificationCanal canal,
            NotificationTypeDestinataireEmail type
    ) {
        if (cBPartnerId <= 0) {
            throw new IllegalArgumentException("BPartner invalide");
        }

        this.cBPartnerId = cBPartnerId;
        this.canalValue = canalValue != null ? canalValue.trim() : null;
        this.canal = Objects.requireNonNull(canal, "Canal obligatoire");
        this.type = Objects.requireNonNull(type, "Type destinataire obligatoire");
    }

    /* ==========================
     * GETTERS
     * ========================== */

    public int getCBPartnerId() {
        return cBPartnerId;
    }

    public String getCanalValue() {
        return canalValue;
    }

    public NotificationCanal getCanal() {
        return canal;
    }

    public NotificationTypeDestinataireEmail getType() {
        return type;
    }

    /* ==========================
     * EQUALITY
     * ========================== */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeanDestinataire)) return false;

        BeanDestinataire that = (BeanDestinataire) o;

        return cBPartnerId == that.cBPartnerId
            && canal == that.canal
            && type == that.type
            && Objects.equals(canalValue, that.canalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cBPartnerId, canalValue, canal, type);
    }
}
