package org.sitracel.notification.gestionmodele;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.compiere.model.PO;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.enumeration.NotificationEvent;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;

public final class NotificationGestionDestinataireControler {

    private NotificationGestionDestinataireControler() {
        // utilitaire
    }

    /* =====================================================
     * OUTIL DEFENSIF
     * ===================================================== */
    public static <T> List<T> safeList(List<T> list) {
        if (list == null) {
            return new ArrayList<T>();
        }
        return list;
    }

    /* =====================================================
     * HANDLERS DECLARES EXPLICITEMENT
     * ===================================================== */
    private static final List<NotificationGestionDestinataire> HANDLERS =
        initialiserHandlers();

    private static List<NotificationGestionDestinataire> initialiserHandlers() {

        List<NotificationGestionDestinataire> handlers =
            new ArrayList<NotificationGestionDestinataire>();

        handlers.add(new NotificationGestionDestinataireMission());
        handlers.add(new NotificationGestionDestinataireMissionAffectation());
        handlers.add(new NotificationGestionDestinataireDemandeExplication());
        handlers.add(new NotificationGestionDestinatairePunishment());
        handlers.add(new NotificationGestionDestinataireHoliday());

        return handlers;
    }

    /* =====================================================
     * POINT D'ENTREE PRINCIPAL
     * ===================================================== */
    public static List<BeanDestinataire> traiter(
            NotificationEvent event,
            PO po,
            String trxNme
    ) {

        if (event == null || po == null) {
            return new ArrayList<BeanDestinataire>();
        }

        for (NotificationGestionDestinataire handler : HANDLERS) {

            if (handler.supports(po)) {
                return safeList(
                    handler.traiter(event, po, trxNme)
                );
            }
        }

        return new ArrayList<BeanDestinataire>();
    }

    /* =====================================================
     * AJOUT / MISE A JOUR D'UN DESTINATAIRE
     * ===================================================== */
    public static void addOrUpgrade(
            Map<String, BeanDestinataire> map,
            int bpartnerId,
            NotificationCanal canal,
            NotificationTypeDestinataireEmail type
    ) {

        if (map == null) {
            return;
        }

        if (bpartnerId <= 0) {
            return;
        }

        if (canal == null || type == null) {
            return;
        }

        String canalValue =
            NotificationGestionCanal.gererCanal(
                bpartnerId,
                canal
            );

        if (canalValue == null || canalValue.trim().isEmpty()) {
            return;
        }

        String key = bpartnerId + "|" + canal.name();

        BeanDestinataire existant = map.get(key);

        if (existant == null) {

            BeanDestinataire nouveau =
                new BeanDestinataire(
                    bpartnerId,
                    canalValue,
                    canal,
                    type
                );

            map.put(key, nouveau);
            return;
        }

        /* ==========================
         * GESTION DE PRIORITE
         * ========================== */
        NotificationTypeDestinataireEmail typeExistant =
            existant.getType();

        if (typeExistant == null) {
            map.put(
                key,
                new BeanDestinataire(
                    bpartnerId,
                    canalValue,
                    canal,
                    type
                )
            );
            return;
        }

        if (type.getPriority() < typeExistant.getPriority()) {

            BeanDestinataire remplace =
                new BeanDestinataire(
                    bpartnerId,
                    canalValue,
                    canal,
                    type
                );

            map.put(key, remplace);
        }
    }
}
