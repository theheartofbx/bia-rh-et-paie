package org.sitracel.discipline.callout.sanction;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.discipline.callout.sanction.controller.CalloutControllerDiscipline;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.model.MHRAmpliation;

/**
 * Corrigé : utilisait Timestamp.getYear() (obsolète depuis Java 1.1),
 * qui retourne l'année MOINS 1900, pas l'année réelle — une sanction
 * émise en 2026 aurait produit "126" dans la chaîne d'ampliation.
 */
public class CalloutAmpliationSanction implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        Timestamp dateEmission = (Timestamp) mTab.getValue(MHRPunishment.COLUMNNAME_Date_Emission);
        String ampliation = (String) mTab.getValue(MHRPunishment.COLUMNNAME_Initial);
        Integer idAmpliation = (Integer) mTab.getValue(MHRPunishment.COLUMNNAME_HR_Ampliation_ID);
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
                ampliation = CalloutControllerDiscipline.getAmpliation(ampliation, initial.getAbreviation(), annee);
                mTab.setValue(MHRPunishment.COLUMNNAME_Initial, ampliation);
            }
        }
        return null;
    }
}
