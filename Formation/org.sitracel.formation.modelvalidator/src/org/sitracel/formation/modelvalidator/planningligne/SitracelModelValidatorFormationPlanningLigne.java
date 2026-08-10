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

        // ========== BEFORE_NEW / BEFORE_CHANGE ==========
        if (type == TYPE_BEFORE_NEW || type == TYPE_BEFORE_CHANGE) {
            String heureDebut = (String) po.get_Value("Heure_Debut");
            String heureFin = (String) po.get_Value("Heure_Fin");
            Timestamp datePlanning = (Timestamp) po.get_Value("Date_Planning");
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int programmeID = po.get_ValueAsInt("HR_FormationProgramme_ID");
            int moduleID = po.get_ValueAsInt("HR_FormationModule_ID");
            int numeroPartie = po.get_ValueAsInt("Numero_Partie");
            int ligneID = po.get_ID();

            // Anti-doublon : même planning + programme + module + numéro partie
            if (planningID > 0 && programmeID > 0 && moduleID > 0 && numeroPartie > 0) {
                int doublon = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                    + " WHERE HR_FormationPlanning_ID=?"
                    + " AND HR_FormationProgramme_ID=?"
                    + " AND HR_FormationModule_ID=?"
                    + " AND Numero_Partie=?"
                    + " AND HR_FormationPlanningLigne_ID != ?"
                    + " AND IsActive='Y'",
                    planningID, programmeID, moduleID, numeroPartie, ligneID);
                if (doublon > 0)
                    return "Cette ligne existe déjà : même module, même partie (" + numeroPartie + ").";
            }

            // S2 : Heure_Fin > Heure_Debut
            if (heureDebut != null && !heureDebut.isEmpty() && heureFin != null && !heureFin.isEmpty()) {
                if (heureFin.compareTo(heureDebut) <= 0)
                    return "L'heure de fin doit être postérieure à l'heure de début.";
            }

            // S3 : Date_Planning dans la période de la session
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

            // M7 : Anti-collision horaire
            if (datePlanning != null && heureDebut != null && !heureDebut.isEmpty()
                    && heureFin != null && !heureFin.isEmpty()) {
                String lieu = (String) po.get_Value("Lieu");
                int encadrantID = po.get_ValueAsInt("Encadrant_ID");

                if (lieu != null && !lieu.trim().isEmpty()) {
                    int c = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                        + " WHERE HR_FormationPlanningLigne_ID != ? AND Date_Planning = ? AND Lieu = ? AND IsActive='Y'"
                        + " AND Heure_Debut < ? AND Heure_Fin > ?",
                        ligneID, datePlanning, lieu, heureFin, heureDebut);
                    if (c > 0)
                        return "Collision horaire : le lieu '" + lieu + "' est déjà occupé sur ce créneau.";
                }

                if (encadrantID > 0) {
                    int c = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanningLigne"
                        + " WHERE HR_FormationPlanningLigne_ID != ? AND Date_Planning = ? AND Encadrant_ID = ? AND IsActive='Y'"
                        + " AND Heure_Debut < ? AND Heure_Fin > ?",
                        ligneID, datePlanning, encadrantID, heureFin, heureDebut);
                    if (c > 0)
                        return "Collision horaire : l'encadrant est déjà affecté sur ce créneau.";
                }
            }

            // Bloquer modification si planning a des participants assignés et IsOk passe à N
            if (type == TYPE_BEFORE_CHANGE && po.is_ValueChanged("IsOk") && "N".equals(po.get_Value("IsOk"))) {
                int nbP = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbP > 0)
                    return "Impossible de modifier : " + nbP + " participant(s) assignés à ce planning.";
            }

            // M1 : Auto IsOk quand date + heures renseignées
            if (datePlanning != null
                    && heureDebut != null && !heureDebut.isEmpty()
                    && heureFin != null && !heureFin.isEmpty()) {
                po.set_ValueOfColumn("IsOk", true);
            } else {
                po.set_ValueOfColumn("IsOk", false);
            }
        }

        // ========== AFTER_NEW / AFTER_CHANGE / AFTER_DELETE : recalcul IsOk parent ==========
        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE || type == TYPE_AFTER_DELETE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            if (planningID > 0) {
                int total = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y'", planningID);
                int ok = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND IsOk='Y'", planningID);
                String v = (total > 0 && total == ok) ? "Y" : "N";
                DB.executeUpdateEx("UPDATE HR_FormationPlanning SET IsOk='" + v + "' WHERE HR_FormationPlanning_ID=" + planningID, null);
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
