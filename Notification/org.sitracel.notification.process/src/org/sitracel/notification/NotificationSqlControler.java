package org.sitracel.notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.notification.model.MHRDestinataireType;
import org.sitracel.notification.model.MHRNotificationAcces;
import org.sitracel.notification.model.MHRNotificationCanal;
import org.sitracel.notification.model.MHRNotificationStatut;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;

/**
 * Couche SQL du système de notifications.
 *
 * Toutes les requêtes SQL liées aux notifications sont ici.
 * Aucune logique métier — uniquement de l'accès données.
 */
public class NotificationSqlControler {

    private static final CLogger log =
        CLogger.getCLogger(NotificationSqlControler.class);

    // =========================================================================
    // CHARGEMENT IDS TECHNIQUES
    // =========================================================================

    /**
     * Charge l'ID d'un type de notification par son code (Value).
     * Ex: "HOLIDAY_CREATED" → 2423
     *
     * Utilisé par le cache de NotificationControler.
     * Méthode compatible Function<String, Integer> pour computeIfAbsent.
     */
    public static int loadNotificationTypeIdFromDB(String code) {
        if (code == null || code.trim().isEmpty()) return 0;

        String sql =
            "SELECT " + MHRNotificationType.COLUMNNAME_HR_NotificationType_ID
            + " FROM " + MHRNotificationType.Table_Name
            + " WHERE " + MHRNotificationType.COLUMNNAME_Value + " = ?"
            + " AND "  + MHRNotificationType.COLUMNNAME_IsActive + " = 'Y'";

        int id = DB.getSQLValue(null, sql, code);

        if (id <= 0) {
            log.warning("Type de notification introuvable pour le code : " + code);
        }

        return id;
    }

    /**
     * Alias pour compatibilité avec l'ancien code.
     * Cherche par Name au lieu de Value.
     */
    public static int loadNotificationTypeFromDB(String name) {
        if (name == null || name.trim().isEmpty()) return 0;

        String sql =
            "SELECT " + MHRNotificationType.COLUMNNAME_HR_NotificationType_ID
            + " FROM " + MHRNotificationType.Table_Name
            + " WHERE " + MHRNotificationType.COLUMNNAME_Name + " = ?"
            + " AND "  + MHRNotificationType.COLUMNNAME_IsActive + " = 'Y'";

        int id = DB.getSQLValue(null, sql, name);

        if (id <= 0) {
            log.warning("Type de notification introuvable pour le nom : " + name);
        }

        return id;
    }

