package org.sitracel.paie.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRGestionPaieEmploye;

public class CalloutAjouterElementPaie implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {

        Object gestionIdObj = mTab.getValue(MHRElementBasePaieEmploye.COLUMNNAME_HR_GestionPaieEmploye_ID);

        // Vérifier que le champ existe
        if (gestionIdObj == null) {
            return null;
        }

        try {
            // Récupération de l’ID
            int gestionId = (gestionIdObj instanceof Integer)
                    ? (Integer) gestionIdObj
                    : Integer.parseInt(gestionIdObj.toString());

            // Charger l’objet métier
            MHRGestionPaieEmploye gestionPaieEmploye = new MHRGestionPaieEmploye(ctx, gestionId, null);

            String columnName = gestionPaieEmploye.getName(); // ⚠️ à vérifier si c’est bien un "Nom de colonne"
            if (columnName != null && mTab.getField(columnName) != null) {

                Object val = mTab.getValue(columnName);
                BigDecimal montant = (val instanceof BigDecimal) ? (BigDecimal) val : null;

                // Si montant nul ou <= 0 → forcer à 1
                if (montant == null || montant.compareTo(BigDecimal.ZERO) <= 0) {
                    mTab.setValue(columnName, BigDecimal.ONE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // pour debug
            return "Erreur lors de l’ajout de l’élément de paie : " + e.getMessage();
        }

        return null;
    }
}
