package org.sitracel.conge.callout.conge;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.conge.callout.conge.service.CongeCalloutService;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.model.MHRAmpliation;

public class CalloutAmpliationConge implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        Timestamp dateEmission = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Emission);
        String ampliation      = (String)    mTab.getValue(MHRHoliday.COLUMNNAME_Initial);
        Integer idAmpliation   = (Integer)   mTab.getValue(MHRHoliday.COLUMNNAME_HR_Ampliation_ID);

        if (idAmpliation == null) return null;
        if (ampliation == null) ampliation = "";

        MHRAmpliation initial = new MHRAmpliation(Env.getCtx(), idAmpliation, null);
        if (initial == null) return null;

        String annee = "";
        if (dateEmission != null) {
            annee = String.valueOf(dateEmission.getYear());
        }

        ampliation = CongeCalloutService.getAmpliation(ampliation, initial.getAbreviation(), annee);
        mTab.setValue(MHRHoliday.COLUMNNAME_Initial, ampliation);

        return null;
    }
}
