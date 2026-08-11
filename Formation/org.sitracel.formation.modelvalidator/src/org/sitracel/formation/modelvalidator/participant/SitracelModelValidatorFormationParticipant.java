package org.sitracel.formation.modelvalidator.participant;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SitracelModelValidatorFormationParticipant implements ModelValidator {
    private int clientID = -1;
    private static final String TYPE_ABSENCE_FORMATION = "Formation";

    private static int getTypeAbsenceFormationID() {
        return DB.getSQLValueEx(null,
            "SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence=?",
            TYPE_ABSENCE_FORMATION);
    }

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationParticipant", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {

        // ========== BEFORE_NEW ==========
        if (type == TYPE_BEFORE_NEW) {
            int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");

            // Session doit être validée
            if (sessionID > 0) {
                String isValidee = DB.getSQLValueStringEx(null,
                    "SELECT IsValidee FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                if (!"Y".equals(isValidee))
                    return "Impossible d'inscrire un participant : la session n'est pas encore validee.";
            }

            // Planning doit être terminé
            if (planningID > 0) {
                String isOk = DB.getSQLValueStringEx(null,
                    "SELECT IsOk FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", planningID);
                if (!"Y".equals(isOk))
                    return "Impossible d'inscrire : le planning n'est pas encore termine.";

                // Vérifier absence sur les dates du planning
                String msg = verifierAbsencesSurPlanning(bpartnerID, planningID);
                if (msg != null) return msg;
            }

            // Nombre de places
            if (sessionID > 0) {
                int nbPlaces = DB.getSQLValueEx(null,
                    "SELECT COALESCE(Nombre_Places, 0) FROM HR_FormationSession WHERE HR_FormationSession_ID=?", sessionID);
                if (nbPlaces > 0) {
                    int nbPart = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationSession_ID=? AND IsActive='Y'", sessionID);
                    if (nbPart >= nbPlaces)
                        return "Toutes les places sont prises (" + nbPlaces + "/" + nbPlaces + ").";
                }
            }
        }

        // ========== BEFORE_CHANGE ==========
        if (type == TYPE_BEFORE_CHANGE) {
            // Si le planning change, vérifier les absences sur le nouveau planning
            if (po.is_ValueChanged("HR_FormationPlanning_ID")) {
                int newPlanningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
                int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");

                if (newPlanningID > 0) {
                    String isOk = DB.getSQLValueStringEx(null,
                        "SELECT IsOk FROM HR_FormationPlanning WHERE HR_FormationPlanning_ID=?", newPlanningID);
                    if (!"Y".equals(isOk))
                        return "Impossible d'assigner : le planning n'est pas encore termine.";

                    String msg = verifierAbsencesSurPlanning(bpartnerID, newPlanningID);
                    if (msg != null) return msg;
                }
            }
        }

        // ========== AFTER_NEW / AFTER_CHANGE : créer/mettre à jour absences ==========
        if (type == TYPE_AFTER_NEW || type == TYPE_AFTER_CHANGE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");

            if (type == TYPE_AFTER_CHANGE && po.is_ValueChanged("HR_FormationPlanning_ID")) {
                // Supprimer les absences de l'ancien planning
                int oldPlanningID = ((Number) po.get_ValueOld("HR_FormationPlanning_ID")).intValue();
                if (oldPlanningID > 0) {
                    supprimerAbsencesParticipant(bpartnerID, oldPlanningID);
                }
            }

            // Créer les absences pour le nouveau planning
            if (planningID > 0) {
                if (type == TYPE_AFTER_NEW || (type == TYPE_AFTER_CHANGE && po.is_ValueChanged("HR_FormationPlanning_ID"))) {
                    creerAbsencesParticipant(bpartnerID, planningID);
                }
            }
        }

        // ========== BEFORE_DELETE : supprimer absences ==========
        if (type == TYPE_BEFORE_DELETE) {
            int planningID = po.get_ValueAsInt("HR_FormationPlanning_ID");
            int bpartnerID = po.get_ValueAsInt("C_BPartner_ID");
            if (planningID > 0) {
                supprimerAbsencesParticipant(bpartnerID, planningID);
            }
        }

        return null;
    }

    private String verifierAbsencesSurPlanning(int bpartnerID, int planningID) {
        int nbAbsences = DB.getSQLValueEx(null,
            "SELECT COUNT(*) FROM HR_Absence a"
            + " JOIN HR_FormationPlanningLigne pl ON pl.Date_Planning = a.Date_Absence"
            + " WHERE pl.HR_FormationPlanning_ID=? AND a.C_BPartner_ID=?"
            + " AND a.IsActive='Y' AND pl.IsActive='Y' AND pl.Date_Planning IS NOT NULL"
            + " AND a.HR_Type_Absence_ID != COALESCE((SELECT HR_Type_Absence_ID FROM HR_Type_Absence WHERE Nom_Absence='Formation'), -1)",
            planningID, bpartnerID);
        if (nbAbsences > 0)
            return "Impossible d'assigner : l'employe a " + nbAbsences
                + " jour(s) d'absence (conge, suspension, etc.) sur les dates de ce planning.";
        return null;
    }

    private void creerAbsencesParticipant(int bpartnerID, int planningID) {
        int typeAbsID = getTypeAbsenceFormationID();
        if (typeAbsID <= 0) return;

        int clientId = Env.getAD_Client_ID(Env.getCtx());
        int orgId = Env.getAD_Org_ID(Env.getCtx());
        int userID = Env.getAD_User_ID(Env.getCtx());

        // Infos émetteur
        int approuvParID = DB.getSQLValueEx(null,
            "SELECT C_BPartner_ID FROM AD_User WHERE AD_User_ID=?", userID);
        if (approuvParID <= 0) approuvParID = 0;
        String matriculeApprouveur = DB.getSQLValueStringEx(null,
            "SELECT Value FROM C_BPartner WHERE C_BPartner_ID=?", approuvParID);
        if (matriculeApprouveur == null) matriculeApprouveur = "N/A";
        int posteApprouveurID = DB.getSQLValueEx(null,
            "SELECT HR_Job_ID FROM HR_Affectation WHERE C_BPartner_ID=? AND IsActive='Y' ORDER BY Date_Debut DESC FETCH FIRST 1 ROWS ONLY", approuvParID);
        if (posteApprouveurID < 0) posteApprouveurID = 0;

        // Infos employé
        String matriculeEmploye = DB.getSQLValueStringEx(null,
            "SELECT Value FROM C_BPartner WHERE C_BPartner_ID=?", bpartnerID);
        if (matriculeEmploye == null) matriculeEmploye = "N/A";
        int posteEmployeID = DB.getSQLValueEx(null,
            "SELECT HR_Job_ID FROM HR_Affectation WHERE C_BPartner_ID=? AND IsActive='Y' ORDER BY Date_Debut DESC FETCH FIRST 1 ROWS ONLY", bpartnerID);
        if (posteEmployeID < 0) posteEmployeID = 0;

        // Parcourir les dates du planning
        String sql = "SELECT Date_Planning FROM HR_FormationPlanningLigne"
            + " WHERE HR_FormationPlanning_ID=? AND IsActive='Y' AND Date_Planning IS NOT NULL"
            + " ORDER BY Date_Planning";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, planningID);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Timestamp datePlanning = rs.getTimestamp("Date_Planning");

                // Vérifier qu'il n'y a pas déjà une absence Formation à cette date
                int dejaPresent = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_Absence WHERE C_BPartner_ID=? AND Date_Absence=? AND HR_Type_Absence_ID=? AND IsActive='Y'",
                    bpartnerID, datePlanning, typeAbsID);
                if (dejaPresent > 0) continue;

                int absID = DB.getNextID(clientId, "HR_Absence", null);
                DB.executeUpdateEx(
                    "INSERT INTO HR_Absence ("
                    + "HR_Absence_ID, AD_Client_ID, AD_Org_ID,"
                    + " Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                    + " C_BPartner_ID, Date_Absence, HR_Type_Absence_ID,"
                    + " Date_Emission, IsConge, IsDemandeExplication,"
                    + " IsCongeTraite, IsDemandeExplicationTraite,"
                    + " Emis_Par_Nom_ID, Emis_Par_Poste_ID, Emis_Par_Matricule,"
                    + " Matricule_Employe, Poste_Employe_ID"
                    + ") VALUES ("
                    + absID + ", " + clientId + ", " + orgId + ","
                    + " now(), " + userID + ", now(), " + userID + ", 'Y',"
                    + " " + bpartnerID + ", " + DB.TO_DATE(datePlanning) + ", " + typeAbsID + ","
                    + " now(), 'N', 'N', 'Y', 'Y',"
                    + " " + approuvParID + ", " + posteApprouveurID + ", '" + matriculeApprouveur + "',"
                    + " '" + matriculeEmploye + "',"
                    + " " + posteEmployeID + ")",
                    null);
            }
        } catch (Exception e) {
            // Log mais ne pas bloquer
        } finally {
            DB.close(rs, pstmt);
        }
    }

    private void supprimerAbsencesParticipant(int bpartnerID, int planningID) {
        int typeAbsID = getTypeAbsenceFormationID();
        if (typeAbsID <= 0) return;

        DB.executeUpdateEx(
            "DELETE FROM HR_Absence WHERE C_BPartner_ID=" + bpartnerID
            + " AND HR_Type_Absence_ID=" + typeAbsID
            + " AND Date_Absence IN ("
            + "   SELECT Date_Planning FROM HR_FormationPlanningLigne"
            + "   WHERE HR_FormationPlanning_ID=" + planningID
            + "   AND IsActive='Y' AND Date_Planning IS NOT NULL"
            + " )",
            null);
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
