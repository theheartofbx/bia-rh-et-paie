package org.sitracel.organigramme.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.organigramme.model.I_HR_Categorie_Responsabilite;
import org.sitracel.organigramme.model.I_HR_Organigramme;
import org.sitracel.organigramme.model.MHRCategorieResponsabilite;
import org.sitracel.organigramme.model.MHROrganigramme;

public class SitracelOrganigrammeModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return MHROrganigramme.class;
        if (tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name))
            return MHRCategorieResponsabilite.class;
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name))
            return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        if (tableName.equalsIgnoreCase(I_HR_Organigramme.Table_Name))
            return new MHROrganigramme(Env.getCtx(), rs, trxName);
        if (tableName.equalsIgnoreCase(I_HR_Categorie_Responsabilite.Table_Name))
            return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
        return null;
    }
}
