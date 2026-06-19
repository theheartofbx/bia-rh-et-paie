package org.sitracel.mission.callout.service;

import java.sql.Timestamp;

import org.compiere.util.DB;
import org.sitracel.mission.model.MHRMissionValidation;
import org.sitracel.mission.model.MHRTypeValidation;

/**
 * Repository — requêtes SQL propres au callout mission.
 *
 * Regroupe les requêtes nécessaires au recalcul de l'état
 * de validation d'une mission.
 *
 * Remplace SqlControlerMission.
 */
public final class MissionCalloutRepository {

    private MissionCalloutRepository() {}

    // =========================================================================
    // ÉTAT DE VALIDATION
    // =========================================================================

    /**
     * Retourne la date de validation finale d'une mission.
     *
     * La date finale est le MAX des dates de validation des validations
     * obligatoires, uniquement si TOUTES les validations obligatoires
     * sont validées (et aucune rejetée).
     */
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

    /**
     * Vérifie si au moins une validation obligatoire est rejetée.
     * Si oui, la mission est considérée rejetée.
     */
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

    /**
     * Vérifie si toutes les validations obligatoires sont validées.
     * Si oui, la mission peut être considérée comme validée.
     */
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
}
