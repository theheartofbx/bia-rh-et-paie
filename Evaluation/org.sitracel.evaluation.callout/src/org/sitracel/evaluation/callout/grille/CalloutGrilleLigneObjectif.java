package org.sitracel.evaluation.callout.grille;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.DB;

/**
 * Callout HR_EvalGrilleLigne.HR_EvalObjectif_ID
 *
 * Quand on sélectionne un objectif du catalogue,
 * auto-remplir les propriétés par défaut :
 * IsProgressif, IsBinaire, IsSubjectif, IsPourcentage, ScoreMax
 */
public class CalloutGrilleLigneObjectif implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
            GridField mField, Object value, Object oldValue) {

        if (value == null) return "";

        int objectifId = ((Number) value).intValue();
        if (objectifId <= 0) return "";

        String trx = null;

        // --- IsProgressif ---
        String isProgressif = DB.getSQLValueString(trx,
            "SELECT IsProgressif FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?",
            objectifId);
        if (isProgressif != null) mTab.setValue("IsProgressif", "Y".equals(isProgressif));

        // --- IsBinaire ---
        String isBinaire = DB.getSQLValueString(trx,
            "SELECT IsBinaire FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?",
            objectifId);
        if (isBinaire != null) mTab.setValue("IsBinaire", "Y".equals(isBinaire));

        // --- IsSubjectif ---
        String isSubjectif = DB.getSQLValueString(trx,
            "SELECT IsSubjectif FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?",
            objectifId);
        if (isSubjectif != null) mTab.setValue("IsSubjectif", "Y".equals(isSubjectif));

        // --- IsPourcentage ---
        String isPourcentage = DB.getSQLValueString(trx,
            "SELECT IsPourcentage FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?",
            objectifId);
        if (isPourcentage != null) mTab.setValue("IsPourcentage", "Y".equals(isPourcentage));

        // --- ScoreMax_Defaut → ScoreMax ---
        BigDecimal scoreMaxDefaut = DB.getSQLValueBD(trx,
            "SELECT ScoreMax_Defaut FROM HR_EvalObjectif WHERE HR_EvalObjectif_ID = ?",
            objectifId);
        if (scoreMaxDefaut != null) mTab.setValue("ScoreMax", scoreMaxDefaut);

        return "";
    }
}
