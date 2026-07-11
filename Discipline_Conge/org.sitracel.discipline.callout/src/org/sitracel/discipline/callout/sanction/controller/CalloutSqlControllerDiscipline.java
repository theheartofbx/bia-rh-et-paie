package org.sitracel.discipline.callout.sanction.controller;

import org.compiere.util.DB;
import org.sitracel.discipline.model.MHRSanctionAutorisation;

/**
 * @deprecated SQL callout discipline.
 * Conservé pour compatibilité avec CalloutTypeSanction.
 */
@Deprecated
public class CalloutSqlControllerDiscipline {

    private CalloutSqlControllerDiscipline() {}

    /**
     * Retourne l'ID de l'autorisation de sanction pour un type,
     * un poste employé et un poste émetteur donnés.
     */
    public static Integer getAutorisationSanctionID(Integer typeSanctionId,
                                                     Integer posteEmployeId,
                                                     Integer posteEmetteurId,
                                                     String trxName) {
        if (typeSanctionId == null || posteEmployeId == null || posteEmetteurId == null) {
            return null;
        }

        String sql = "SELECT " + MHRSanctionAutorisation.COLUMNNAME_HR_Sanction_Autorisation_ID
            + " FROM " + MHRSanctionAutorisation.Table_Name
            + " WHERE " + MHRSanctionAutorisation.COLUMNNAME_HR_TypeSanction_ID + " = ?"
            + " AND IsActive = 'Y'"
            + " ORDER BY Created DESC";

        int id = DB.getSQLValue(trxName, sql, typeSanctionId);
        return id > 0 ? id : null;
    }
}
