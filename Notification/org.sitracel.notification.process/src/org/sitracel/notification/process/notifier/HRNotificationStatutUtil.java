package org.sitracel.notification.process.notifier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.compiere.util.DB;
import org.sitracel.enumeration.NotificationStatut;

/**
 * Résout les IDs des statuts de notification depuis la base de données.
 * Cache en mémoire pour éviter les requêtes répétées.
 *
 * La table HR_NotificationStatut n'a pas de colonne Value —
 * on identifie les statuts par leur Name (= NotificationStatut.getCode()).
 */
public final class HRNotificationStatutUtil {

    private static final Map<String, Integer> CACHE =
        new ConcurrentHashMap<>();

    private HRNotificationStatutUtil() {}

    /**
     * Retourne l'ID du statut pour un NotificationStatut donné.
     *
     * @param statut  L'enum statut (CREATED, SENT, ERROR, FAILED...)
     * @param trxName Nom de la transaction en cours
     * @return L'ID en base, ou 0 si introuvable
     */
    public static int getStatutId(NotificationStatut statut, String trxName) {
        if (statut == null) return 0;
        return CACHE.computeIfAbsent(statut.getCode(), name ->
            DB.getSQLValueEx(trxName,
                "SELECT hr_notificationstatut_id "
                + "FROM adempiere.hr_notificationstatut "
                + "WHERE name = ? AND isactive = 'Y'",
                name
            )
        );
    }

    /**
     * Surcharge avec code texte brut — pour la compatibilité avec
     * SitracelProcessNotifier qui passait "SENT", "ERROR", "FAILED".
     *
     * @param code    Code technique : "SENT", "ERROR", "FAILED"...
     * @param trxName Nom de la transaction
     */
    public static int getStatutId(String code, String trxName) {
        if (code == null) return 0;
        // Résoudre via l'enum pour garantir la cohérence
        for (NotificationStatut s : NotificationStatut.values()) {
            if (s.name().equalsIgnoreCase(code)) {
                return getStatutId(s, trxName);
            }
        }
        return 0;
    }
}
