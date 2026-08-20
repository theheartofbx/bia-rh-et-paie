package org.sitracel.evaluation.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.evaluation.callout.grille.CalloutGrilleLigneObjectif;
import org.sitracel.evaluation.callout.ligne.CalloutEvalLigneAppreciation;

public class SitracelEvaluationCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();

        // Callout sur HR_EvalGrilleLigne → sélection d'un objectif
        if ("HR_EvalGrilleLigne".equals(tableName)
                && "HR_EvalObjectif_ID".equals(columnName)) {
            list.add(new CalloutGrilleLigneObjectif());
        }

        // Callout sur HR_EvalLigne → sélection d'une appréciation
        if ("HR_EvalLigne".equals(tableName)
                && "HR_EvalAppreciation_ID".equals(columnName)) {
            list.add(new CalloutEvalLigneAppreciation());
        }

        return list.toArray(new IColumnCallout[0]);
    }
}
