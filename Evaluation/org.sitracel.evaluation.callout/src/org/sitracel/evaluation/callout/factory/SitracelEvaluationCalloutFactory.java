package org.sitracel.evaluation.callout.factory;

import java.util.ArrayList;
import java.util.List;

import org.adempiere.base.IColumnCallout;
import org.adempiere.base.IColumnCalloutFactory;
import org.sitracel.evaluation.callout.grille.CalloutGrilleLigneObjectif;
import org.sitracel.evaluation.callout.grille.CalloutGrilleFormulePrincipale;
import org.sitracel.evaluation.callout.grille.CalloutSeqNoAuto;
import org.sitracel.evaluation.callout.ligne.CalloutEvalLigneAppreciation;
import org.sitracel.evaluation.callout.ligne.CalloutEvalLigneBinaire;

public class SitracelEvaluationCalloutFactory implements IColumnCalloutFactory {

    @Override
    public IColumnCallout[] getColumnCallouts(String tableName, String columnName) {
        List<IColumnCallout> list = new ArrayList<IColumnCallout>();

        // --- Grille Ligne ---
        if ("HR_EvalGrilleLigne".equals(tableName)) {
            if ("HR_EvalObjectif_ID".equals(columnName)) {
                list.add(new CalloutGrilleLigneObjectif());
            }
            if ("SeqNo".equals(columnName)) {
                list.add(new CalloutSeqNoAuto());
            }
        }

        // --- Grille Formule ---
        if ("HR_EvalGrilleFormule".equals(tableName)) {
            if ("IsPrincipale".equals(columnName)) {
                list.add(new CalloutGrilleFormulePrincipale());
            }
        }

        // --- Eval Ligne ---
        if ("HR_EvalLigne".equals(tableName)) {
            if ("HR_EvalAppreciation_ID".equals(columnName)) {
                list.add(new CalloutEvalLigneAppreciation());
            }
            if ("IsOk".equals(columnName)) {
                list.add(new CalloutEvalLigneBinaire());
            }
            if ("SeqNo".equals(columnName)) {
                list.add(new CalloutSeqNoAuto());
            }
        }

        return list.toArray(new IColumnCallout[0]);
    }
}
