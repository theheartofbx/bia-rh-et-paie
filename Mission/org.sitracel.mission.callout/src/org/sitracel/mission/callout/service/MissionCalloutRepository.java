package org.sitracel.mission.callout.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.sitracel.employe.HRContratService;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.mission.model.MHRTypeValidation;

/**
 * Repository — requêtes SQL propres au callout mission.
 *
 * Remplace SqlControlerMission (supprimé).
 * isUserRH() délègue maintenant à HRContratService (outils) — le
 * ModelValidator mission en a aussi besoin sans pouvoir dépendre de ce
 * bundle callout, donc la logique vit désormais dans outils.
 */
public final class MissionCalloutRepository {

    private static final CLogger log = CLogger.getCLogger(MissionCalloutRepository.class);

    private MissionCalloutRepository() {}

    // =========================================================================
    // ÉTAT DE VALIDATION
    // =========================================================================

    public static Timestamp getDateValidationFinale(int idMission, String trxName) {
        String sql =
            "SELECT MAX(mv." + MHRMissionValidation.COLUMNNAME_Date_Validation + ") "
            + "FROM " + MHRMissionValidation.Table_Name + " mv "
            + "JOIN " + MHRTypeValidation.Table_Name + " tv "
            + "  ON tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID
            + "   = mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID
            + " WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + " = ? "
            + " AND mv." + MHRMissionValidation.COLUMNNAME_IsValidee + " = 'Y' "
            + " AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' "
            + " AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + " = 'Y' "
            + " AND tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + " = 'Y' "
            + " AND tv." + MHRTypeValidation.COLUMNNAME_IsActive + " = 'Y' "
            + " AND NOT EXISTS ("
            + "   SELECT 1 FROM " + MHRTypeValidation.Table_Name + " tv2 "
            + "   WHERE tv2." + MHRTypeValidation.COLUMNNAME_IsObligatoire + " = 'Y' "
            + "   AND tv2." + MHRTypeValidation.COLUMNNAME_IsActive + " = 'Y' "
            + "   AND NOT EXISTS ("
            + "     SELECT 1 FROM " + MHRMissionValidation.Table_Name + " mv2 "
            + "     WHERE mv2." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + " = ? "
            + "     AND mv2." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID
            + "           = tv2." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID
            + "     AND mv2." + MHRMissionValidation.COLUMNNAME_IsValidee + " = 'Y' "
            + "     AND COALESCE(mv2." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' "
            + "     AND mv2." + MHRMissionValidation.COLUMNNAME_IsActive + " = 'Y' "
            + "   )"
            + " )";

        return DB.getSQLValueTS(trxName, sql, idMission, idMission);
    }

    public static boolean existeRejetObligatoire(int idMission, String trxName)
            throws Exception {
        String sql =
            "SELECT 1 FROM " + MHRMissionValidation.Table_Name + " mv "
            + "JOIN " + MHRTypeValidation.Table_Name + " tv "
            + "  ON tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID
            + "   = mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID
            + " WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + " = ? "
            + " AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'Y' "
            + " AND tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + " = 'Y' "
            + " AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + " = 'Y'";

        return DB.getSQLValue(trxName, sql, idMission) == 1;
    }

    public static boolean isMissionValide(int idMission, String trxName)
            throws Exception {
        String sql =
            "SELECT NOT EXISTS ("
            + "  SELECT 1 FROM " + MHRTypeValidation.Table_Name + " tv "
            + "  WHERE tv." + MHRTypeValidation.COLUMNNAME_IsObligatoire + " = 'Y' "
            + "  AND tv." + MHRTypeValidation.COLUMNNAME_IsActive + " = 'Y' "
            + "  AND NOT EXISTS ("
            + "    SELECT 1 FROM " + MHRMissionValidation.Table_Name + " mv "
            + "    WHERE mv." + MHRMissionValidation.COLUMNNAME_HR_Mission_ID + " = ? "
            + "    AND mv." + MHRMissionValidation.COLUMNNAME_HR_TypeValidation_ID
            + "          = tv." + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID
            + "    AND mv." + MHRMissionValidation.COLUMNNAME_IsValidee + " = 'Y' "
            + "    AND COALESCE(mv." + MHRMissionValidation.COLUMNNAME_IsRejetee + ", 'N') = 'N' "
            + "    AND mv." + MHRMissionValidation.COLUMNNAME_IsActive + " = 'Y' "
            + "  )"
            + ")";

        return DB.getSQLValue(trxName, sql, idMission) == 1;
    }

    // =========================================================================
    // DÉTERMINATION DU TYPE DE VALIDATION D'UN VOTANT
    // =========================================================================

    /** @deprecated Utiliser {@link HRContratService#isUserRH} */
    @Deprecated
    public static boolean isUserRH(int adUserId) {
        return HRContratService.isUserRH(adUserId, null);
    }

    public static Integer getCategorieResponsabilite(int hrMissionId, int adUserId) {
        String sql = "SELECT adempiere.fn_get_hr_mission_categorie_responsabilite(?, ?)";

        int value = DB.getSQLValue(null, sql, hrMissionId, adUserId);
        return value > 0 ? value : null;
    }

    public static Integer getTypeValidationIdByName(String keyword) {
        if (keyword == null) return null;

        String sql =
            "SELECT " + MHRTypeValidation.COLUMNNAME_HR_TypeValidation_ID + " "
            + "FROM " + MHRTypeValidation.Table_Name + " "
            + "WHERE UPPER(Name) LIKE ? "
            + "AND IsActive = 'Y' "
            + "ORDER BY Created DESC";

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
            log.warning("getTypeValidationIdByName : " + e.getMessage());
        } finally {
            DB.close(rs, pstmt);
        }

        return null;
    }
}
