package org.sitracel.notification.process.notifier;

import java.util.List;
import java.util.Map;

import org.compiere.process.SvrProcess;
import org.compiere.util.CLogger;
import org.sitracel.enumeration.NotificationStatut;
import org.sitracel.notification.model.MHRNotification;
import org.sitracel.notification.model.MHRNotificationDestinataire;
import org.sitracel.notification.model.MHRNotificationQueue;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;
import org.compiere.util.EMail;

/**
 * Processus planifiable d'envoi des notifications en queue.
 *
 * À planifier dans iDempiere (AD_Scheduler) toutes les X minutes.
 * Peut aussi être lancé manuellement.
 *
 * Traite toutes les entrées au statut "Créée" dans HR_NotificationQueue.
 * En cas d'échec : incrémente Nombre_Tentative, passe au statut "Erreur".
 * Après MAX_TENTATIVES échecs : passe au statut "Échouée" (abandon).
 */
public class SitracelProcessNotifier extends SvrProcess {

    private static final CLogger log =
        CLogger.getCLogger(SitracelProcessNotifier.class);

    private static final int MAX_TENTATIVES = 3;

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {

        List<MHRNotificationQueue> queue =
            MHRNotificationQueue.getNew(getCtx(), get_TrxName());

        if (queue == null || queue.isEmpty()) {
            return "Aucune notification en attente.";
        }

        int envoyes    = 0;
        int erreurs    = 0;
        int abandonnes = 0;
        String dernierMessageErreur = null;

        for (MHRNotificationQueue q : queue) {
            try {
                if (q.getNombre_Tentative() >= MAX_TENTATIVES) {
                    q.setHR_NotificationStatut_ID(
                        HRNotificationStatutUtil.getStatutId(
                            NotificationStatut.FAILED, get_TrxName()
                        )
                    );
                    q.saveEx();
                    abandonnes++;
                    continue;
                }

                sendOne(q);

                q.setHR_NotificationStatut_ID(
                    HRNotificationStatutUtil.getStatutId(
                        NotificationStatut.SENT, get_TrxName()
                    )
                );
                envoyes++;

            } catch (Exception erreurCatch) {
                String msgErreur = erreurCatch.getMessage();
                log.warning("Erreur envoi notification #"
                    + q.getHR_NotificationQueue_ID()
                    + " : " + msgErreur);
                dernierMessageErreur = msgErreur;

                q.setNombre_Tentative(q.getNombre_Tentative() + 1);
                q.setHR_NotificationStatut_ID(
                    HRNotificationStatutUtil.getStatutId(
                        NotificationStatut.ERROR, get_TrxName()
                    )
                );
                erreurs++;
            }

            q.saveEx();
        }

        String resultat = String.format(
            "Traitement terminé — Envoyés: %d | Erreurs: %d | Abandonnés: %d",
            envoyes, erreurs, abandonnes
        );
        if (dernierMessageErreur != null) {
            resultat += " | Détail: " + dernierMessageErreur;
        }
        return resultat;
    }

    private void sendOne(MHRNotificationQueue q) throws Exception {

        MHRNotification notif = (MHRNotification) q.getHR_Notification();
        if (notif == null) throw new IllegalStateException(
            "Notification introuvable pour queue #" + q.getHR_NotificationQueue_ID()
        );

        MHRNotificationType notifType = (MHRNotificationType) notif.getHR_NotificationType();
        if (notifType == null) throw new IllegalStateException(
            "Type de notification introuvable"
        );

        List<MHRNotificationDestinataire> destinataires =
            MHRNotificationDestinataire.getByNotification(
                notif.getHR_Notification_ID(), get_TrxName()
            );

        if (destinataires == null || destinataires.isEmpty()) {
            throw new IllegalStateException(
                "Aucun destinataire pour notification #"
                + notif.getHR_Notification_ID()
            );
        }

        MHRNotificationTemplate template =
            HRNotificationTemplateUtil.getTemplate(
                notifType.getHR_NotificationType_ID(),
                "fr_FR",
                get_TrxName()
            );

        if (template == null) throw new IllegalStateException(
            "Template introuvable pour : " + notifType.getValue()
        );

        Map<String, String> variables =
            HRNotificationVariableBuilder.build(notif, get_TrxName());

        String sujet = HRNotificationTemplateEngine.render(
            template.getMessage_Objet(), variables
        );
        String corps = HRNotificationTemplateEngine.render(
            template.getMessage_Contenu(), variables
        );

        int envoyes = 0;
        String dernierErreur = null;
        for (MHRNotificationDestinataire dest : destinataires) {
            String email = dest.getAdresse();
            if (email == null || email.trim().isEmpty()) continue;
            try {
                EMail mail = HRMailUtil.createMail(
                    getCtx(), email.trim(), sujet, corps
                );
                String result = mail.send();
                if (!"OK".equals(result)) {
                    throw new Exception("SMTP: " + result);
                }
                envoyes++;
            } catch (Exception erreurCatch) {
                dernierErreur = erreurCatch.getMessage();
                log.warning("Échec envoi à " + email
                    + " : " + dernierErreur);
            }
        }
        if (envoyes == 0) {
            throw new Exception("Aucun email envoyé. Dernière erreur: "
                + dernierErreur);
        }
    }
}
