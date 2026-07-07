package org.sitracel.conge.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.I_HR_Autorisation_Conge;
import org.sitracel.conge.model.I_HR_Employee_Children;
import org.sitracel.conge.model.I_HR_Holiday;
import org.sitracel.conge.model.I_HR_Public_Holiday;
import org.sitracel.conge.model.I_HR_Type_Conge;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeConge;

public class SitracelCongeModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name))
            return MHRAutorisationConge.class;
        if (tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name))
            return MHREmployeeChildren.class;
        if (tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name))
            return MHRHoliday.class;
        if (tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name))
            return MHRPublicHoliday.class;
        if (tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name))
            return MHRTypeConge.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name))
            return new MHRAutorisationConge(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name))
            return new MHREmployeeChildren(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name))
            return new MHRHoliday(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name))
            return new MHRPublicHoliday(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name))
            return new MHRTypeConge(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Conge.Table_Name))
            return new MHRAutorisationConge(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Employee_Children.Table_Name))
            return new MHREmployeeChildren(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Holiday.Table_Name))
            return new MHRHoliday(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Public_Holiday.Table_Name))
            return new MHRPublicHoliday(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Conge.Table_Name))
            return new MHRTypeConge(Env.getCtx(), rs, trxName);
        return null;
    }
}
