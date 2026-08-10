package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationProgramme {

    public static String beforeSave(PO po, boolean newRecord) {
        if (!newRecord && po.is_ValueChanged("Nombre_Partie")) {
            int programmeID = po.get_ID();
            int nbLignes = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationProgramme_ID=? AND IsActive='Y'",
                programmeID);
            if (nbLignes > 0) {
                return "Impossible de modifier le nombre de parties : " + nbLignes + " ligne(s) de planning existent déjà. Supprimez-les d'abord.";
            }
        }
        return null;
    }

    public static String beforeDelete(PO po) {
        int programmeID = po.get_ID();
        int nbLignes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationProgramme_ID=? AND IsActive='Y'",
            programmeID);
        if (nbLignes > 0) {
            return "Impossible de supprimer : " + nbLignes + " ligne(s) de planning référencent ce programme.";
        }
        return null;
    }
}
