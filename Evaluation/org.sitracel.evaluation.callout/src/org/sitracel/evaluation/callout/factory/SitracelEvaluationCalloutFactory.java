package org.sitracel.evaluation.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;

public class SitracelEvaluationCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();
        // Les callouts seront ajoutés ici
        return list.toArray(new IColumnCallout[0]);
    }
}
