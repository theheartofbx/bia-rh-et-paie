package org.sitracel.formation.modelvalidator.planning;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.compiere.model.MClient;
import org.compiere.model.ModelValidationEngine;
import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class SitracelModelValidatorFormationPlanning implements ModelValidator {
    private int clientID = -1;

    @Override
    public void initialize(ModelValidationEngine engine, MClient client) {
        if (client != null) clientID = client.getAD_Client_ID();
        engine.addModelChange("HR_FormationPlanning", this);
    }

    @Override
    public int getAD_Client_ID() { return clientID; }

    @Override
    public String login(int AD_Org_ID, int AD_Role_ID, int AD_User_ID) { return null; }

    @Override
    public String modelChange(PO po, int type) throws Exception {
        int planningID = po.get_ID();
        int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");

        // ========== BEFORE_NEW ==========
        if (type == TYPE_BEFORE_NEW) {
            // G2 : Bloquer si le catalogue de la session n'a aucun programme
            if (sessionID > 0) {
                int catalogueID = DB.getSQLValueEx(null,
                    "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                    sessionID);
                if (catalogueID > 0) {
                    int nbProg = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
                        catalogueID);
                    if (nbProg == 0)
                        return "Impossible de creer un planning : le catalogue n'a aucun programme defini. Ajoutez des modules au catalogue d'abord.";
                }
            }

            // G3 : Anti-doublon nom dans la même session
            String nom = (String) po.get_Value("Name");
            if (nom != null && !nom.trim().isEmpty() && sessionID > 0) {
                int doublon = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanning"
                    + " WHERE HR_FormationSession_ID=? AND Name=? AND IsActive='Y'",
                    sessionID, nom);
                if (doublon > 0)
                    return "Un planning avec le nom '" + nom + "' existe deja dans cette session.";
            }
        }

        // ========== BEFORE_CHANGE ==========
        if (type == TYPE_BEFORE_CHANGE) {
            // Bloquer IsOk -> N si participants assignés
            if (po.is_ValueChanged("IsOk")) {
                String newIsOk = (String) po.get_Value("IsOk");
                if ("N".equals(newIsOk)) {
                    int nbPart = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                        planningID);
                    if (nbPart > 0)
                        return "Impossible : " + nbPart + " participant(s) sont assignes a ce planning.";
                }
            }

            // Bloquer changement de session si des lignes existent
            if (po.is_ValueChanged("HR_FormationSession_ID")) {
                int nbLignes = DB.getSQLValueEx(null,
                    "SELECT COUNT(*) FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                    planningID);
                if (nbLignes > 0)
                    return "Impossible de changer la session : " + nbLignes + " ligne(s) de planning existent. Supprimez-les d'abord.";
            }

            // G3 : Anti-doublon nom en cas de renommage
            if (po.is_ValueChanged("Name")) {
                String nom = (String) po.get_Value("Name");
                if (nom != null && !nom.trim().isEmpty() && sessionID > 0) {
                    int doublon = DB.getSQLValueEx(null,
                        "SELECT COUNT(*) FROM HR_FormationPlanning"
                        + " WHERE HR_FormationSession_ID=? AND Name=? AND HR_FormationPlanning_ID != ? AND IsActive='Y'",
                        sessionID, nom, planningID);
                    if (doublon > 0)
                        return "Un planning avec le nom '" + nom + "' existe deja dans cette session.";
                }
            }
        }

        // ========== BEFORE_DELETE ==========
        if (type == TYPE_BEFORE_DELETE) {
            int nbPart = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                planningID);
            if (nbPart > 0)
                return "Impossible de supprimer : " + nbPart + " participant(s) sont assignes a ce planning.";

            // Supprimer automatiquement les lignes
            DB.executeUpdateEx(
                "DELETE FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=" + planningID,
                null);
        }

        // ========== AFTER_NEW : auto-générer les lignes ==========
        if (type == TYPE_AFTER_NEW) {
            if (sessionID <= 0) return null;

            int catalogueID = DB.getSQLValueEx(null,
                "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                sessionID);
            if (catalogueID <= 0) return null;

            int clientId = Env.getAD_Client_ID(Env.getCtx());
            int orgId = Env.getAD_Org_ID(Env.getCtx());
            int userId = Env.getAD_User_ID(Env.getCtx());

            String sql = "SELECT HR_FormationProgramme_ID, HR_FormationModule_ID, Nombre_Partie"
                + " FROM HR_FormationProgramme"
                + " WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'"
                + " ORDER BY SeqNo";

            PreparedStatement pstmt = null;
            ResultSet rs = null;
            try {
                pstmt = DB.prepareStatement(sql, null);
                pstmt.setInt(1, catalogueID);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    int programmeID = rs.getInt("HR_FormationProgramme_ID");
                    int moduleID = rs.getInt("HR_FormationModule_ID");
                    int nbParties = rs.getInt("Nombre_Partie");
                    if (nbParties <= 0) nbParties = 1;

                    for (int partie = 1; partie <= nbParties; partie++) {
                        int ligneID = DB.getNextID(clientId, "HR_FormationPlanningLigne", null);
                        DB.executeUpdateEx(
                            "INSERT INTO HR_FormationPlanningLigne ("
                            + "HR_FormationPlanningLigne_ID, HR_FormationPlanningLigne_UU,"
                            + " AD_Client_ID, AD_Org_ID, Created, CreatedBy, Updated, UpdatedBy, IsActive,"
                            + " HR_FormationPlanning_ID, HR_FormationProgramme_ID, HR_FormationModule_ID,"
                            + " Numero_Partie, IsOk"
                            + ") VALUES ("
                            + ligneID + ", uuid_generate_v4()::varchar,"
                            + " " + clientId + ", " + orgId
                            + ", now(), " + userId + ", now(), " + userId + ", 'Y',"
                            + " " + planningID + ", " + programmeID + ", " + moduleID + ","
                            + " " + partie + ", 'N')",
                            null);
                    }
                }
            } finally {
                DB.close(rs, pstmt);
            }
        }

        return null;
    }

    @Override
    public String docValidate(PO po, int timing) { return null; }
}
