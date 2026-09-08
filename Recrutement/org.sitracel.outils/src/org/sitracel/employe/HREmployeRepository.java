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
import org.sitracel.contrat.model.I_HR_Affectation;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.organigramme.model.I_HR_Organigramme;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

/**
 * Repository transversal - acces SQL aux donnees des employes.
 *
 * Regroupe les requetes SQL liees aux employes et a la hierarchie,
 * utilisees par plusieurs modules (Conges, Discipline, Mission, Paie...).
 *
 * Remplace les methodes SQL employe de GeneralSqlController.
 *
 * Depuis Session 9 : HR_EmployeeJob (mecanisme herite, sans notion de
 * periode) a ete remplace par HR_Affectation via HRContratService/
 * HRContratRepository, qui gerent correctement les dates de debut/fin.
 */
public final class HREmployeRepository {

    private static final CLogger log = CLogger.getCLogger(HREmployeRepository.class);

    private HREmployeRepository() {}

    // =========================================================================
    // POSTE ET HIERARCHIE
    // =========================================================================

    public static Integer getCurrentJobId(Integer bpartnerId) {
        if (bpartnerId == null) {
            return null;
        }

        MHRAffectation affectation = HRContratService.getAffectationActive(
            bpartnerId, new Timestamp(System.currentTimeMillis()), null);

        if (affectation == null || affectation.getHR_Job_ID() <= 0) {
            return null;
        }
        return affectation.getHR_Job_ID();
    }

    public static List<Integer> getPostesResponsables(Integer posteId) {
        List<Integer> list = new ArrayList<>();
        if (posteId == null) {
            return list;
        }

        String sql = "SELECT " + I_HR_Organigramme.COLUMNNAME_Poste_Responsable_ID
            + " FROM " + I_HR_Organigramme.Table_Name
            + " WHERE " + I_HR_Organigramme.COLUMNNAME_Poste_ID + " = ?";

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

    public static List<Integer> getPostesResponsablesParCategorie(
            Integer posteId, Integer categorieResponsabiliteId) {

        List<Integer> list = new ArrayList<>();
        if (posteId == null || categorieResponsabiliteId == null) {
            return list;
        }

        String sql = "SELECT " + I_HR_Organigramme.COLUMNNAME_Poste_Responsable_ID
            + " FROM " + I_HR_Organigramme.Table_Name
            + " WHERE " + I_HR_Organigramme.COLUMNNAME_Poste_ID + " = ?"
            + " AND " + I_HR_Organigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID + " = ?";

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

    public static List<Integer> getEmployeesByJob(Integer posteId) {
        if (posteId == null) {
            return new ArrayList<>();
        }
        return HRContratRepository.getBPartnersAffectesAuPoste(
            posteId, new Timestamp(System.currentTimeMillis()), null);
    }

    public static List<Integer> getEmployeesByRoles(List<String> roles, String trxName) {
        List<Integer> list = new ArrayList<>();
        if (roles == null || roles.isEmpty()) {
            return list;
        }

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

    public static List<MHRElementBasePaieEmploye> getDatesDerniersContrats(
            Integer bpartnerId, Timestamp dateMax, String trxName) {

        List<MHRElementBasePaieEmploye> resultat = new ArrayList<>();
        if (bpartnerId == null || dateMax == null) {
            return resultat;
        }

        String sql = "SELECT * FROM " + I_HR_ElementBasePaieEmploye.Table_Name
            + " WHERE " + I_HR_ElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + " = ?"
            + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_IsActive + " = 'Y'"
            + " AND " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + " <= ?"
            + " AND (" + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin + " IS NULL"
            + "   OR " + I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Fin + " >= "
            +            I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut + ")"
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

    // =========================================================================
    // CATEGORIES DE RESPONSABILITE
    // =========================================================================

    public static List<Integer> getEmployeesByCategoriesResponsabilite(
            List<Integer> categorieIds, Timestamp dateReference) {

        List<Integer> list = new ArrayList<>();
        if (categorieIds == null || categorieIds.isEmpty() || dateReference == null) {
            return list;
        }

        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < categorieIds.size(); i++) {
            placeholders.append(i == 0 ? "?" : ", ?");
        }

        String sql =
            "SELECT DISTINCT aff." + I_HR_Affectation.COLUMNNAME_C_BPartner_ID
            + " FROM " + I_HR_Organigramme.Table_Name + " org"
            + " JOIN " + I_HR_Affectation.Table_Name + " aff"
            + "   ON aff." + I_HR_Affectation.COLUMNNAME_HR_Job_ID
            + "    = org." + I_HR_Organigramme.COLUMNNAME_Poste_Responsable_ID
            + " WHERE org." + I_HR_Organigramme.COLUMNNAME_HR_Categorie_Responsabilite_ID
            + "   IN (" + placeholders + ")"
            + " AND org.IsActive = 'Y'"
            + " AND aff." + I_HR_Affectation.COLUMNNAME_Date_Debut + " <= ?"
            + " AND (aff." + I_HR_Affectation.COLUMNNAME_Date_Fin + " IS NULL"
            + "      OR aff." + I_HR_Affectation.COLUMNNAME_Date_Fin + " >= ?)"
            + " AND aff.IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            int idx = 1;
            for (Integer categorieId : categorieIds) {
                pstmt.setInt(idx++, categorieId);
            }
            pstmt.setTimestamp(idx++, dateReference);
            pstmt.setTimestamp(idx++, dateReference);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log.warning("getEmployeesByCategoriesResponsabilite : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return list;
    }

    public static List<Integer> getEmployeesByRoleIds(List<Integer> roleIds, String trxName) {
        List<Integer> list = new ArrayList<>();
        if (roleIds == null || roleIds.isEmpty()) {
            return list;
        }

        StringBuilder placeholders = new StringBuilder();
        for (int i = 0; i < roleIds.size(); i++) {
            placeholders.append(i == 0 ? "?" : ", ?");
        }

        String sql = "SELECT DISTINCT u.C_BPartner_ID"
            + " FROM AD_User u"
            + " JOIN AD_UserRoles ur ON ur.AD_User_ID = u.AD_User_ID"
            + " WHERE ur.AD_Role_ID IN (" + placeholders + ")"
            + " AND u.C_BPartner_ID IS NOT NULL"
            + " AND u.IsActive = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, trxName);
            for (int i = 0; i < roleIds.size(); i++) {
                pstmt.setInt(i + 1, roleIds.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException e) {
            log.warning("getEmployeesByRoleIds : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }
        return list;
    }

}
