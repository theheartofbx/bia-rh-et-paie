package org.sitracel.notification;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.notification.model.MHRDestinataireType;
import org.sitracel.notification.model.MHRNotificationAcces;
import org.sitracel.notification.model.MHRNotificationCanal;
import org.sitracel.notification.model.MHRNotificationStatut;
import org.sitracel.notification.model.MHRNotificationTemplate;
import org.sitracel.notification.model.MHRNotificationType;

public class NotificationSqlControler {


    public static int loadNotificationTypeFromDB(String typeName) {

        String sql = "SELECT "+ MHRNotificationType.COLUMNNAME_HR_NotificationType_ID
            + "FROM "+MHRNotificationType.Table_Name
            + "WHERE "+MHRNotificationType.COLUMNNAME_Name+" = ?"
              + "AND "+MHRNotificationType.COLUMNNAME_IsActive+" = 'Y'";

        int id = DB.getSQLValue(null, sql, typeName);

        if (id <= 0) {
            throw new AdempiereException(
                "Type de notification introuvable : " + typeName
            );
        }

        return id;
    }
    
    public static int loadDestinataireTypeFromDB(String name) {

        String sql =
            "SELECT " + MHRDestinataireType.COLUMNNAME_HR_DestinataireType_ID 
            + " FROM " + MHRDestinataireType.Table_Name
            + " WHERE " + MHRDestinataireType.COLUMNNAME_Name + " = ?";

        return DB.getSQLValue(
            null,
            sql,
            name
        );
    }

    
    public static int loadChannelIdFromDB(String name) {

        String sql = "SELECT " + MHRNotificationCanal.COLUMNNAME_HR_NotificationCanal_ID
            + " FROM " + MHRNotificationCanal.Table_Name
            + " WHERE " + MHRNotificationCanal.COLUMNNAME_Name + " = ?"
            + " AND " + MHRNotificationCanal.COLUMNNAME_IsActive + " = 'Y'";

        int id = DB.getSQLValue(null, sql, name);
        
        if (id <= 0) {
            throw new AdempiereException(
                "Canal de notification introuvable : " + name
            );
        }

        return id;
    }
    
    public static int loadNotificationStatutIdFromDB(String name) {

        String sql =
            "SELECT " + MHRNotificationStatut.COLUMNNAME_HR_NotificationStatut_ID
            + " FROM " + MHRNotificationStatut.Table_Name
            + " WHERE " + MHRNotificationStatut.COLUMNNAME_Name 
            + "= ? AND "+ MHRNotificationStatut.COLUMNNAME_IsActive + "='Y'";

        try (PreparedStatement ps = DB.prepareStatement(sql, null)) {

            ps.setString(1, name);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new AdempiereException(
                "Impossible de charger le statut notification : " + name,
                e
            );
        }

        return 0;
    }

    
    public static void buildCompany(Map<String, Object> ctx) {

        String sql = "SELECT "
                + " c.name                         AS company_name,"
                + " ci.phone                       AS phone1,"
                + " ci.email                       AS email,"
                + " ci.iext_headquater             AS siege_social,"
                + " ci.iext_pobox                  AS po_box,"
                + " ci.iext_contribuable           AS contribuable,"
                + " ci.iext_numbercnps             AS cnps,"
                + " img.binarydata                 AS logo"
            + " FROM ad_client c"
            + " LEFT JOIN ad_clientinfo ci" 
                + " ON ci.ad_client_id = c.ad_client_id"
            + " LEFT JOIN ad_image img" 
                + " ON img.ad_image_id = ci.logo_id"
            + " WHERE c.ad_client_id = ?";

        int adClientId = Env.getAD_Client_ID(Env.getCtx());

        try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
            ps.setInt(1, adClientId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    ctx.put("CompanyName", rs.getString("company_name"));
                    ctx.put("CompanyPhone", rs.getString("phone1"));
                    ctx.put("CompanyEmail", rs.getString("email"));
                    ctx.put("CompanyAddress", rs.getString("siege_social"));
                    ctx.put("CompanyPoBox", rs.getString("po_box"));
                    ctx.put("CompanyContribuable", rs.getString("contribuable"));
                    ctx.put("CompanyCNPS", rs.getString("cnps"));

                    byte[] logo = rs.getBytes("logo");
                    ctx.put(
                        "CompanyLogoBase64",
                        logo != null
                            ? Base64.getEncoder().encodeToString(logo)
                            : ""
                    );
                }
            }
        } catch (SQLException e) {
            throw new AdempiereException("Erreur chargement informations entreprise", e);
        }
    }
    
    public static List<MHRNotificationAcces> getReglesAccess(
            int notificationTypeId,
            Properties ctx,
            String trxName
    ) {

        String where =
            MHRNotificationAcces.COLUMNNAME_HR_NotificationType_ID+" =? " +
            " AND "+MHRNotificationAcces.COLUMNNAME_IsActive+" ='Y'";

        return new Query(
                ctx,
                MHRNotificationAcces.Table_Name,
                where,
                trxName
        )
        .setParameters(notificationTypeId)
        .setOrderBy("Priority DESC")
        .list();
    }
    
    public static int loadTemplateIdByNotificationType(
            int notificationTypeId
    ) {

        if (notificationTypeId <= 0) {
            throw new IllegalArgumentException(
                "notificationTypeId invalide"
            );
        }

        String sql =
            "SELECT " + MHRNotificationTemplate.COLUMNNAME_HR_NotificationTemplate_ID +
            "FROM " + MHRNotificationTemplate.Table_Name +
            "WHERE "+MHRNotificationTemplate.COLUMNNAME_HR_NotificationType_ID+" = ? " +
            "AND "+MHRNotificationTemplate.COLUMNNAME_IsActive+" = 'Y'";

        int templateId = 0;

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, notificationTypeId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                templateId = rs.getInt(
                    "HR_NotificationTemplate_ID"
                );
            }

        } catch (Exception e) {
            throw new RuntimeException(
                "Erreur lors du chargement du template pour le type "
                + notificationTypeId,
                e
            );
        } finally {
            DB.close(rs, pstmt);
        }

        return templateId;
    }

}
