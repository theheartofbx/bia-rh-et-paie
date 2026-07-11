package org.sitracel.notification.gestionmodele;

import org.compiere.util.Env;
import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.model.MCBPartner;

/**
 * Résout la valeur d'un canal de notification pour un destinataire donné.
 *
 *   EMAIL    → C_BPartner.EMail    ✅ Implémenté
 *   WHATSAPP → C_BPartner.Phone   🔒 En attente compte WhatsApp Business
 *   SMS      → C_BPartner.Phone   🔒 Prévu, non prioritaire
 *
 * Pour activer WhatsApp : décommenter le return dans le case WHATSAPP
 * et créer un envoyeur dans SitracelProcessNotifier.
 */
public final class NotificationGestionCanal {

    private NotificationGestionCanal() {}

    public static String gererCanal(int bpartnerId, NotificationCanal canal) {
        if (bpartnerId <= 0 || canal == null) return null;
        switch (canal) {
            case EMAIL:
                return getEmailByBPartner(bpartnerId);
            case WHATSAPP:
                // 🔒 En attente compte WhatsApp Business
                // Décommenter quand disponible :
                // return getPhoneByBPartner(bpartnerId);
                return null;
            case SMS:
                // 🔒 Prévu — provider SMS à choisir (Twilio, Orange API...)
                // Décommenter quand disponible :
                // return getPhoneByBPartner(bpartnerId);
                return null;
            default:
                return null;
        }
    }

    public static String getEmailByBPartner(int cBPartnerId) {
        if (cBPartnerId <= 0) return null;
        MCBPartner bp = new MCBPartner(Env.getCtx(), cBPartnerId, null);
        String email = bp.getEMail();
        return (email != null && !email.trim().isEmpty()) ? email.trim() : null;
    }

    // Mutualisé WhatsApp + SMS — format attendu : +237XXXXXXXXX
    @SuppressWarnings("unused")
    private static String getPhoneByBPartner(int cBPartnerId) {
        if (cBPartnerId <= 0) return null;
        MCBPartner bp = new MCBPartner(Env.getCtx(), cBPartnerId, null);
        String phone = bp.getPhone();
        return (phone != null && !phone.trim().isEmpty()) ? phone.trim() : null;
    }
}
