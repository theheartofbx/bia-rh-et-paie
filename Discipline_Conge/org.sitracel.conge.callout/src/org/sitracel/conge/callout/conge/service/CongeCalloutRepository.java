package org.sitracel.conge.callout.conge.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.beanfactory.BeanFactory;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.I_HR_EmployeeJob;

/**
 * Repository — requêtes SQL propres au callout congé.
 *
 * Regroupe les accès base de données nécessaires au calcul
 * des informations congé dans les callouts (disponibilité
 * département, périodes critiques, etc.).
 */
public final class CongeCalloutRepository {

    private static final CLogger log = CLogger.getCLogger(CongeCalloutRepository.class);

    private CongeCalloutRepository() {}

    // =========================================================================
    // DISPONIBILITÉ DÉPARTEMENT
    // =========================================================================

    /**
     * Retourne le nombre total d'employés dans le département d'un employé.
     */
    public static int getNombreEmployeDepartement(Integer bpartnerId, String trxName) {
        if (bpartnerId == null) return 0;

        String sql = "SELECT COUNT(*) FROM " + I_HR_EmployeeJob.Table_Name + " ej"
            + " WHERE ej.HR_Department_ID = ("
            + "   SELECT HR_Department_ID FROM " + I_HR_EmployeeJob.Table_Name
            + "   WHERE C_BPartner_ID = ?"
            + "   ORDER BY DateFrom DESC LIMIT 1"
            + ")";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            log.warning("getNombreEmployeDepartement : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

    /**
     * Retourne le nombre d'employés du même département déjà en congé
     * sur une période donnée.
     */
    public static int getNombreEmployeDepartementEnConge(Integer bpartnerId,
                                                          Timestamp dateDebut,
                                                          Timestamp dateFin,
                                                          String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return 0;

        String sql = "SELECT COUNT(DISTINCT h.C_BPartner_ID)"
            + " FROM " + I_HR_Holiday.Table_Name + " h"
            + " JOIN " + I_HR_EmployeeJob.Table_Name + " ej"
            + "   ON ej.C_BPartner_ID = h.C_BPartner_ID"
            + " WHERE ej.HR_Department_ID = ("
            + "   SELECT HR_Department_ID FROM " + I_HR_EmployeeJob.Table_Name
            + "   WHERE C_BPartner_ID = ?"
            + "   ORDER BY DateFrom DESC LIMIT 1"
            + ")"
            + " AND h." + I_HR_Holiday.COLUMNNAME_IsRejetee + " = 'N'"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " <= ?"
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " >= ?";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateFin);
            pstmt.setTimestamp(3, dateDebut);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            log.warning("getNombreEmployeDepartementEnConge : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }

    /**
     * Retourne le nombre maximum de jours de congé simultanés autorisés
     * dans le département (paramètre de criticité).
     */
    public static int getJourCritiqueDepartement(Integer bpartnerId, String trxName) {
        if (bpartnerId == null) return 0;

        String sql = "SELECT COALESCE(d.Jour_Conge_Critique, 0)"
            + " FROM HR_Department d"
            + " JOIN " + I_HR_EmployeeJob.Table_Name + " ej ON ej.HR_Department_ID = d.HR_Department_ID"
            + " WHERE ej.C_BPartner_ID = ?"
            + " ORDER BY ej.DateFrom DESC LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            log.warning("getJourCritiqueDepartement : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return 0;
    }
}
