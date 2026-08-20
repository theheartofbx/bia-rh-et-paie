package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Renvoyer l'évaluation vers le N+1
 *
 * Le N+2 renvoie au N+1 pour correction.
 * Remet IsSoumiseN1 = N, le N+1 peut modifier ses scores.
 */
public class RenvoyerVersN1 extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();

        // --- Vérifications ---
        String isSoumiseN1 = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseN1 FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseN1)) {
            return "Le N+1 n'a pas encore soumis. Rien à renvoyer.";
        }

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est déjà validée. Impossible de renvoyer.";
        }

        // --- Renvoyer ---
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseN1 = 'N',"
            + " Date_Soumission_N1 = NULL"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ evalId }, get_TrxName());

        return "Évaluation renvoyée au N+1 pour correction.";
    }
}
