package org.sitracel.evaluation.callout.grille;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

/**
 * Callout HR_EvalGrilleFormule.IsPrincipale
 *
 * Quand on coche IsPrincipale = Y, décoche automatiquement
 * toutes les autres formules de la même grille.
 */
public class CalloutGrilleFormulePrincipale implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
            GridField mField, Object value, Object oldValue) {

        if (value == null) return "";

        boolean isPrincipale = "Y".equals(value.toString()) || Boolean.TRUE.equals(value);
        if (!isPrincipale) return "";

        Object grilleIdObj = mTab.getValue("HR_EvalGrille_ID");
        if (grilleIdObj == null) return "";
        int grilleId = ((Number) grilleIdObj).intValue();

        Object formuleIdObj = mTab.getValue("HR_EvalGrilleFormule_ID");
        int formuleId = (formuleIdObj != null) ? ((Number) formuleIdObj).intValue() : 0;

        // Décocher toutes les autres formules principales de la même grille
        DB.executeUpdateEx(
            "UPDATE HR_EvalGrilleFormule SET IsPrincipale = 'N'"
            + " WHERE HR_EvalGrille_ID = ? AND IsPrincipale = 'Y'"
            + " AND HR_EvalGrilleFormule_ID != ?",
            new Object[]{ grilleId, formuleId }, null);

        return "";
    }
}
