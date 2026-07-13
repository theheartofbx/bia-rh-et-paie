package org.sitracel.paie.callout;

import java.math.BigDecimal;
import java.util.Properties;

import org.adempiere.base.IColumnCallout;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.sitracel.paie.model.MHRMouvementPaie;

/**
 * Callout HR_Mouvement_Paie — Calcul automatique des mensualités.
 *
 * Déclenché sur : Montant_Total, Nombre_Mensualite, Acompte
 *
 * Mode déterminé (IsRecurrent) :
 *   - Nombre_Mensualite = 0 ou null → IsRecurrent='Y', mensualités vides
 *   - Nombre_Mensualite > 0         → IsRecurrent='N', mensualités calculées
 *
 * Logique mensualités (mode non récurrent) :
 *   base = Montant_Total - Acompte
 *   mensualite = base / Nombre_Mensualite  (division entière)
 *   derniere   = base - mensualite * (N-1) (absorbe les centimes)
 *   solde      = base (initialisé = montant restant à prélever)
 */
public class CalloutMajMouvementPaieMensualite implements IColumnCallout {

    @Override
    public String start(Properties ctx, int WindowNo, GridTab mTab,
                        GridField mField, Object value, Object oldValue) {

        BigDecimal montantTotal   = (BigDecimal) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Montant_Total);
        BigDecimal acompte        = (BigDecimal) mTab.getValue(MHRMouvementPaie.COLUMNNAME_Acompte);
        Integer    nbMensualite   = (Integer)    mTab.getValue(MHRMouvementPaie.COLUMNNAME_Nombre_Mensualite);

        boolean recurrent = (nbMensualite == null || nbMensualite <= 0);

        // Poser le drapeau IsRecurrent
        mTab.setValue("IsRecurrent", recurrent ? "Y" : "N");

        if (recurrent) {
            // Mode CDI : pas de calcul de mensualités, pas de solde
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Mensualite,         null);
            mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Derniere_Mensualite, null);
            mTab.setValue("Solde", null);
            return null;
        }

        if (montantTotal == null) return null;

        // Base = montant total moins l'éventuel acompte déjà versé
        BigDecimal base = montantTotal;
        if (acompte != null && acompte.compareTo(BigDecimal.ZERO) > 0) {
            base = base.subtract(acompte);
        }

        // Calcul mensualités
        BigDecimal mensualite = base.divideToIntegralValue(BigDecimal.valueOf(nbMensualite));
        BigDecimal derniere;
        if (nbMensualite == 1) {
            derniere = mensualite;
        } else {
            derniere = base.subtract(mensualite.multiply(BigDecimal.valueOf(nbMensualite - 1)));
        }

        mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Mensualite,          mensualite);
        mTab.setValue(MHRMouvementPaie.COLUMNNAME_Montant_Derniere_Mensualite, derniere);
        mTab.setValue("Solde", base);

        return null;
    }
}
