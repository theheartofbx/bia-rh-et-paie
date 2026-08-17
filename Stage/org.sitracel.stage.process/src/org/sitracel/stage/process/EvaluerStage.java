package org.sitracel.stage.process;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class EvaluerStage extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int stageId = getRecord_ID();

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isValidee)) {
            return "Le stage est déjà validé.";
        }

        int totalObjectifs = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y'", stageId);
        if (totalObjectifs == 0) {
            return "Aucun objectif dans le suivi. Générez d'abord le planning.";
        }

        int nonDefinis = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsOk = 'N'", stageId);
        if (nonDefinis > 0) {
            return nonDefinis + " objectif(s) ne sont pas encore défini(s). Complétez le planning d'abord.";
        }

        int nonEvalues = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsEvalue = 'N'", stageId);
        if (nonEvalues > 0) {
            return nonEvalues + " objectif(s) ne sont pas encore évalué(s). Évaluez tous les objectifs d'abord.";
        }

        // Calcul somme pondérée (même logique que recrutement)
        BigDecimal scoreTotal = BigDecimal.ZERO;
        BigDecimal scoreTotalMax = BigDecimal.ZERO;

        String sql = "SELECT Score, ScoreMax, Ponderation FROM HR_StageSuivi "
                   + "WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsEvalue = 'Y'";

        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = DB.prepareStatement(sql, get_TrxName());
            pstmt.setInt(1, stageId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BigDecimal score = rs.getBigDecimal("Score");
                BigDecimal scoreMax = rs.getBigDecimal("ScoreMax");
                int ponderation = rs.getInt("Ponderation");
                if (rs.wasNull() || ponderation <= 0) ponderation = 1;

                if (score != null) {
                    scoreTotal = scoreTotal.add(score.multiply(new BigDecimal(ponderation)));
                }
                if (scoreMax != null) {
                    scoreTotalMax = scoreTotalMax.add(scoreMax.multiply(new BigDecimal(ponderation)));
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
            throw e;
        } finally {
            DB.close(rs, pstmt);
        }

        DB.executeUpdateEx(
            "UPDATE HR_Stage SET Score_Total = " + scoreTotal +
            ", ScoreMax_Total = " + scoreTotalMax +
            " WHERE HR_Stage_ID = " + stageId,
            get_TrxName());

        return "Évaluation calculée : " + scoreTotal + " / " + scoreTotalMax +
               " (sur " + totalObjectifs + " objectifs)";
    }
}
