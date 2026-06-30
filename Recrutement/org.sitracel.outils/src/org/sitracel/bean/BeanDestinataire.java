package org.sitracel.bean;

import java.util.Objects;

import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;

public class BeanDestinataire {

    private int cBPartnerId;
    private String canalValue;
    private NotificationCanal canal;
    private NotificationTypeDestinataireEmail type;

    /* ==========================
     * CONSTRUCTEURS
     * ========================== */

    /** Constructeur sans args — pour usage avec setters */
    public BeanDestinataire() {}
    /** Constructeur à 3 args — canalValue déduit plus tard */
    public BeanDestinataire(
            int cBPartnerId,
            NotificationCanal canal,
            NotificationTypeDestinataireEmail type
    ) {
        this.cBPartnerId = cBPartnerId;
        this.canal       = canal;
        this.type        = type;
    }



    /** Constructeur complet */
    public BeanDestinataire(
            int cBPartnerId,
            String canalValue,
            NotificationCanal canal,
            NotificationTypeDestinataireEmail type
    ) {
        this.cBPartnerId = cBPartnerId;
        this.canalValue  = canalValue != null ? canalValue.trim() : null;
        this.canal       = canal;
        this.type        = type;
    }

    /* ==========================
     * GETTERS
     * ========================== */

    public int getCBPartnerId()                      { return cBPartnerId; }
    public String getCanalValue()                    { return canalValue; }
    public NotificationCanal getCanal()              { return canal; }
    public NotificationTypeDestinataireEmail getType() { return type; }

    /* ==========================
     * SETTERS
     * ========================== */

    public void setCBPartnerId(int cBPartnerId)      { this.cBPartnerId = cBPartnerId; }
    public void setCanalValue(String canalValue)     { this.canalValue = canalValue; }
    public void setCanal(NotificationCanal canal)    { this.canal = canal; }
    public void setType(NotificationTypeDestinataireEmail type) { this.type = type; }

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
            && type  == that.type
            && Objects.equals(canalValue, that.canalValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cBPartnerId, canalValue, canal, type);
    }
}
