package org.sitracel.notification;

import java.util.ArrayList;
import java.util.List;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataire;
import org.sitracel.notification.gestionmodele.NotificationGestionDestinataireMission;

public class NotificationDestinataireService {

    private static final List<NotificationGestionDestinataire> HANDLERS =
            List.of(
                    new NotificationGestionDestinataireMission()
                    // autres handlers ici
            );

    public static List<BeanDestinataire> resolve(
            NotificationEvent event,
            PO po,
            String trxName
    ) {

        for (NotificationGestionDestinataire h : HANDLERS) {
            if (h.supports(po)) {
                return h.traiter(event, po, trxName);
            }
        }

        return new ArrayList<BeanDestinataire>();
    }
}

