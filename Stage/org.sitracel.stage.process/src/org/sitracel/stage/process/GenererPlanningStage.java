package org.sitracel.stage.process;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;
import org.sitracel.stage.model.MHR_StageSuivi;

public class GenererPlanningStage extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int stageId = getRecord_ID();

        // Vérifier que le stage n'est pas validé
        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isValidee)) {
            return "Le stage est déjà validé, impossible de regénérer le planning.";
        }

        // Vérifier qu'un programme est assigné
        int programmeId = DB.getSQLValue(get_TrxName(),
            "SELECT HR_StageProgramme_ID FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if (programmeId <= 0) {
            return "Aucun programme n'est assigné à ce stage. Veuillez d'abord sélectionner un programme.";
        }

        // Vérifier qu'il n'y a pas déjà des lignes de suivi
        int existant = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y'", stageId);
        if (existant > 0) {
            return "Le planning existe déjà (" + existant + " objectifs). Supprimez les lignes existantes avant de regénérer.";
        }

        // Récupérer l'AD_Org_ID du stage
        int orgId = DB.getSQLValue(get_TrxName(),
            "SELECT AD_Org_ID FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);

        // Récupérer les lignes du programme et créer les lignes de suivi
        int count = 0;
        String sql = "SELECT HR_StageProgrammeLigne_ID, HR_StageObjectif_ID, SeqNo, Ponderation_Defaut, ScoreMax_Defaut "
                   + "FROM HR_StageProgrammeLigne "
                   + "WHERE HR_StageProgramme_ID = ? AND IsActive = 'Y' "
                   + "ORDER BY SeqNo";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, get_TrxName());
            pstmt.setInt(1, programmeId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                MHR_StageSuivi suivi = new MHR_StageSuivi(getCtx(), 0, get_TrxName());
                suivi.setHR_Stage_ID(stageId);
                suivi.setHR_StageProgrammeLigne_ID(rs.getInt("HR_StageProgrammeLigne_ID"));
                suivi.setHR_StageObjectif_ID(rs.getInt("HR_StageObjectif_ID"));
                suivi.set_ValueNoCheck("SeqNo", rs.getInt("SeqNo"));
                int ponderation = rs.getInt("Ponderation_Defaut");
                if (!rs.wasNull()) suivi.setPonderation(ponderation);
                java.math.BigDecimal scoreMax = rs.getBigDecimal("ScoreMax_Defaut");
                if (scoreMax != null) suivi.setScoreMax(scoreMax);
                suivi.setAD_Org_ID(orgId);
                suivi.saveEx(get_TrxName());
                count++;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
            throw e;
        } finally {
            DB.close(rs, pstmt);
        }

        if (count == 0) {
            return "Le programme sélectionné ne contient aucun objectif.";
        }

        return count + " objectif(s) généré(s) dans le suivi.";
    }
}
