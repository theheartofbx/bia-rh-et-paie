package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Renvoyer l'évaluation vers l'employé
 *
 * Remet IsSoumiseEmploye = N et IsSoumiseN1 = N.
 * L'employé peut tout ressaisir.
 */
public class RenvoyerVersEmploye extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int evalId = getRecord_ID();

        // --- Vérifications ---
        String isSoumiseEmploye = DB.getSQLValueString(get_TrxName(),
            "SELECT IsSoumiseEmploye FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if (!"Y".equals(isSoumiseEmploye)) {
            return "L'employé n'a pas encore soumis. Rien à renvoyer.";
        }

        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_Eval WHERE HR_Eval_ID = ?", evalId);
        if ("Y".equals(isValidee)) {
            return "L'évaluation est déjà validée. Impossible de renvoyer.";
        }

        // --- Renvoyer ---
        DB.executeUpdateEx(
            "UPDATE HR_Eval SET IsSoumiseEmploye = 'N', IsSoumiseN1 = 'N',"
            + " Date_Soumission_Employe = NULL, Date_Soumission_N1 = NULL"
            + " WHERE HR_Eval_ID = ?",
            new Object[]{ evalId }, get_TrxName());

        return "Évaluation renvoyée à l'employé pour correction.";
    }
}
