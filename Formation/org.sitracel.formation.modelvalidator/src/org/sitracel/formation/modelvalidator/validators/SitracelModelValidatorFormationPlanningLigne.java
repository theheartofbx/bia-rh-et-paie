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

        // S3 : Date_Planning comprise entre Date_Debut et Date_Fin de la session
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

        // M7 : Anti-collision horaire (même lieu ou même encadrant, même date)
        if (datePlanning != null && heureDebut != null && !heureDebut.isEmpty()
                && heureFin != null && !heureFin.isEmpty()) {
            String lieu = (String) po.get_Value("Lieu");
            int encadrantID = po.get_ValueAsInt("Encadrant_ID");

            // Collision par lieu
            if (lieu != null && !lieu.trim().isEmpty()) {
                int conflitLieu = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanningLigne_ID != ?"
                    + " AND Date_Planning = ?"
                    + " AND Lieu = ?"
                    + " AND IsActive='Y'"
                    + " AND Heure_Debut < ?"
                    + " AND Heure_Fin > ?",
                    ligneID, datePlanning, lieu, heureFin, heureDebut);
                if (conflitLieu > 0) {
                    return "Collision horaire : le lieu '" + lieu + "' est déjà occupé sur ce créneau.";
                }
            }

            // Collision par encadrant
            if (encadrantID > 0) {
                int conflitEncadrant = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanningLigne_ID != ?"
                    + " AND Date_Planning = ?"
                    + " AND Encadrant_ID = ?"
                    + " AND IsActive='Y'"
                    + " AND Heure_Debut < ?"
                    + " AND Heure_Fin > ?",
                    ligneID, datePlanning, encadrantID, heureFin, heureDebut);
                if (conflitEncadrant > 0) {
                    return "Collision horaire : l'encadrant est déjà affecté sur ce créneau.";
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
        // M2 : Vérifier si TOUTES les lignes du planning sont IsOk → mettre Planning.IsOk
        int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
        if (planningID > 0) {
            int totalLignes = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                + " WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                planningID);
            int lignesOk = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                + " WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND IsOk='Y'",
                planningID);

            String newIsOk = (totalLignes > 0 && totalLignes == lignesOk) ? "Y" : "N";
            DB.executeUpdateEx(
                "UPDATE HR_FormationPlanning SET IsOk='" + newIsOk + "' WHERE HR_FormationPlanning_ID=" + planningID,
                null);
        }
        return null;
    }
}
