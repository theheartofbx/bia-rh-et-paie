package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationPlanning {

    public static String beforeDelete(PO po) {
        int planningID = po.get_ID();
        int nbParticipants = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
            planningID);
        if (nbParticipants > 0) {
            return "Impossible de supprimer : " + nbParticipants + " participant(s) sont assignés à ce planning.";
        }
        return null;
    }

    public static String beforeSave(PO po, boolean newRecord) {
        if (!newRecord && po.is_ValueChanged("IsOk")) {
            String newIsOk = (String) po.get_Value("IsOk");
            if ("N".equals(newIsOk)) {
                int planningID = po.get_ID();
                int nbPart = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbPart > 0) {
                    return "Impossible de remettre le planning en non-terminé : " + nbPart + " participant(s) y sont assignés.";
                }
            }
        }
        return null;
    }
}
