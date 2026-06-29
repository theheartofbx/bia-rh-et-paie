package org.sitracel.conge.callout.conge;

import java.sql.Timestamp;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.bean.BeanConge;
import org.sitracel.conge.HRCongeService;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class CalloutTypeCongeEmploye implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        Integer idConge = (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_Emission_Conge_Employe_ID);
        if (idConge == null) return null;

        MHRTypeConge typeConge = new MHRTypeConge(Env.getCtx(), idConge, null);
        if (typeConge == null) return null;

        mTab.setValue(MHRHoliday.COLUMNNAME_Emission_Conge_ID,         typeConge.getHR_Type_Conge_ID());
        mTab.setValue(MHRHoliday.COLUMNNAME_IsCongeAnnuel,             typeConge.isCongeAnnuel());
        mTab.setValue(MHRHoliday.COLUMNNAME_IsDefinir_Periode_Conge,   typeConge.isDefinir_Periode_Conge());

        if (typeConge.isCongeAnnuel()) {
            Integer idEmploye = (Integer) mTab.getValue(MHRHoliday.COLUMNNAME_C_BPartner_ID);
            if (idEmploye == null) return null;

            BeanConge bic = HRCongeService.getInfoConge(
                idEmploye, typeConge.getHR_Type_Conge_ID(),
                new Timestamp(System.currentTimeMillis()), null);

            if (bic != null) {
                mTab.setValue(MHRHoliday.COLUMNNAME_Enfant_Conge,              bic.getNombreEnfantPetit());
                mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Total,         bic.getNombreJourCongeTotal());
                mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_Deja_Utilise,  bic.getNombreJourCongeUtilise());
                mTab.setValue(MHRHoliday.COLUMNNAME_Annee_Anciennete,          bic.getAnneeAnciennete());
                mTab.setValue(MHRHoliday.COLUMNNAME_Jours_Conge_A_Compenser,   bic.getDetteConge());
                mTab.setValue(MHRHoliday.COLUMNNAME_Date_Dernier_Conge,        bic.getDateFinDernierConge());
            }
        }

        return null;
    }
}
