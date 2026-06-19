package org.sitracel.employe;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.organigramme.model.MHROrganigramme;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Repository transversal — accès SQL aux données des employés.
 *
 * Regroupe les requêtes SQL liées aux employés et à la hiérarchie,
 * utilisées par plusieurs modules (Congés, Discipline, Mission, Paie...).
 *
 * Remplace les méthodes SQL employé de GeneralSqlController.
 */
public final class HREmployeRepository {

    private static final CLogger log = CLogger.getCLogger(HREmployeRepository.class);

    private HREmployeRepository() {}

    // =========================================================================
    // POSTE ET HIÉRARCHIE
    // =========================================================================

    /**
     * Retourne l'ID du poste actuel d'un employé
     * (le plus récent selon DateFrom).
     */
    public static Integer getCurrentJobId(Integer bpartnerId) {
        if (bpartnerId == null) return null;

        String sql = "SELECT " + MHREmployeeJob.COLUMNNAME_HR_Job_ID
            + " FROM " + MHREmployeeJob.Table_Name
            + " WHERE " + MHREmployeeJob.COLUMNNAME_C_BPartner_ID + " = ?"
            + " ORDER BY " + MHREmployeeJob.COLUMNNAME_DateFrom + " DESC"
            + " LIMIT 1";

        return DB.getSQLValue(null, sql, bpartnerId);
    }

    /**
     * Retourne les IDs des postes responsables d'un poste donné
     * (remonte d'un niveau dans l'organigramme).
     */
    public static List<Integer> getPostesResponsables(Integer posteId) {
        List<Integer> list = new ArrayList<>();
        if (posteId == null) return list;

        String sql = "SELECT " + MHROrganigramme.COLUMNNAME_Poste_Responsable_ID
            + " FROM " + MHROrganigramme.Table_Name
            + " WHERE " + MHROrganigramme.COLUMNNAME_Poste_ID + " = ?";

        try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
            ps.setInt(1, posteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.warning("getPostesResponsables : " + e.getMessage());
        }
        return list;
    }

    /**
     * Retourne les IDs des postes responsables d'un poste
     * filtrés par catégorie de responsabilité.
     */
    public static List<Integer> getPostesResponsablesParCategorie(
            Integer posteId, Integer categorieResponsabiliteId) {

        List<Integer> list = new ArrayList<>();
        if (posteId == null || categorieResponsabiliteId == null) return list;

        String sql = "SELECT " + MHROrganigramme.COLUMNNAME_Poste_Responsable_ID
            + " FROM " + MHROrganigramme.Table_Name
            + " WHERE " + MHROrganigramme.COLUMNNAME_Poste_ID + " = ?"
            + " AND " + MHROrganigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID + " = ?";

        try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
            ps.setInt(1, posteId);
            ps.setInt(2, categorieResponsabiliteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.warning("getPostesResponsablesParCategorie : " + e.getMessage());
        }
        return list;
    }

    /**
     * Retourne les IDs (C_BPartner_ID) des employés occupant un poste donné.
     */
    public static List<Integer> getEmployeesByJob(Integer posteId) {
        List<Integer> list = new ArrayList<>();
        if (posteId == null) return list;

        String sql = "SELECT " + MHREmployeeJob.COLUMNNAME_C_BPartner_ID
            + " FROM " + MHREmployeeJob.Table_Name
            + " WHERE " + MHREmployeeJob.COLUMNNAME_HR_Job_ID + " = ?";

        try (PreparedStatement ps = DB.prepareStatement(sql, null)) {
            ps.setInt(1, posteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            log.warning("getEmployeesByJob : " + e.getMessage());
        }
        return list;
    }

    /**
     * Retourne les IDs (C_BPartner_ID) des employés ayant un rôle donné
     * parmi une liste de noms de rôles.
     */
    public static List<Integer> getEmployeesByRoles(List<String> roles, String trxName) {
        List<Integer> list = new ArrayList<>();
        if (roles == null || roles.isEmpty()) return list;

        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < roles.size(); i++) {
            placeholders.append(i == 0 ? "?" : ", ?");
        }

        String sql = "SELECT DISTINCT u.C_BPartner_ID"
            + " FROM AD_User u"
            + " JOIN AD_UserRoles ur ON ur.AD_User_ID = u.AD_User_ID"
            + " JOIN AD_Role r ON r.AD_Role_ID = ur.AD_Role_ID"
            + " WHERE r.Name IN (" + placeholders + ")"
            + " AND u.C_BPartner_ID IS NOT NULL"
            + " AND u.IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            for (int i = 0; i < roles.size(); i++) {
                pstmt.setString(i + 1, roles.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log.warning("getEmployeesByRoles : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return list;
    }

    // =========================================================================
    // CONTRATS
    // =========================================================================

    /**
     * Retourne la liste des contrats d'un employé antérieurs à une date,
     * triés du plus récent au plus ancien.
     *
     * Utilisé par HREmployeService.getDateDernierContrat().
     */
    public static List<MHRElementBasePaieEmploye> getDatesDerniersContrats(
            Integer bpartnerId, Timestamp dateMax, String trxName) {

        List<MHRElementBasePaieEmploye> resultat = new ArrayList<>();
        if (bpartnerId == null || dateMax == null) return resultat;

        String sql = "SELECT * FROM " + I_HR_ElementBasePaieEmploye.Table_Name
            + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " <= ?"
            + " ORDER BY " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " DESC";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            pstmt.setInt(1, bpartnerId);
            pstmt.setTimestamp(2, dateMax);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                resultat.add(new MHRElementBasePaieEmploye(Env.getCtx(), rs, trxName));
            }
        } catch (SQLException e) {
            log.warning("getDatesDerniersContrats : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
            rs = null;
            pstmt = null;
        }
        return resultat;
    }
}
