package org.sitracel.formation.modelvalidator.validators;

import java.sql.Timestamp;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationPlanningLigne {

    public static String beforeSave(PO po, boolean newRecord) {
        String heureDebut = (String) po.get_Value("Heure_Debut");
        String heureFin = (String) po.get_Value("Heure_Fin");
        Timestamp datePlanning = (Timestamp) po.get_Value("Date_Planning");
        int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
        int ligneID = po.get_ID();

        // S2 : Heure_Fin > Heure_Debut
        if (heureDebut != null && !heureDebut.isEmpty()
                && heureFin != null && !heureFin.isEmpty()) {
            if (heureFin.compareTo(heureDebut) <= 0) {
                return "L'heure de fin doit être postérieure à l'heure de début.";
            }
        }

        // S3 : Date_Planning dans la période de la session
        if (datePlanning != null && planningID > 0) {
            int sessionID = DB.getSQLValueEx(null,
                "SELECT HR_FormationSession_ID FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?",
                planningID);
            if (sessionID > 0) {
                Timestamp sessDebut = (Timestamp) DB.getSQLValueTSEx(null,
                    "SELECT Date_Debut FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                    sessionID);
                Timestamp sessFin = (Timestamp) DB.getSQLValueTSEx(null,
                    "SELECT Date_Fin FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                    sessionID);
                if (sessDebut != null && datePlanning.before(sessDebut)) {
                    return "La date du planning est antérieure à la date de début de la session.";
                }
                if (sessFin != null && datePlanning.after(sessFin)) {
                    return "La date du planning est postérieure à la date de fin de la session.";
                }
            }
        }

        // M7 : Anti-collision horaire
        if (datePlanning != null && heureDebut != null && !heureDebut.isEmpty()
                && heureFin != null && !heureFin.isEmpty()) {
            String lieu = (String) po.get_Value("Lieu");
            int encadrantID = po.get_ValueAsInt("Encadrant_ID");

            if (lieu != null && !lieu.trim().isEmpty()) {
                int conflitLieu = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanningLigne_ID != ?"
                    + " AND Date_Planning = ? AND Lieu = ? AND IsActive='Y'"
                    + " AND Heure_Debut < ? AND Heure_Fin > ?",
                    ligneID, datePlanning, lieu, heureFin, heureDebut);
                if (conflitLieu > 0) {
                    return "Collision horaire : le lieu '" + lieu + "' est déjà occupé sur ce créneau.";
                }
            }

            if (encadrantID > 0) {
                int conflitEncadrant = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanningLigne_ID != ?"
                    + " AND Date_Planning = ? AND Encadrant_ID = ? AND IsActive='Y'"
                    + " AND Heure_Debut < ? AND Heure_Fin > ?",
                    ligneID, datePlanning, encadrantID, heureFin, heureDebut);
                if (conflitEncadrant > 0) {
                    return "Collision horaire : l'encadrant est déjà affecté sur ce créneau.";
                }
            }
        }

        // Bloquer modification si planning a des participants assignés
        if (!newRecord && po.is_ValueChanged("IsOk")) {
            String newIsOk = (String) po.get_Value("IsOk");
            if ("N".equals(newIsOk)) {
                int nbPartSurPlanning = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant"
                    + " WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbPartSurPlanning > 0) {
                    return "Impossible de modifier : " + nbPartSurPlanning + " participant(s) sont déjà assignés à ce planning.";
                }
            }
        }

        // M1 : Auto IsOk quand date + heures renseignées
        if (datePlanning != null
                && heureDebut != null && !heureDebut.isEmpty()
                && heureFin != null && !heureFin.isEmpty()) {
            po.set_ValueOfColumn("IsOk", true);
        } else {
            po.set_ValueOfColumn("IsOk", false);
        }

        return null;
    }

    public static String afterSave(PO po, boolean newRecord) {
        recalculerIsOkPlanning(po.get_ValueAsInt("HR_FormationPlanning_ID"));
        return null;
    }

    public static String afterDelete(PO po) {
        recalculerIsOkPlanning(po.get_ValueAsInt("HR_FormationPlanning_ID"));
        return null;
    }

    private static void recalculerIsOkPlanning(int planningID) {
        if (planningID <= 0) return;
        int totalLignes = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
            planningID);
        int lignesOk = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND IsOk='Y'",
            planningID);
        String newIsOk = (totalLignes > 0 && totalLignes == lignesOk) ? "Y" : "N";
        DB.executeUpdateEx(
            "UPDATE HR_FormationPlanning SET IsOk='" + newIsOk + "' WHERE HR_FormationPlanning_ID=" + planningID,
            null);
    }
}
