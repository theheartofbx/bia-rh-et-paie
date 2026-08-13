package org.sitracel.stage.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

public class ValiderStage extends SvrProcess {

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

        // Vérifier que le score a été calculé
        int scoreTotal = DB.getSQLValue(get_TrxName(),
            "SELECT COALESCE(Score_Total, -1) FROM HR_Stage WHERE HR_Stage_ID = ?", stageId);
        if (scoreTotal < 0) {
            return "Le score n'a pas encore été calculé. Lancez d'abord l'évaluation.";
        }

        // Vérifier que tous les objectifs sont évalués
        int nonEvalues = DB.getSQLValue(get_TrxName(),
            "SELECT COUNT(*) FROM HR_StageSuivi WHERE HR_Stage_ID = ? AND IsActive = 'Y' AND IsEvalue = 'N'", stageId);
        if (nonEvalues > 0) {
            return nonEvalues + " objectif(s) non évalué(s). Impossible de valider.";
        }

        // Valider le stage
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DB.executeUpdateEx(
            "UPDATE HR_Stage SET IsValidee = 'Y', Date_Validation = '" + now +
            "' WHERE HR_Stage_ID = " + stageId,
            get_TrxName());

        return "Stage validé avec succès.";
    }
}
