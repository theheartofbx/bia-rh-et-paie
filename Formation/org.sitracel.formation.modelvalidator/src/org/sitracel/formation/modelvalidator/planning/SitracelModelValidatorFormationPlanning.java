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
                        return "Impossible : " + nbPart + " participant(s) sont assignés à ce planning.";
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
        }

        // ========== BEFORE_DELETE ==========
        if (type == TYPE_BEFORE_DELETE) {
            // Bloquer si participants assignés
            int nbPart = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationParticipant WHERE HR_FormationPlanning_ID=? AND IsActive='Y'",
                planningID);
            if (nbPart > 0)
                return "Impossible de supprimer : " + nbPart + " participant(s) sont assignés à ce planning.";

            // Supprimer automatiquement les lignes
            DB.executeUpdateEx(
                "DELETE FROM HR_FormationPlanningLigne WHERE HR_FormationPlanning_ID=" + planningID,
                null);
        }

        // ========== AFTER_NEW : auto-générer les lignes ==========
        if (type == TYPE_AFTER_NEW) {
            int sessionID = po.get_ValueAsInt("HR_FormationSession_ID");
            if (sessionID <= 0) return null;

            int catalogueID = DB.getSQLValueEx(null,
                "SELECT HR_FormationCatalogue_ID FROM HR_FormationSession WHERE HR_FormationSession_ID=?",
                sessionID);
            if (catalogueID <= 0) return null;

            int nbProgrammes = DB.getSQLValueEx(null,
                "SELECT COUNT(*) FROM HR_FormationProgramme WHERE HR_FormationCatalogue_ID=? AND IsActive='Y'",
                catalogueID);
            if (nbProgrammes == 0) return null;

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
