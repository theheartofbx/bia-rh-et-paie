package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationCatalogue {

    public static String beforeDelete(PO po) {
        int catalogueID = po.get_ID();
        int nbSessions = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationSession WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
            catalogueID);
        if (nbSessions > 0) {
            return "Impossible de supprimer : " + nbSessions + " session(s) existent pour cette formation.";
        }
        return null;
    }
}
