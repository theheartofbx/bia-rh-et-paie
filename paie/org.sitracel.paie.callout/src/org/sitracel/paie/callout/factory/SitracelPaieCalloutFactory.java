package org.sitracel.paie.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.paie.callout.CalloutAjouterElementPaie;
import org.sitracel.paie.callout.CalloutChargerDernierElement;
import org.sitracel.paie.callout.CalloutDateElementPaie;
import org.sitracel.paie.callout.CalloutMajMouvementPaieFinPeriode;
import org.sitracel.paie.callout.CalloutMajMouvementPaieMensualite;
import org.sitracel.paie.callout.CalloutMajMouvementPaieType;
import org.sitracel.paie.callout.CalloutMajPeriodeSalariale;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRMouvementPaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;

public class SitracelPaieCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {

        List<IColumnCallout> list = new ArrayList<>();

        // --- HR_Periode_Salariale ---
        if (tableName.equalsIgnoreCase(MHRPeriodeSalariale.Table_Name)
                && (columnName.equalsIgnoreCase(MHRPeriodeSalariale.COLUMNNAME_Date_Debut_Defaut)
                 || columnName.equalsIgnoreCase(MHRPeriodeSalariale.COLUMNNAME_Date_Fin_Defaut))) {
            list.add(new CalloutMajPeriodeSalariale());
        }

        // --- HR_Mouvement_Paie : choix du type → sync IsIndemnite ---
        if (tableName.equalsIgnoreCase(MHRMouvementPaie.Table_Name)
                && columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_HR_MouvementPaieType_ID)) {
            list.add(new CalloutMajMouvementPaieType());
        }

        // --- HR_Mouvement_Paie : calcul mensualités + IsRecurrent ---
        if (tableName.equalsIgnoreCase(MHRMouvementPaie.Table_Name)
                && (columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_Montant_Total)
                 || columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite)
                 || columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_Acompte))) {
            list.add(new CalloutMajMouvementPaieMensualite());
        }

        // --- HR_Mouvement_Paie : calcul période de fin ---
        if (tableName.equalsIgnoreCase(MHRMouvementPaie.Table_Name)
                && (columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_Debut_Prelevement_ID)
                 || columnName.equalsIgnoreCase(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite))) {
            list.add(new CalloutMajMouvementPaieFinPeriode());
        }

        // --- HR_ElementBasePaieEmploye ---
        if (tableName.equalsIgnoreCase(MHRElementBasePaieEmploye.Table_Name)
                && columnName.equalsIgnoreCase(MHRElementBasePaieEmploye.COLUMNNAME_Charger_Dernier)) {
            list.add(new CalloutChargerDernierElement());
        }
        if (tableName.equalsIgnoreCase(MHRElementBasePaieEmploye.Table_Name)
                && columnName.equalsIgnoreCase(MHRElementBasePaieEmploye.COLUMNNAME_HR_GestionPaieEmploye_ID)) {
            list.add(new CalloutAjouterElementPaie());
        }
        if (tableName.equalsIgnoreCase(MHRElementBasePaieEmploye.Table_Name)
                && (columnName.equalsIgnoreCase(MHRElementBasePaieEmploye.COLUMNNAME_Date_Debut)
                 || columnName.equalsIgnoreCase(MHRElementBasePaieEmploye.COLUMNNAME_Date_Fin))) {
            list.add(new CalloutDateElementPaie());
        }

        return list.toArray(new IColumnCallout[0]);
    }
}
