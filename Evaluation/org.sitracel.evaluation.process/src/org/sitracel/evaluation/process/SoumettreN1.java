package org.sitracel.evaluation.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Soumettre l'évaluation par le N+1
 *
 * Fige les scores et commentaires du N+1.
 * Met IsSoumiseN1 = Y, Date_Soumission_N1 = now()
 */
public class SoumettreN1 extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();

        // --- Vérifications ---
        String isSoumiseEmploye = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseEmploye FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseEmploye)) {
            return "L'employé n'a pas encore soumis son évaluation.";
        }

        String isSoumiseN1 = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseN1 FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isSoumiseN1)) {
            return "L'évaluation a déjà été soumise par le N+1.";
        }

        // --- Vérifier qu'au moins 1 score N+1 est renseigné ---
        int nbScores = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'"
            + " AND Score_N1 IS NOT NULL AND Score_N1 != 0",
            evalId);
        if (nbScores == 0) {
            return "Aucun score N+1 n'a été saisi. Renseignez au moins un score avant de soumettre.";
        }

        // --- Soumettre ---
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseN1 = 'Y',"
            + " Date_Soumission_N1 = ?, IsRejetee = 'N'"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ now, evalId }, get_TrxName());

        return "Évaluation soumise par le N+1.";
    }
}
