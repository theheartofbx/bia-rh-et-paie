package org.sitracel.evaluation.callout.grille;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;
import org.compiere.util.Env;

/**
 * Callout générique SeqNo
 *
 * À la création d'un nouvel enregistrement, calcule automatiquement
 * le prochain SeqNo disponible (max + 10) dans le parent.
 *
 * Fonctionne pour HR_EvalGrilleLigne et HR_EvalLigne.
 */
public class CalloutSeqNoAuto implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
            GridField mField, Object value, Object oldValue) {

        // Ne rien faire si on est en modification (pas en création)
        Object recordId = mTab.getValue(mTab.getKeyColumnName());
        if (recordId != null && ((Number) recordId).intValue() > 0) return "";

        String tableName = mTab.getTableName();
        String parentColumn = null;
        Object parentId = null;

        if ("HR_EvalGrilleLigne".equals(tableName)) {
            parentColumn = "HR_EvalGrille_ID";
            parentId = mTab.getValue("HR_EvalGrille_ID");
        } else if ("HR_EvalLigne".equals(tableName)) {
            parentColumn = "HR_Eval_ID";
            parentId = mTab.getValue("HR_Eval_ID");
        }

        if (parentColumn == null || parentId == null) return "";

        int parentIdInt = ((Number) parentId).intValue();

        int maxSeqNo = DB.getSQLValueEx(null,
            "SELECT COALESCE(MAX(SeqNo), 0) FROM " + tableName
            + " WHERE " + parentColumn + " = ? AND IsActive = 'Y'",
            parentIdInt);

        mTab.setValue("SeqNo", maxSeqNo + 10);

        return "";
    }
}
