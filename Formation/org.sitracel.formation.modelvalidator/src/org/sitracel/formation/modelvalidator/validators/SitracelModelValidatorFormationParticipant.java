package org.sitracel.formation.modelvalidator.validators;

import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationParticipant {

    public static String beforeNew(PO po) {
        int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
        int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");

        // M3 : Le planning assigné doit être terminé (IsOk='Y')
        if (planningID > 0) {
            String isOk = DB.getSQLValueStringEx(null,
                "SELECT IsOk FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?",
                planningID);
            if (!"Y".equals(isOk)) {
                return "Impossible d'inscrire un participant : le planning n'est pas encore terminé (tous les créneaux doivent être définis).";
            }
        }

        // S6 : Nombre de participants <= Nombre_Places
        if (sessionID > 0) {
            int nbPlaces = DB.getSQLValueEx(null,
                "SELECT COALESCE(Nombre_Places, 0) FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                sessionID);
            if (nbPlaces > 0) {
                int nbParticipants = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant"
                    + " WHERE HR_FormationSession_ID=? AND IsActive='Y'",
                    sessionID);
                if (nbParticipants >= nbPlaces) {
                    return "Impossible d'inscrire : toutes les places sont prises (" + nbPlaces + "/" + nbPlaces + ").";
                }
            }
        }

        return null;
    }
}
