package org.sitracel.mission.callout;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.compiere.util.DB;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.mission.model.MHRTypeValidation;

public class SqlControlerMission {
	
	public static Timestamp getDateValidationFinale(int idMission, String trxName) {

	    String sql =
	        "SELECT MAX(mv." + MHRMissionValidation.COLUMNNAME_Date_Validation + ") " +
	        "FROM " + MHRMissionValidation.Table_Name + " mv " +
	        "JOIN " + MHRTypeValidation.Table_Name + " tv " +
	        "  ON tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID +
	        "     = mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID + " " +
	        "WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + "=? " +
	        "AND mv." + MHRMissionValidation.COLUMNNAME_IsValidee + "='Y' " +
	        "AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' " +
	        "AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + "='Y' " +
	        "AND tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + "='Y' " +
	        "AND tv." + MHRTypeValidation.COLUMNNAME_IsActive + "='Y' " +
	        "AND NOT EXISTS ( " +
	        "   SELECT 1 FROM " + MHRTypeValidation.Table_Name + " tv2 " +
	        "   WHERE tv2." + MHRTypeValidation.COLUMNNAME_IsObligatoire + "='Y' " +
	        "   AND tv2." + MHRTypeValidation.COLUMNNAME_IsActive + "='Y' " +
	        "   AND NOT EXISTS ( " +
	        "       SELECT 1 FROM " + MHRMissionValidation.Table_Name + " mv2 " +
	        "       WHERE mv2." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + "=? " +
	        "       AND mv2." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID +
	        "             = tv2." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID + " " +
	        "       AND mv2." + MHRMissionValidation.COLUMNNAME_IsValidee + "='Y' " +
	        "       AND COALESCE(mv2." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' " +
	        "       AND mv2." + MHRMissionValidation.COLUMNNAME_IsActive + "='Y' " +
	        "   ) " +
	        ")";

	    return DB.getSQLValueTS(trxName, sql, idMission, idMission);
	}
	
	public static boolean existeRejetObligatoire(int idMission, String trxName) throws Exception {

	    String sql =
	        "SELECT 1 FROM " + MHRMissionValidation.Table_Name + " mv " +
	        "JOIN " + MHRTypeValidation.Table_Name + " tv " +
	        " ON tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID +
	        " = mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID +
	        " WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + "=? " +
	        " AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'Y' " +
	        " AND tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + "='Y' " +
	        " AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + "='Y' ";

	    return DB.getSQLValue(trxName, sql, idMission) > 0;
	}
	
	public static boolean isMissionValide(int idMission, String trxName) {

	    String sql =
	        "SELECT 1 " +
	        "FROM " + MHRTypeValidation.Table_Name + " tv " +
	        "WHERE tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + "='Y' " +
	        "AND tv." + MHRTypeValidation.COLUMNNAME_IsActive + "='Y' " +
	        "AND NOT EXISTS ( " +
	        "   SELECT 1 " +
	        "   FROM " + MHRMissionValidation.Table_Name + " mv " +
	        "   WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID +
	        "         = tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID +
	        "   AND mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + "=? " +
	        "   AND mv." + MHRMissionValidation.COLUMNNAME_IsValidee + "='Y' " +
	        "   AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' " +
	        "   AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + "='Y' " +
	        ")";

	    Integer found = DB.getSQLValue(trxName, sql, idMission);

	    return found == null;
	}
	
	public static boolean isUserRH(int adUserId) {
        String sql =
            "SELECT 1 " +
            "FROM AD_User_Roles ur " +
            "JOIN AD_Role r ON r.AD_Role_ID = ur.AD_Role_ID " +
            "WHERE ur.AD_User_ID=? " +
            "AND r.Name IN ('Ressource Humaine', 'Ressource Humaine - Responsable')";

        return DB.getSQLValue(null, sql, adUserId) == 1;
    }

    public static Integer getCategorieResponsabilite(int hrMissionId, int adUserId) {
        String sql =
            "SELECT adempiere.fn_get_hr_mission_categorie_responsabilite(?, ?)";

        int value = DB.getSQLValue(null, sql, hrMissionId, adUserId);
        return value > 0 ? value : null;
    }

    public static Integer getTypeValidationIdByName(String keyword) {
        String sql =
            "SELECT " + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID + " "+
            "FROM " + MHRTypeValidation.Table_Name + " "+
            "WHERE UPPER(Name) LIKE ? " +
            "AND IsActive='Y' " +
            "ORDER BY Created DESC";

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setString(1, "%" + keyword.toUpperCase() + "%");
            rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DB.close(rs, pstmt);
        }

        return null;
    }

}
