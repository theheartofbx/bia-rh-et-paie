package org.sitracel.notification.gestionmodele;

import org.compiere.util.Env;
import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.model.MCBPartner;
import org.sitracel.notification.NotificationControler;

public final class NotificationGestionCanal {

    private NotificationGestionCanal() {
        // utilitaire
    }

    /**
     * Retourne la valeur du canal pour un BPartner
     * (email, téléphone, token, etc.)
     */
    public static String gererCanal(
            int bpartnerId,
            NotificationCanal canal
    ) {

        if (bpartnerId <= 0 || canal == null) {
            return null;
        }

        switch (canal) {

            case EMAIL:
                return getEmailByBPartner(bpartnerId);

            case SMS:
                // TODO
                // return GeneralSqlController.getMobileByBPartner(bpartnerId);
                return null;

            case WHATSAPP:
                // TODO
                // return GeneralSqlController.getMobileByBPartner(bpartnerId);
                return null;

            default:
                return null;
        }
    }
    

	public static String getEmailByBPartner(int cBPartnerId) {
	
	    if (cBPartnerId <= 0) {
	        return null;
	    }
	
	    MCBPartner bp = new MCBPartner(
	            Env.getCtx(),
	            cBPartnerId,
	            null
	    );
	
	    String email = bp.getEMail();
	
	    return (email != null && !email.isBlank())
	            ? email.trim()
	            : null;
	}
}
