package org.sitracel.model.factory;

import java.sql.ResultSet;

import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.model.I_HR_EmployeeJob;
import org.sitracel.organigramme.model.I_HR_Organigramme;
import org.sitracel.model.I_HR_Parametre_Numerique;
import org.sitracel.model.MHRAmpliation;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.organigramme.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;
import org.sitracel.model.I_HR_Ampliation;
import org.adempiere.base.IModelFactory;

/**
 * Factory pour org.sitracel.model.
 *
 * Ne contient que les classes effectivement présentes dans ce bundle.
 * Les classes conge/discipline/paie/recrutement ont été migrées
 * dans leurs bundles respectifs et sont gérées par leurs factories.
 */
public class SitracelModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name))
            return MHRAmpliation.class;
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return MHROrganigramme.class;
        if (tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name))
            return MHREmployeeJob.class;
        if (tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name))
            return MHRParametreNumerique.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name))
            return new MHRAmpliation(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name))
            return new MHREmployeeJob(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name))
            return new MHRParametreNumerique(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Ampliation.Table_Name))
            return new MHRAmpliation(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return new MHROrganigramme(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_EmployeeJob.Table_Name))
            return new MHREmployeeJob(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Parametre_Numerique.Table_Name))
            return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
        return null;
    }
}