    /**
     * Charge l'ID d'un statut de notification par son nom.
     * Ex: "Créée" → 1000000
     */
    public static int loadNotificationStatutIdFromDB(String name) {
        if (name == null || name.trim().isEmpty()) return 0;

        String sql =
            "SELECT " + MHRNotificationStatut.COLUMNNAME_HR_NotificationStatut_ID
            + " FROM " + MHRNotificationStatut.Table_Name
            + " WHERE " + MHRNotificationStatut.COLUMNNAME_Name + " = ?"
            + " AND "  + MHRNotificationStatut.COLUMNNAME_IsActive + " = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException erreurCatch) {
            log.warning("loadNotificationStatutIdFromDB: " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

    /**
     * Charge l'ID d'un type de destinataire par son nom (TO / CC / BCC).
     */
    public static int loadDestinataireTypeFromDB(String name) {
        if (name == null || name.trim().isEmpty()) return 0;

        String sql =
            "SELECT " + MHRDestinataireType.COLUMNNAME_HR_DestinataireType_ID
            + " FROM " + MHRDestinataireType.Table_Name
            + " WHERE " + MHRDestinataireType.COLUMNNAME_Name + " = ?";

        return DB.getSQLValue(null, sql, name);
    }

    /**
     * Charge l'ID d'un canal de notification par son nom (EMAIL / WHATSAPP / SMS).
     */
    public static int loadChannelIdFromDB(String name) {
        if (name == null || name.trim().isEmpty()) return 0;

        String sql =
            "SELECT " + MHRNotificationCanal.COLUMNNAME_HR_NotificationCanal_ID
            + " FROM " + MHRNotificationCanal.Table_Name
            + " WHERE " + MHRNotificationCanal.COLUMNNAME_Name + " = ?"
            + " AND "  + MHRNotificationCanal.COLUMNNAME_IsActive + " = 'Y'";

        int id = DB.getSQLValue(null, sql, name);

        if (id <= 0) {
            log.warning("Canal de notification introuvable : " + name);
        }

        return id;
    }

    /**
     * Charge l'ID d'un template par type de notification.
     */
    public static int loadTemplateIdByNotificationType(int notificationTypeId) {
        if (notificationTypeId <= 0) return 0;

        String sql =
            "SELECT " + MHRNotificationTemplate.COLUMNNAME_HR_NotificationTemplate_ID
            + " FROM " + MHRNotificationTemplate.Table_Name
            + " WHERE " + MHRNotificationTemplate.COLUMNNAME_HR_NotificationType_ID + " = ?"
            + " AND "  + MHRNotificationTemplate.COLUMNNAME_IsActive + " = 'Y'"
            ;

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, notificationTypeId);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException erreurCatch) {
            log.warning("loadTemplateIdByNotificationType: " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

    // =========================================================================
    // RÉSOLUTION DESTINATAIRES RH
    // =========================================================================

    /**
     * Retourne les C_BPartner_ID de tous les employés ayant un des rôles RH.
     *
     * Utilisé pour mettre les responsables RH en BCC sur toutes
     * les notifications.
     *
     * @param rolesRH  Liste des noms de rôles RH
     * @param trxName  Nom de la transaction
     * @return Liste des C_BPartner_ID des responsables RH
     */
    public static List<Integer> getEmployeesByRoles(
            List<String> rolesRH, String trxName) {

        List<Integer> resultat = new ArrayList<>();

        if (rolesRH == null || rolesRH.isEmpty()) return resultat;

        // Construire les placeholders IN (?, ?, ?)
        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < rolesRH.size(); i++) {
            if (i > 0) placeholders.append(", ");
            placeholders.append("?");
        }

        String sql =
            "SELECT DISTINCT bp.C_BPartner_ID "
            + "FROM adempiere.C_BPartner bp "
            + "JOIN adempiere.AD_User u "
            + "  ON u.C_BPartner_ID = bp.C_BPartner_ID "
            + "JOIN adempiere.AD_User_Roles ur "
            + "  ON ur.AD_User_ID = u.AD_User_ID "
            + "JOIN adempiere.AD_Role r "
            + "  ON r.AD_Role_ID = ur.AD_Role_ID "
            + "WHERE r.Name IN (" + placeholders + ") "
            + "AND bp.IsActive = 'Y' "
            + "AND u.IsActive = 'Y' "
            + "AND r.IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            for (int i = 0; i < rolesRH.size(); i++) {
                pstmt.setString(i + 1, rolesRH.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(rs.getInt(1));
            }
        } catch (SQLException erreurCatch) {
            log.warning("getEmployeesByRoles: " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return resultat;
    }

    // =========================================================================
    // RÈGLES D'ACCÈS AUX NOTIFICATIONS
    // =========================================================================

    /**
     * Retourne les règles d'accès configurées pour un type de notification.
     * Utilisé par le système de cibles configurable (NotificationRegroupementCible).
     */
    public static List<MHRNotificationAcces> getReglesAccess(
            int notificationTypeId,
            Properties ctx,
            String trxName) {

        String where =
            MHRNotificationAcces.COLUMNNAME_HR_NotificationType_ID + " = ? "
            + "AND " + MHRNotificationAcces.COLUMNNAME_IsActive + " = 'Y'";

        return new Query(ctx, MHRNotificationAcces.Table_Name, where, trxName)
            .setParameters(notificationTypeId)
            .setOrderBy("Priority DESC")
            .list();
    }

    // =========================================================================
    // INFORMATIONS ENTREPRISE (pour les templates)
    // =========================================================================

    /**
     * Charge les informations de l'entreprise dans le contexte du template.
     * Variables disponibles : CompanyName, CompanyPhone, CompanyEmail,
     * CompanyAddress, CompanyPoBox, CompanyContribuable, CompanyCNPS, CompanyLogoBase64
     */
    public static void buildCompany(Map<String, Object> ctx) {

        String sql =
            "SELECT "
            + "c.name                     AS company_name,"
            + "ci.phone                   AS phone1,"
            + "ci.email                   AS email,"
            + "ci.iext_headquater         AS siege_social,"
            + "ci.iext_pobox              AS po_box,"
            + "ci.iext_contribuable       AS contribuable,"
            + "ci.iext_numbercnps         AS cnps,"
            + "img.binarydata             AS logo "
            + "FROM ad_client c "
            + "LEFT JOIN ad_clientinfo ci ON ci.ad_client_id = c.ad_client_id "
            + "LEFT JOIN ad_image img ON img.ad_image_id = ci.logo_id "
            + "WHERE c.ad_client_id = ?";

        int adClientId = Env.getAD_Client_ID(Env.getCtx());

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, adClientId);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                ctx.put("CompanyName",         safeStr(rs, "company_name"));
                ctx.put("CompanyPhone",        safeStr(rs, "phone1"));
                ctx.put("CompanyEmail",        safeStr(rs, "email"));
                ctx.put("CompanyAddress",      safeStr(rs, "siege_social"));
                ctx.put("CompanyPoBox",        safeStr(rs, "po_box"));
                ctx.put("CompanyContribuable", safeStr(rs, "contribuable"));
                ctx.put("CompanyCNPS",         safeStr(rs, "cnps"));
                byte[] logo = rs.getBytes("logo");
                ctx.put("CompanyLogoBase64",
                    logo != null ? Base64.getEncoder().encodeToString(logo) : "");
            }
        } catch (SQLException erreurCatch) {
            log.warning("buildCompany: " + erreurCatch.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
    }

    // =========================================================================
    // UTILITAIRES
    // =========================================================================

    private static String safeStr(ResultSet rs, String col) {
        try {
            String v = rs.getString(col);
            return v != null ? v : "";
        } catch (SQLException e) {
            return "";
        }
    }
}
