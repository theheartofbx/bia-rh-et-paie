package org.sitracel.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.model.MHRAmpliation;
import org.sitracel.model.MHRCategorieResponsabilite;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.model.MHROrganigramme;
import org.sitracel.model.MHRParametreNumerique;

public class SitracelModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return MHRAmpliation.class;
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return MHROrganigramme.class;
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return MHREmployeeJob.class;
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return MHRCategorieResponsabilite.class;
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return MHRParametreNumerique.class;
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), Record_ID, trxName);
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return new MHRAmpliation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
