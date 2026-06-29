package org.sitracel.conge.callout.conge;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.bean.BeanPeriodeConge;
import org.sitracel.conge.callout.conge.service.CongeCalloutService;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class CalloutCongeMaternite implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        Timestamp dateEcheance = (Timestamp) mTab.getValue(MHRHoliday.COLUMNNAME_Date_Probable_Accouchement);
        Integer idConge        = (Integer)   mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID);

        if (idConge == null || dateEcheance == null) return null;

        // Vérifier que la date est au moins 4 mois dans le futur
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar cal  = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.MONTH, 4);
        Timestamp dateMin = new Timestamp(cal.getTime().getTime());

        if (dateEcheance.before(dateMin)) {
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Probable_Accouchement, null);
            mTab.setValue(MHRHoliday.COLUMNNAME_IsMessageAlerteDisplayed, "Y");
            mTab.setValue(MHRHoliday.COLUMNNAME_Message_Alerte,
                "La date d'échéance doit être enregistrée 4 mois au moins avant !");
            return null;
        }

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
        BeanPeriodeConge resultat = CongeCalloutService.getPeriodeCongeMaternite(
            dateEcheance, typeConge.getHR_Type_Conge_ID());

        if (resultat != null
                && resultat.getDateDebutConge() != null
                && resultat.getDateFinConge()   != null) {
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Souhaitee,  resultat.getDateDebutConge());
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Ajustee,    resultat.getDateDebutConge());
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Debut_Effective,  resultat.getDateDebutConge());
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Souhaitee,    resultat.getDateFinConge());
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Ajustee,      resultat.getDateFinConge());
            mTab.setValue(MHRHoliday.COLUMNNAME_Date_Fin_Effective,    resultat.getDateFinConge());
        }

        return null;
    }
}
