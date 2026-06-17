package org.sitracel.notification;

import java.util.List;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.model.MHRNotificationType;

public class NotificationService {

    public static void notify(NotificationEvent event, PO po) {

        if (event == null || po == null) {
            return;
        }

        String trxName = po.get_TrxName();

        // 1️⃣ Charger le type
        MHRNotificationType notifType =
                NotificationTypeService.getByEvent(event, trxName);

        if (notifType == null) {
            return;
        }

        // 2️⃣ Résoudre destinataires
        List<BeanDestinataire> destinataires =
                NotificationDestinataireService.resolve(
                        event,
                        po,
                        trxName
                );

        if (destinataires.isEmpty()) {
            return;
        }

        // 3️⃣ Persister
        NotificationPersistenceService.createNotification(
                notifType,
                po,
                destinataires,
                trxName
        );
    }
}

