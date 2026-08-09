package org.sitracel.formation.model.factory;

import java.sql.ResultSet;
import java.util.Properties;
import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

public class SitracelFormationModelFactory implements IModelFactory {

    @Override
    public Class<?> getClass(String tableName) {
        return null;
    }

    @Override
    public PO getPO(String tableName, int Record_ID, String trxName) {
        return null;
    }

    @Override
    public PO getPO(String tableName, ResultSet rs, String trxName) {
        return null;
    }
}
