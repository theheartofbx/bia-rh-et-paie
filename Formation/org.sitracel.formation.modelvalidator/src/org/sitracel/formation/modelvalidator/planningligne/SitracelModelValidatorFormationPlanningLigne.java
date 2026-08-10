package org.sitracel.formation.modelvalidator.planningligne;

import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;

public class SitracelModelValidatorFormationPlanningLigne implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationPlanningLigne", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            String heureDebut = (String) po.get_Value("Heure_Debut");
            String heureFin = (String) po.get_Value("Heure_Fin");
            Timestamp datePlanning = (Timestamp) po.get_Value("Date_Planning");
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int ligneID = po.get_ID();

            if (heureDebut != null && !heureDebut.isEmpty() && heureFin != null && !heureFin.isEmpty()) {
                if (heureFin.compareTo(heureDebut) <= 0)
                    return "L'heure de fin doit être postérieure à l'heure de début.";
            }

            if (datePlanning != null && planningID > 0) {
                int sessionID = DB.getSQLValueEx(null,
                    "SELECT HR_FormationSession_ID FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", planningID);
                if (sessionID > 0) {
                    Timestamp sessDebut = (Timestamp) DB.getSQLValueTSEx(null,
                        "SELECT Date_Debut FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                    Timestamp sessFin = (Timestamp) DB.getSQLValueTSEx(null,
                        "SELECT Date_Fin FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                    if (sessDebut != null && datePlanning.before(sessDebut))
                        return "La date du planning est antérieure à la date de début de la session.";
                    if (sessFin != null && datePlanning.after(sessFin))
                        return "La date du planning est postérieure à la date de fin de la session.";
                }
            }

            if (datePlanning != null && heureDebut != null && !heureDebut.isEmpty()
                    && heureFin != null && !heureFin.isEmpty()) {
                String lieu = (String) po.get_Value("Lieu");
                int encadrantID = po.get_ValueAsInt("Encadrant_ID");
                if (lieu != null && !lieu.trim().isEmpty()) {
                    int conflit = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                        + " WHERE HR_FormationPlanningLigne_ID != ? AND Date_Planning = ? AND Lieu = ? AND IsActive='Y'"
                        + " AND Heure_Debut < ? AND Heure_Fin > ?",
                        ligneID, datePlanning, lieu, heureFin, heureDebut);
                    if (conflit > 0) return "Collision horaire : le lieu '" + lieu + "' est déjà occupé.";
                }
                if (encadrantID > 0) {
                    int conflit = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                        + " WHERE HR_FormationPlanningLigne_ID != ? AND Date_Planning = ? AND Encadrant_ID = ? AND IsActive='Y'"
                        + " AND Heure_Debut < ? AND Heure_Fin > ?",
                        ligneID, datePlanning, encadrantID, heureFin, heureDebut);
                    if (conflit > 0) return "Collision horaire : l'encadrant est déjà affecté sur ce créneau.";
                }
            }

            if (datePlanning != null && heureDebut != null && !heureDebut.isEmpty()
                    && heureFin != null && !heureFin.isEmpty()) {
                po.set_ValueOfColumn("IsOk", true);
            } else {
                po.set_ValueOfColumn("IsOk", false);
            }
        }

        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE || type == TYPE_AFTER_DELETE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            if (planningID > 0) {
                int total = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y'", planningID);
                int ok = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND IsOk='Y'", planningID);
                String newIsOk = (total > 0 && total == ok) ? "Y" : "N";
                DB.executeUpdateEx("UPDATE HR_FormationPlanning SET IsOk='" + newIsOk + "' WHERE HR_FormationPlanning_ID=" + planningID, null);
            }
        }
        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
