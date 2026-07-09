package org.sitracel.discipline.callout.demandeexplication;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.callout.demandeexplication.controller.CalloutControllerDemandeExplication;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.model.MHRAmpliation;

/**
 * Corrigé : même bug que CalloutAmpliationSanction — Timestamp.getYear()
 * (obsolète depuis Java 1.1) retourne l'année MOINS 1900, pas l'année réelle.
 */
public class CalloutAmpliationDemandeExplication implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        Timestamp dateEmission = (Timestamp) mTab.getValue(MHRDemandeExplication.COLUMNNAME_Date_Emission);
        String ampliation = (String) mTab.getValue(MHRDemandeExplication.COLUMNNAME_Initial);
        Integer idAmpliation = (Integer) mTab.getValue(MHRDemandeExplication.COLUMNNAME_HR_Ampliation_ID);
        String annee = "";

        if (idAmpliation != null) {
            if (ampliation == null) {
                ampliation = "";
            }
            MHRAmpliation initial = new MHRAmpliation(Env.getCtx(), idAmpliation, null);
            if (initial != null) {
                if (dateEmission != null) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(dateEmission);
                    annee = String.valueOf(cal.get(Calendar.YEAR));
                }
                ampliation = CalloutControllerDemandeExplication.getAmpliation(ampliation, initial.getAbreviation(), annee);
                mTab.setValue(MHRDemandeExplication.COLUMNNAME_Initial, ampliation);
            }
        }
        return null;
    }
}
