package org.sitracel.discipline.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.discipline.callout.demandeexplication.CalloutAmpliationDemandeExplication;
import org.sitracel.discipline.callout.sanction.CalloutAmpliationSanction;
import org.sitracel.discipline.callout.sanction.CalloutDateDebutApplication;
import org.sitracel.discipline.callout.sanction.CalloutDureeSuspension;
import org.sitracel.discipline.callout.sanction.CalloutMajDemandeExplication;
import org.sitracel.discipline.callout.sanction.CalloutTypeSanction;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;

/**
 * Corrigé : les enregistrements de CalloutAmpliationSanction et
 * CalloutAmpliationDemandeExplication étaient dupliqués (2 blocs if
 * identiques chacun) — sans conséquence grave (la logique interne évite
 * les doublons de texte), mais le même callout était inutilement exécuté
 * deux fois à chaque changement.
 */
public class SitracelDisciplineCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();

        if (tableName.equalsIgnoreCase(MHRPunishment.Table_Name) && columnName.equalsIgnoreCase(MHRPunishment.COLUMNNAME_Date_Debut_Application)) {
            list.add(new CalloutDateDebutApplication());
        }
        if (tableName.equalsIgnoreCase(MHRPunishment.Table_Name) && columnName.equalsIgnoreCase(MHRPunishment.COLUMNNAME_HR_Duree_Sanction_ID)) {
            list.add(new CalloutDureeSuspension());
        }
        if (tableName.equalsIgnoreCase(MHRPunishment.Table_Name) && columnName.equalsIgnoreCase(MHRPunishment.COLUMNNAME_Demande_Explication_ID)) {
            list.add(new CalloutMajDemandeExplication());
        }
        if (tableName.equalsIgnoreCase(MHRPunishment.Table_Name) && columnName.equalsIgnoreCase(MHRPunishment.COLUMNNAME_HR_Ampliation_ID)) {
            list.add(new CalloutAmpliationSanction());
        }
        if (tableName.equalsIgnoreCase(MHRPunishment.Table_Name) && columnName.equalsIgnoreCase(MHRPunishment.COLUMNNAME_Emission_Sanction_ID)) {
            list.add(new CalloutTypeSanction());
        }
        if (tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name) && columnName.equalsIgnoreCase(MHRDemandeExplication.COLUMNNAME_HR_Ampliation_ID)) {
            list.add(new CalloutAmpliationDemandeExplication());
        }

        return list.toArray(new IColumnCallout[0]);
    }
}
