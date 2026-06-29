package org.sitracel.notification;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataire;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireDemandeExplication;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireHoliday;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireMission;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireMissionAffectation;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinatairePunishment;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireRecrutement;

/**
 * Point d'entrée unique pour la résolution des destinataires d'une notification.
 *
 * Pour ajouter un nouveau type de document :
 *   1. Créer NotificationGestionDestinataire[NomDocument] dans gestionmodele
 *   2. L'ajouter à HANDLERS ci-dessous
 *   C'est tout.
 */
public class NotificationDestinataireService {

    private static final List<NotificationGestionDestinataire> HANDLERS = List.of(
            new NotificationGestionDestinataireMission(),
            new NotificationGestionDestinataireMissionAffectation(),
            new NotificationGestionDestinataireHoliday(),
            new NotificationGestionDestinataireDemandeExplication(),
            new NotificationGestionDestinatairePunishment(),
            new NotificationGestionDestinataireRecrutement()
    );

    public static List<BeanDestinataire> resolve(
            NotificationEvent event,
            PO po,
            String trxName
    ) {
        if (event == null || po == null) {
            return new ArrayList<>();
        }
        for (NotificationGestionDestinataire handler : HANDLERS) {
            if (handler.supports(po)) {
                List<BeanDestinataire> result = handler.traiter(event, po, trxName);
                return result != null ? result : new ArrayList<>();
            }
        }
        return new ArrayList<>();
    }
}
