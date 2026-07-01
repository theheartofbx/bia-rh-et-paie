package org.sitracel.contrat.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.contrat.model.I_HR_Affectation;
import org.sitracel.contrat.model.I_HR_Contrat;
import org.sitracel.contrat.model.I_HR_ContratStatut;
import org.sitracel.contrat.model.I_HR_ContratType;
import org.sitracel.contrat.model.MHRAffectation;
import org.sitracel.contrat.model.MHRContrat;
import org.sitracel.contrat.model.X_HR_ContratStatut;
import org.sitracel.contrat.model.X_HR_ContratType;

public class SitracelContratModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_ContratType.Table_Name))
            return X_HR_ContratType.class;
        if (tableName.equalsIgnoreCase(I_HR_ContratStatut.Table_Name))
            return X_HR_ContratStatut.class;
        if (tableName.equalsIgnoreCase(I_HR_Contrat.Table_Name))
            return MHRContrat.class;
        if (tableName.equalsIgnoreCase(I_HR_Affectation.Table_Name))
            return MHRAffectation.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_ContratType.Table_Name))
            return new X_HR_ContratType(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_ContratStatut.Table_Name))
            return new X_HR_ContratStatut(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Contrat.Table_Name))
            return new MHRContrat(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Affectation.Table_Name))
            return new MHRAffectation(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_ContratType.Table_Name))
            return new X_HR_ContratType(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_ContratStatut.Table_Name))
            return new X_HR_ContratStatut(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Contrat.Table_Name))
            return new MHRContrat(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Affectation.Table_Name))
            return new MHRAffectation(Env.getCtx(), rs, trxName);
        return null;
    }
}
