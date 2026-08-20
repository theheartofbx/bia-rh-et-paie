package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Annuler la validation d'une grille d'évaluation
 *
 * Vérifie :
 *   1. Aucune évaluation avec IsGeneree = Y n'utilise cette grille
 *
 * Si OK → IsValidee = N (grille redevient modifiable)
 */
public class AnnulerValidationGrille extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int grilleId = getRecord_ID();

        // Vérifier que la grille est bien validée
        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
        if (!"Y".equals(isValidee)) {
            return "La grille n'est pas validée.";
        }

        // Vérifier qu'aucune évaluation générée n'utilise cette grille
        int nbEvalGenerees = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_Eval"
            + " WHERE HR_EvalGrille_ID = ? AND IsGeneree = 'Y' AND IsActive = 'Y'",
            grilleId);
        if (nbEvalGenerees > 0) {
            return "Impossible d'annuler : " + nbEvalGenerees
                + " évaluation(s) générée(s) utilisent cette grille.";
        }

        // Annuler la validation
        DB.executeUpdateEx(
            "UPDATE HR_EvalGrille SET IsValidee = 'N'"
            + " WHERE HR_EvalGrille_ID = ?",
            new Object[]{ grilleId }, get_TrxName());

        return "Validation annulée. La grille est de nouveau modifiable.";
    }
}
