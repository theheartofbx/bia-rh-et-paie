package org.sitracel.stage.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.stage.callout.programmeligne.CalloutStageProgrammeLigneObjectif;

public class SitracelStageCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();

        if (tableName.equalsIgnoreCase("HR_StageProgrammeLigne") && columnName.equalsIgnoreCase("HR_StageObjectif_ID")) {
            list.add(new CalloutStageProgrammeLigneObjectif());
        }

        return list.toArray(new IColumnCallout[0]);
    }
}
