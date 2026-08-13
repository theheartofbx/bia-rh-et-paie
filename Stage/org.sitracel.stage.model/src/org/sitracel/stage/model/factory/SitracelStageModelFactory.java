package org.sitracel.stage.model.factory;

import java.sql.ResultSet;
import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.stage.model.*;

public class SitracelStageModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        switch (tableName) {
            case "HR_StageTypeObjectif":     return MHR_StageTypeObjectif.class;
            case "HR_StageEtatObjectif":     return MHR_StageEtatObjectif.class;
            case "HR_StageObjectif":         return MHR_StageObjectif.class;
            case "HR_StageProgramme":        return MHR_StageProgramme.class;
            case "HR_StageProgrammeLigne":   return MHR_StageProgrammeLigne.class;
            case "HR_Stage":                 return MHR_Stage.class;
            case "HR_StageSuivi":            return MHR_StageSuivi.class;
            default: return null;
        }
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        switch (tableName) {
            case "HR_StageTypeObjectif":     return new MHR_StageTypeObjectif(Env.getCtx(), Record_ID, trxName);
            case "HR_StageEtatObjectif":     return new MHR_StageEtatObjectif(Env.getCtx(), Record_ID, trxName);
            case "HR_StageObjectif":         return new MHR_StageObjectif(Env.getCtx(), Record_ID, trxName);
            case "HR_StageProgramme":        return new MHR_StageProgramme(Env.getCtx(), Record_ID, trxName);
            case "HR_StageProgrammeLigne":   return new MHR_StageProgrammeLigne(Env.getCtx(), Record_ID, trxName);
            case "HR_Stage":                 return new MHR_Stage(Env.getCtx(), Record_ID, trxName);
            case "HR_StageSuivi":            return new MHR_StageSuivi(Env.getCtx(), Record_ID, trxName);
            default: return null;
        }
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        switch (tableName) {
            case "HR_StageTypeObjectif":     return new MHR_StageTypeObjectif(Env.getCtx(), rs, trxName);
            case "HR_StageEtatObjectif":     return new MHR_StageEtatObjectif(Env.getCtx(), rs, trxName);
            case "HR_StageObjectif":         return new MHR_StageObjectif(Env.getCtx(), rs, trxName);
            case "HR_StageProgramme":        return new MHR_StageProgramme(Env.getCtx(), rs, trxName);
            case "HR_StageProgrammeLigne":   return new MHR_StageProgrammeLigne(Env.getCtx(), rs, trxName);
            case "HR_Stage":                 return new MHR_Stage(Env.getCtx(), rs, trxName);
            case "HR_StageSuivi":            return new MHR_StageSuivi(Env.getCtx(), rs, trxName);
            default: return null;
        }
    }
}
