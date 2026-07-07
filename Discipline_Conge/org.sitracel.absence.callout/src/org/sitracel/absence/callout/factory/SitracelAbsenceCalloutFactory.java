package org.sitracel.absence.callout.factory;
import java.util.ArrayList;
import java.util.List;
import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.absence.callout.CalloutDateAbsenceConforme;
import org.sitracel.absence.model.MHRAbsence;
public class SitracelAbsenceCalloutFactory implements IColumnCalloutFactory{
    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();

        if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name) && (columnName.equalsIgnoreCase(MHRAbsence.COLUMNNAME_Date_Absence))) {
            list.add(new CalloutDateAbsenceConforme());
        }

        return list !=null ? list.toArray(new IColumnCallout[0]) : new IColumnCallout[0];
    }
}
