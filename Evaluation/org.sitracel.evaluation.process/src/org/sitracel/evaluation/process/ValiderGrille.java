package org.sitracel.evaluation.process;

import org.compiere.process.SvrProcess;
import org.compiere.util.DB;

/**
 * Processus : Valider une grille d'évaluation
 *
 * Vérifie :
 *   1. Au moins 1 objectif dans la grille
 *   2. Au moins 1 formule principale valide
 *   3. Tous les acronymes des formules existent dans les lignes
 *
 * Si OK → IsValidee = Y (grille et enfants en lecture seule)
 */
public class ValiderGrille extends SvrProcess {

    @Override
    protected void prepare() {}

    @Override
    protected String doIt() throws Exception {
        int grilleId = getRecord_ID();

        // Vérifier que la grille n'est pas déjà validée
        String isValidee = DB.getSQLValueString(get_TrxName(),
            "SELECT IsValidee FROM HR_EvalGrille WHERE HR_EvalGrille_ID = ?", grilleId);
        if ("Y".equals(isValidee)) {
            return "La grille est déjà validée.";
        }

        // Vérifier qu'il y a au moins 1 objectif
        int nbLignes = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalGrilleLigne"
            + " WHERE HR_EvalGrille_ID = ? AND IsActive = 'Y'",
            grilleId);
        if (nbLignes == 0) {
            return "La grille n'a aucun objectif. Ajoutez au moins un objectif avant de valider.";
        }

        // Vérifier qu'il y a au moins 1 formule principale valide
        int nbFormulePrincipale = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalGrilleFormule"
            + " WHERE HR_EvalGrille_ID = ? AND IsPrincipale = 'Y'"
            + " AND IsValide = 'Y' AND IsActive = 'Y'",
            grilleId);
        if (nbFormulePrincipale == 0) {
            return "Aucune formule principale valide. "
                + "Créez et validez au moins une formule marquée comme principale.";
        }

        // Vérifier qu'il n'y a pas de formule invalide
        int nbFormuleInvalide = DB.getSQLValueEx(get_TrxName(),
            "SELECT COUNT(*) FROM HR_EvalGrilleFormule"
            + " WHERE HR_EvalGrille_ID = ? AND IsValide = 'N' AND IsActive = 'Y'",
            grilleId);
        if (nbFormuleInvalide > 0) {
            return nbFormuleInvalide + " formule(s) invalide(s) dans la grille. "
                + "Corrigez-les ou désactivez-les avant de valider.";
        }

        // Valider la grille
        DB.executeUpdateEx(
            "UPDATE HR_EvalGrille SET IsValidee = 'Y'"
            + " WHERE HR_EvalGrille_ID = ?",
            new Object[]{ grilleId }, get_TrxName());

        return "Grille validée avec succès (" + nbLignes + " objectif(s), "
            + nbFormulePrincipale + " formule(s) principale(s)).";
    }
}
