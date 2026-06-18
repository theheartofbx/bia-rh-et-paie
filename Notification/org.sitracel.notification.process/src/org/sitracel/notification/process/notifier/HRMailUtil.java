package org.sitracel.notification.process.notifier;

import java.util.Properties;

import org.compiere.model.MClient;
import org.compiere.util.EMail;
import org.compiere.util.Env;

/**
 * Utilitaire de création d'emails via la configuration SMTP d'iDempiere.
 *
 * iDempiere stocke la configuration SMTP dans AD_Client :
 *   - SMTPHost, SMTPPort
 *   - RequestEMail (expéditeur)
 *   - RequestUser / RequestUserPW (authentification)
 *
 * Aucun mot de passe en dur dans le code — tout vient de AD_Client.
 */
public final class HRMailUtil {

    private HRMailUtil() {}

    /**
     * Crée un email prêt à envoyer via la config SMTP du client iDempiere.
     *
     * @param ctx     Contexte iDempiere
     * @param to      Adresse email du destinataire
     * @param subject Objet de l'email
     * @param body    Corps de l'email (texte brut)
     */
    public static EMail createMail(
            Properties ctx,
            String to,
            String subject,
            String body
    ) {
        MClient client = MClient.get(ctx);

        EMail email = client.createEMail(to, subject, body);

        if (email == null) {
            throw new IllegalStateException(
                "Impossible de créer l'email — vérifiez la configuration SMTP "
                + "dans Administration > Client > onglet Email"
            );
        }

        return email;
    }
}
