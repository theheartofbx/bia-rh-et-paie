package org.sitracel.paie.callout;

import java.util.ArrayList;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.PO;
import org.compiere.model.Query;
import org.compiere.util.CLogger;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.MHRCategorieResponsabilite;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;

public class CalloutChargerDernierElement implements IColumnCallout {

    private static final CLogger log = CLogger.getCLogger(CalloutChargerDernierElement.class);

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

        // ✅ Exécuter uniquement si la case est cochée
        if (!(value instanceof Boolean) || !((Boolean) value)) {
            return "";
        }

        Integer bpartnerId = (Integer) mTab.getValue(MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID);
        if (bpartnerId == null) {
            return "";
        }

        // Charger le dernier enregistrement existant pour ce partenaire
        PO last = new Query(ctx, MHRElementBasePaieEmploye.Table_Name,
                MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=?",
                null)
                .setParameters(bpartnerId)
                .setOrderBy("Date_Debut DESC")
                .first();

        if (last == null) {
            return "";
        }

        // Copier les valeurs des colonnes de gestion
        ArrayList<MHRGestionPaieEmploye> listeGestion =
                GeneralSqlController.getAllGestionPaieEmploye(null);
        Object valeur = null;
        
        for (MHRGestionPaieEmploye gestion : listeGestion) {
            try {
                valeur = last.get_Value(gestion.getName());
                if (valeur != null) {
                    mTab.setValue(gestion.getName(), valeur);
                }
            } catch (Exception e) {
                log.warning("⚠️ Erreur champ : " + gestion.getName() + " - " + e.getMessage());
            }
        }
        
        valeur = last.get_Value(MHRElementBasePaieEmploye.COLUMNNAME_HR_CategorieProfessionnelle_ID);
        
        if(valeur!=null) {
        	mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_CategorieProfessionnelle_ID, valeur);
        }
        
        valeur = last.get_Value(MHRElementBasePaieEmploye.COLUMNNAME_HR_Echelon_ID);
        
        if(valeur!=null) {
        	mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_Echelon_ID, valeur);
        }

        valeur = last.get_Value(MHRElementBasePaieEmploye.COLUMNNAME_HR_Job_ID);
        
        if(valeur!=null) {
        	mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_Job_ID, valeur);
        }

        valeur = last.get_Value(MHRElementBasePaieEmploye.COLUMNNAME_HR_Taux_Salarial_ID);
        
        if(valeur!=null) {
        	mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_Taux_Salarial_ID, valeur);
        }

        valeur = last.get_Value(MHRElementBasePaieEmploye.COLUMNNAME_HR_TypeContrat_ID);
        
        if(valeur!=null) {
        	mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_TypeContrat_ID, valeur);
        }

        return "";
    }
}
