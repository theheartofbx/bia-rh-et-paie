package org.sitracel.stage.callout.programmeligne;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

public class CalloutStageProgrammeLigneObjectif implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        if (value == null) return null;

        int objectifId = (Integer) value;
        if (objectifId <= 0) return null;

        BigDecimal scoreMaxDefaut = DB.getSQLValueBD(null,
            "SELECT ScoreMax_Defaut FROM HR_StageObjectif WHERE HR_StageObjectif_ID = ?", objectifId);

        if (scoreMaxDefaut != null) {
            mTab.setValue("ScoreMax_Defaut", scoreMaxDefaut);
        }

        return null;
    }
}
