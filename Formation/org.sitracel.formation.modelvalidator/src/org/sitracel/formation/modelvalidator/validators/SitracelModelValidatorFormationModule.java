package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationModule {

    public static String beforeDelete(PO po) {
        int moduleID = po.get_ID();
        int nbProgrammes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationModule_ID=? AND IsActive='Y'",
            moduleID);
        if (nbProgrammes > 0) {
            return "Impossible de supprimer : ce module est utilisé dans " + nbProgrammes + " programme(s).";
        }
        return null;
    }
}
