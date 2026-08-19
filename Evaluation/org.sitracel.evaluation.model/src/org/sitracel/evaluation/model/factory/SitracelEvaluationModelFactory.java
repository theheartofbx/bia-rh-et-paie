package org.sitracel.evaluation.model.factory;

import java.sql.ResultSet;
import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.evaluation.model.*;

public class SitracelEvaluationModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        switch (tableName) {
            case "HR_EvalCategorie":       return MHR_EvalCategorie.class;
            case "HR_EvalStatut":          return MHR_EvalStatut.class;
            case "HR_EvalAppreciation":    return MHR_EvalAppreciation.class;
            case "HR_EvalObjectif":        return MHR_EvalObjectif.class;
            case "HR_EvalPeriode":         return MHR_EvalPeriode.class;
            case "HR_EvalGrille":          return MHR_EvalGrille.class;
            case "HR_EvalGrilleLigne":     return MHR_EvalGrilleLigne.class;
            case "HR_EvalGrilleFormule":   return MHR_EvalGrilleFormule.class;
            case "HR_Eval":               return MHR_Eval.class;
            case "HR_EvalLigne":           return MHR_EvalLigne.class;
            case "HR_EvalResultat":        return MHR_EvalResultat.class;
            default: return null;
        }
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        switch (tableName) {
            case "HR_EvalCategorie":       return new MHR_EvalCategorie(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalStatut":          return new MHR_EvalStatut(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalAppreciation":    return new MHR_EvalAppreciation(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalObjectif":        return new MHR_EvalObjectif(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalPeriode":         return new MHR_EvalPeriode(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalGrille":          return new MHR_EvalGrille(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalGrilleLigne":     return new MHR_EvalGrilleLigne(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalGrilleFormule":   return new MHR_EvalGrilleFormule(Env.getCtx(), Record_ID, trxName);
            case "HR_Eval":               return new MHR_Eval(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalLigne":           return new MHR_EvalLigne(Env.getCtx(), Record_ID, trxName);
            case "HR_EvalResultat":        return new MHR_EvalResultat(Env.getCtx(), Record_ID, trxName);
            default: return null;
        }
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        switch (tableName) {
            case "HR_EvalCategorie":       return new MHR_EvalCategorie(Env.getCtx(), rs, trxName);
            case "HR_EvalStatut":          return new MHR_EvalStatut(Env.getCtx(), rs, trxName);
            case "HR_EvalAppreciation":    return new MHR_EvalAppreciation(Env.getCtx(), rs, trxName);
            case "HR_EvalObjectif":        return new MHR_EvalObjectif(Env.getCtx(), rs, trxName);
            case "HR_EvalPeriode":         return new MHR_EvalPeriode(Env.getCtx(), rs, trxName);
            case "HR_EvalGrille":          return new MHR_EvalGrille(Env.getCtx(), rs, trxName);
            case "HR_EvalGrilleLigne":     return new MHR_EvalGrilleLigne(Env.getCtx(), rs, trxName);
            case "HR_EvalGrilleFormule":   return new MHR_EvalGrilleFormule(Env.getCtx(), rs, trxName);
            case "HR_Eval":               return new MHR_Eval(Env.getCtx(), rs, trxName);
            case "HR_EvalLigne":           return new MHR_EvalLigne(Env.getCtx(), rs, trxName);
            case "HR_EvalResultat":        return new MHR_EvalResultat(Env.getCtx(), rs, trxName);
            default: return null;
        }
    }
}
