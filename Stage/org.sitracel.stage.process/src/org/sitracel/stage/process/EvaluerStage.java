package org.sitracel.stage.process;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.logging.Level;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class EvaluerStage extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int stageId = getRecord_ID();

        // Vérifier que le stage n'est pas déjà validé
        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if ("Y".equals(isValidee)) {
            return "Le stage est déjà validé.";
        }

        // Vérifier que tous les objectifs sont définis (IsOk = Y)
        int totalObjectifs = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y'", stageId);
        if (totalObjectifs == 0) {
            return "Aucun objectif dans le suivi. Générez d'abord le planning.";
        }

        int nonDefinis = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsOk = 'N'", stageId);
        if (nonDefinis > 0) {
            return nonDefinis + " objectif(s) ne sont pas encore défini(s) (IsOk). Complétez le planning d'abord.";
        }

        // Vérifier que tous les objectifs sont évalués
        int nonEvalues = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsEvalue = 'N'", stageId);
        if (nonEvalues > 0) {
            return nonEvalues + " objectif(s) ne sont pas encore évalué(s). Évaluez tous les objectifs d'abord.";
        }

        // Calculer le score pondéré
        BigDecimal scoreTotalPondere = BigDecimal.ZERO;
        BigDecimal scoreMaxPondere = BigDecimal.ZERO;
        int totalPonderation = 0;

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
                if (rs.wasNull()) ponderation = 1;

                if (score != null && scoreMax != null) {
                    scoreTotalPondere = scoreTotalPondere.add(score.multiply(new BigDecimal(ponderation)));
                    scoreMaxPondere = scoreMaxPondere.add(scoreMax.multiply(new BigDecimal(ponderation)));
                    totalPonderation += ponderation;
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, sql, e);
            throw e;
        } finally {
            DB.close(rs, pstmt);
        }

        // Calculer la moyenne pondérée
        BigDecimal scoreTotal = BigDecimal.ZERO;
        BigDecimal scoreMaxTotal = BigDecimal.ZERO;
        if (totalPonderation > 0) {
            scoreTotal = scoreTotalPondere.divide(new BigDecimal(totalPonderation), 2, RoundingMode.HALF_UP);
            scoreMaxTotal = scoreMaxPondere.divide(new BigDecimal(totalPonderation), 2, RoundingMode.HALF_UP);
        }

        // Mettre à jour la fiche stage
        DB.executeUpdateEx(
            "UPDATE HR_Stage SET Score_Total = " + scoreTotal +
            ", ScoreMax_Total = " + scoreMaxTotal +
            " WHERE HR_Stage_ID = " + stageId,
            get_TrxName());

        return "Évaluation calculée : " + scoreTotal + " / " + scoreMaxTotal +
               " (sur " + totalObjectifs + " objectifs, pondération totale : " + totalPonderation + ")";
    }
}
