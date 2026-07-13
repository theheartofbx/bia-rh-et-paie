package org.sitracel.paie.callout;

import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.paie.model.MHRMouvementPaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;

/**
 * Callout HR_Mouvement_Paie — Calcul automatique de la période de fin.
 *
 * Déclenché sur : Debut_Prelevement_ID, Nombre_Mensualite
 *
 * - Mode récurrent (IsRecurrent='Y') : pas de calcul, fin laissée vide.
 * - Mode normal   (IsRecurrent='N') : calcule Fin_Prelevement_ID
 *   à partir de la date de début + nombre de mensualités.
 */
public class CalloutMajMouvementPaieFinPeriode implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        Integer nbMensualite  = (Integer) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite);
        Integer periodeDebutId = (Integer) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Debut_Prelevement_ID);

        boolean recurrent = (nbMensualite == null || nbMensualite <= 0);

        if (recurrent) {
            // Mode CDI : pas de fin de période
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Fin_Prelevement_ID, null);
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Date_Fin,            null);
            return null;
        }

        if (periodeDebutId == null) return null;

        MHRPeriodeSalariale periodeDebut = new MHRPeriodeSalariale(Env.getCtx(), periodeDebutId, null);
        if (periodeDebut == null || periodeDebut.getDate_Debut_Defaut() == null) return null;

        MHRPeriodeSalariale periodeFin = GeneralSqlController
                .getPeriodeSalarialeFinRetenue(periodeDebut.getDate_Debut_Defaut(), nbMensualite, null);

        if (periodeFin != null) {
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Fin_Prelevement_ID, periodeFin.getHR_Periode_Salariale_ID());
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Date_Debut,          periodeDebut.getDate_Debut_Defaut());
        } else {
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Fin_Prelevement_ID, null);
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Date_Debut,          null);
        }

        return null;
    }
}
