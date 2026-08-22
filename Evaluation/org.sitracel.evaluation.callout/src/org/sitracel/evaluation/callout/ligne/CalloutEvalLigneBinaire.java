package org.sitracel.evaluation.callout.ligne;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;

/**
 * Callout HR_EvalLigne.IsOk
 *
 * Pour les objectifs binaires :
 * IsOk = Y → Score_Employe = ScoreMax
 * IsOk = N → Score_Employe = ScoreMin (ou 0)
 */
public class CalloutEvalLigneBinaire implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
            GridField mField, Object value, Object oldValue) {

        if (value == null) return "";

        boolean isOk = "Y".equals(value.toString()) || Boolean.TRUE.equals(value);

        BigDecimal scoreMax = (BigDecimal) mTab.getValue("ScoreMax");
        BigDecimal scoreMin = (BigDecimal) mTab.getValue("ScoreMin");

        if (scoreMax == null) scoreMax = BigDecimal.TEN;
        if (scoreMin == null) scoreMin = BigDecimal.ZERO;

        BigDecimal score = isOk ? scoreMax : scoreMin;
        mTab.setValue("Score_Employe", score);

        return "";
    }
}
