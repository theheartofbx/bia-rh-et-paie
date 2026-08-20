package org.sitracel.evaluation.process;

import java.sql.Timestamp;
import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Soumettre l'évaluation par l'employé
 *
 * Fige les scores et commentaires de l'employé.
 * Met IsSoumiseEmploye = Y, Date_Soumission_Employe = now()
 */
public class SoumettreEmploye extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();

        // --- Vérifications ---
        String isGeneree = DB.getSQLValueString(get_TrxName(),
            "SELECT IsGeneree FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isGeneree)) {
            return "L'évaluation n'a pas encore été générée.";
        }

        String isSoumise = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseEmploye FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isSoumise)) {
            return "L'évaluation a déjà été soumise par l'employé.";
        }

        // --- Vérifier qu'au moins 1 score employé est renseigné ---
        int nbScores = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalLigne"
            + " WHERE HR_Eval_ID = ? AND IsActive = 'Y'"
            + " AND Score_Employe IS NOT NULL AND Score_Employe != 0",
            evalId);
        if (nbScores == 0) {
            return "Aucun score n'a été saisi. Renseignez au moins un score avant de soumettre.";
        }

        // --- Soumettre ---
        Timestamp now = new Timestamp(System.currentTimeMillis());
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseEmploye = 'Y',"
            + " Date_Soumission_Employe = ?, IsRejetee = 'N'"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ now, evalId }, get_TrxName());

        return "Évaluation soumise par l'employé.";
    }
}
