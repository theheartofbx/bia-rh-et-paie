package org.sitracel.evaluation.callout.ligne;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

/**
 * Callout HR_EvalLigne.HR_EvalAppreciation_ID
 *
 * Quand l'évaluateur choisit une appréciation (Bon, Excellent...),
 * auto-remplir Score_Employe avec : Pourcentage / 100 × ScoreMax
 *
 * Le score est mis dans Score_Employe si IsSoumiseEmploye = N,
 * sinon dans Score_N1.
 */
public class CalloutEvalLigneAppreciation implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
            GridField mField, Object value, Object oldValue) {

        if (value == null) return "";

        int appreciationId = ((Number) value).intValue();
        if (appreciationId <= 0) return "";

        // --- Récupérer le pourcentage de l'appréciation ---
        BigDecimal pourcentage = DB.getSQLValueBD(null,
            "SELECT Pourcentage FROM HR_EvalAppreciation"
            + " WHERE HR_EvalAppreciation_ID = ?", appreciationId);
        if (pourcentage == null || pourcentage.compareTo(BigDecimal.ZERO) == 0) return "";

        // --- Récupérer le ScoreMax de la ligne ---
        BigDecimal scoreMax = (BigDecimal) mTab.getValue("ScoreMax");
        if (scoreMax == null || scoreMax.compareTo(BigDecimal.ZERO) == 0) return "";

        // --- Calculer le score ---
        BigDecimal score = pourcentage
            .multiply(scoreMax)
            .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);

        // --- Déterminer dans quel champ mettre le score ---
        // Si l'employé n'a pas encore soumis → Score_Employe
        // Sinon → Score_N1
        Object isSoumiseObj = mTab.getValue("IsSoumiseEmploye");
        boolean isSoumise = isSoumiseObj != null && "Y".equals(isSoumiseObj.toString());

        if (!isSoumise) {
            mTab.setValue("Score_Employe", score);
        } else {
            mTab.setValue("Score_N1", score);
        }

        return "";
    }
}
