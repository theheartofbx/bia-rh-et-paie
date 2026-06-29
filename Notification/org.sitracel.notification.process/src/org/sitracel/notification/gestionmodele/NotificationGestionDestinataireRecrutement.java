package org.sitracel.notification.gestionmodele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.bean.BeanParametreNotificationCible;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.NotificationSqlControler;
import org.sitracel.notification.model.MHRNotificationType;
import org.sitracel.recrutement.model.MHRCandidature;
import org.sitracel.recrutement.model.MHROffreEmploi;

/**
 * Handler de destinataires pour les événements Recrutement.
 *
 * Supporte : MHROffreEmploi et MHRCandidature.
 *
 * Règles destinataires :
 *   OFFRE_EMPLOI_CREEE   → BCC: RH (pas d'employé cible direct)
 *   CANDIDATURE_RECUE    → BCC: RH
 *   CANDIDATURE_CLASSEE  → BCC: RH
 */
public class NotificationGestionDestinataireRecrutement
        implements NotificationGestionDestinataire {

    @Override
    public boolean supports(PO po) {
        return po instanceof MHROffreEmploi
            || po instanceof MHRCandidature;
    }

    @Override
    public List<BeanDestinataire> traiter(
            NotificationEvent event,
            PO po,
            String trxName
    ) {
        Map<String, BeanDestinataire> map = new HashMap<>();

        MHRNotificationType notifType = new MHRNotificationType(
                Env.getCtx(),
                NotificationSqlControler.loadNotificationTypeFromDB(event.name()),
                trxName
        );

        if (notifType.getName() == null) {
            return new ArrayList<>();
        }

        // Construire le paramètre selon le type de document
        BeanParametreNotificationCible param;

        if (po instanceof MHROffreEmploi) {
            MHROffreEmploi offre = (MHROffreEmploi) po;
            param = new BeanParametreNotificationCible(
                    -1,                        // pas d'employé cible direct
                    offre.getCreatedBy(),      // créateur de l'offre
                    offre.getDate_Creation()   // date de création
            );
        } else {
            MHRCandidature candidature = (MHRCandidature) po;
            param = new BeanParametreNotificationCible(
                    -1,                              // pas d'employé interne cible
                    candidature.getCreatedBy(),      // créateur de la candidature
                    candidature.getDate_Creation()   // date de création
            );
        }

        NotificationDestinataireEnrichir.enrichir(
                map,
                notifType,
                param,
                po.getCtx(),
                trxName
        );

        return new ArrayList<>(map.values());
    }
}
