package org.sitracel.absence.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.absence.model.I_HR_Absence;
import org.sitracel.absence.model.I_HR_Absence_Compensation;
import org.sitracel.absence.model.I_HR_Autorisation_Absence;
import org.sitracel.absence.model.I_HR_Type_Absence;
import org.sitracel.absence.model.MHRAbsence;
import org.sitracel.absence.model.MHRAbsenceCompensation;
import org.sitracel.absence.model.MHRAutorisationAbsence;
import org.sitracel.absence.model.MHRTypeAbsence;

public class SitracelAbsenceModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Absence.Table_Name))
            return MHRAbsence.class;
        if (tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name))
            return MHRAbsenceCompensation.class;
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name))
            return MHRAutorisationAbsence.class;
        if (tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name))
            return MHRTypeAbsence.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Absence.Table_Name))
            return new MHRAbsence(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name))
            return new MHRAbsenceCompensation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name))
            return new MHRAutorisationAbsence(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name))
            return new MHRTypeAbsence(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Absence.Table_Name))
            return new MHRAbsence(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Absence_Compensation.Table_Name))
            return new MHRAbsenceCompensation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Autorisation_Absence.Table_Name))
            return new MHRAutorisationAbsence(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Type_Absence.Table_Name))
            return new MHRTypeAbsence(Env.getCtx(), rs, trxName);
        return null;
    }
}
