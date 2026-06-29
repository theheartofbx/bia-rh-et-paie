package org.sitracel.notification.gestionmodele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.util.Env;
import org.sitracel.bean.BeanDestinataire;
import org.sitracel.bean.BeanParametreNotificationCible;
import org.sitracel.enumeration.NotificationCanal;
import org.sitracel.enumeration.NotificationCible;
import org.sitracel.enumeration.NotificationTypeDestinataireEmail;
import org.sitracel.model.MHRCibleType;
import org.sitracel.notification.NotificationSqlControler;
import org.sitracel.notification.model.MHRNotificationAcces;
import org.sitracel.notification.model.MHRNotificationType;

public class NotificationDestinataireEnrichir {

	public static void enrichir(
	        Map<String, BeanDestinataire> map,
	        MHRNotificationType notifType,
	        BeanParametreNotificationCible parametreNotificationCible,
	        Properties ctx,
	        String trxName
	) {

        if (notifType == null) {
            return;
        }

        List<MHRNotificationAcces> regles =
            NotificationSqlControler.getReglesAccess(
                notifType.getHR_NotificationType_ID(),
                ctx,
                trxName
            );

        /* ==========================
         * 1. GROUPER PAR CANAL
         * ========================== */
        Map<NotificationCanal, List<MHRNotificationAcces>> parCanal =
            new HashMap<>();

        for (MHRNotificationAcces r : regles) {

            NotificationCanal canal =
                NotificationCanal.fromId(r.getHR_NotificationCanal_ID());

            if (canal == null) {
                continue;
            }

            if (!parCanal.containsKey(canal)) {
                parCanal.put(canal, new ArrayList<MHRNotificationAcces>());
            }

            parCanal.get(canal).add(r);
        }

        /* ==========================
         * 2. TRAITEMENT PAR CANAL
         * ========================== */
        for (Map.Entry<NotificationCanal, List<MHRNotificationAcces>> entryCanal
                : parCanal.entrySet()) {

            NotificationCanal canal = entryCanal.getKey();
            List<MHRNotificationAcces> reglesCanal = entryCanal.getValue();

            traiterCanal(
                map,
                canal,
                reglesCanal,
                parametreNotificationCible,
                trxName
            );
        }
    }

    /* ======================================================
     * TRAITEMENT D'UN CANAL
     * ====================================================== */
    private static void traiterCanal(
            Map<String, BeanDestinataire> map,
            NotificationCanal canal,
            List<MHRNotificationAcces> regles,
            BeanParametreNotificationCible parametreNotificationCible,
            String trxName
    ) {

        /* ==========================
         * 3. GROUPER PAR TYPE DE CIBLE
         * ========================== */
        Map<NotificationCible, List<MHRNotificationAcces>> parCible =
            new HashMap<>();

        for (MHRNotificationAcces r : regles) {
        	
        	if(r==null) {
        		continue;
        	}
        	
        	MHRCibleType cibleType = new MHRCibleType(Env.getCtx(), r.getHR_CibleType_ID(), trxName);
        	
        	if(cibleType.getName() ==  null) {
        		continue;
        	}
        	
            NotificationCible cible =
                NotificationCible.fromName(cibleType.getName());

            if (cible == null) {
                continue;
            }

            if (!parCible.containsKey(cible)) {
                parCible.put(cible, new ArrayList<MHRNotificationAcces>());
            }

            parCible.get(cible).add(r);
        }

        /* ==========================
         * 4. RESOLUTION PAR TYPE DE CIBLE
         * ========================== */
        for (Map.Entry<NotificationCible, List<MHRNotificationAcces>> entryCible
                : parCible.entrySet()) {

            NotificationCible cible = entryCible.getKey();
            List<MHRNotificationAcces> reglesCible = entryCible.getValue();

            traiterCible(
                map,
                canal,
                cible,
                reglesCible,
                parametreNotificationCible
            );
        }
    }

    /* ======================================================
     * RESOLUTION D'UNE CIBLE (UNE SEULE REQUÊTE)
     * ====================================================== */
    private static void traiterCible(
            Map<String, BeanDestinataire> map,
            NotificationCanal canal,
            NotificationCible cible,
            List<MHRNotificationAcces> regles,
            BeanParametreNotificationCible parametreNotificationCible
    ) {

        /* ==========================
         * EXTRAIRE LES IDS DE CIBLE
         * ========================== */
        List<Integer> cibleIds = new ArrayList<>();

        for (MHRNotificationAcces r : regles) {
            if (r.getCible_ID() > 0) {
                cibleIds.add(r.getCible_ID());
            }
        }

        /* ==========================
         * RESOLUTION (1 APPEL)
         * ========================== */
        List<Integer> bpartners =
            NotificationGestionCible.traiterCible(
                cible,
                cibleIds,
                parametreNotificationCible
            );

        if (bpartners.isEmpty()) {
            return;
        }

        /* ==========================
         * CREATION DES DESTINATAIRES
         * ========================== */
        for (MHRNotificationAcces r : regles) {

            NotificationTypeDestinataireEmail destType =
                NotificationTypeDestinataireEmail.fromId(
                    r.getHR_DestinataireType_ID()
                );

            if (destType == null) {
                continue;
            }

            for (Integer bpId : bpartners) {
                NotificationGestionDestinataireControler.addOrUpgrade(
                    map,
                    bpId,
                    canal,
                    destType
                );
            }
        }
    }
}
