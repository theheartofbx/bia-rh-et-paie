package org.sitracel.paie.callout;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.Query;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

public class CalloutDateElementPaie implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue) {
        Integer bpartnerId = (Integer) mTab.getValue(MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID);
        if (bpartnerId == null) {
            return "";
        }

        Timestamp dateDebut = (Timestamp) mTab.getValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut);
        Timestamp dateFin   = (Timestamp) mTab.getValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Vérifier si une ligne existe déjà à la même date de début
        if (dateDebut != null) {
            boolean existeDebut = new Query(ctx, MHRElementBasePaieEmploye.Table_Name,
                    MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=? AND " 
                    + MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut + "=?",
                    null)
                    .setParameters(bpartnerId, dateDebut)
                    .match();

            if (existeDebut) {
                mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut, null);
                return "⚠️ Un élément de paie existe déjà pour cet employé à la date du " + sdf.format(dateDebut);
            }
        }

        // Vérifier si une ligne existe déjà à la même date de fin
        if (dateFin != null) {
            boolean existeFin = new Query(ctx, MHRElementBasePaieEmploye.Table_Name,
                    MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=? AND " 
                    + MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin + "=?",
                    null)
                    .setParameters(bpartnerId, dateFin)
                    .match();

            if (existeFin) {
                mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin, null);
                return "⚠️ Un élément de paie existe déjà pour cet employé à la date du " + sdf.format(dateFin);
            }
        }

        // Vérifier chevauchement de périodes (date_debut/date_fin)
        if (dateDebut != null && dateFin != null) {
            boolean chevauche = new Query(ctx, MHRElementBasePaieEmploye.Table_Name,
                    MHRElementBasePaieEmploye.COLUMNNAME_C_BPartner_ID + "=? AND (" 
                    + MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut + ", "
                    + MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin + ") OVERLAPS (?,?)",
                    null)
                    .setParameters(bpartnerId, dateDebut, dateFin)
                    .match();

            if (chevauche) {
                mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut, null);
                mTab.setValue(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin, null);
                return "⚠️ Un élément de paie existe déjà pour cet employé avec une période chevauchant "
                        + "les dates du " + sdf.format(dateDebut) + " au " + sdf.format(dateFin);
            }
        }

        return null;
    }
}
