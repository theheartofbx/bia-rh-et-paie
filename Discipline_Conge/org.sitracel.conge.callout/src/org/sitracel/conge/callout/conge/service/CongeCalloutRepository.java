package org.sitracel.conge.callout.conge.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.model.I_HR_EmployeeJob;

/**
 * Repository — requêtes SQL propres au callout congé.
 */
public final class CongeCalloutRepository {

    private static final CLogger log = CLogger.getCLogger(CongeCalloutRepository.class);

    private CongeCalloutRepository() {}

    // =========================================================================
    // DISPONIBILITÉ DÉPARTEMENT
    // =========================================================================

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
     * Retourne la date critique du département — le jour où le plus
     * grand nombre d'employés sont en congé simultanément sur la période.
     *
     * Retourne null si aucun congé trouvé.
     */
    public static Timestamp getJourCritiqueDepartement(Integer bpartnerId,
                                                        Timestamp dateDebut,
                                                        Timestamp dateFin,
                                                        String trxName) {
        if (bpartnerId == null || dateDebut == null || dateFin == null) return null;

        // Parmi tous les débuts de congé du département sur la période,
        // on cherche le jour avec le plus grand chevauchement.
        String sql = "SELECT h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective
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
            + " AND h." + I_HR_Holiday.COLUMNNAME_Date_Fin_Effective + " >= ?"
            + " ORDER BY h." + I_HR_Holiday.COLUMNNAME_Date_Debut_Effective + " DESC"
            + " LIMIT 1";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateFin);
            pstmt.setTimestamp(3, dateDebut);
            rs = pstmt.executeQuery();
            if (rs.next()) return rs.getTimestamp(1);
        } catch (SQLException e) {
            log.warning("getJourCritiqueDepartement : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return null;
    }
}
