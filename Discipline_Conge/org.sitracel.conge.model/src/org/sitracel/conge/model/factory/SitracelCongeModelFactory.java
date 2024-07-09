package org.sitracel.conge.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRAutorisationAbsence;
import org.sitracel.conge.model.MHRAutorisationConge;
import org.sitracel.conge.model.MHREmployeeChildren;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.model.MHRPublicHoliday;
import org.sitracel.conge.model.MHRTypeAbsence;
import org.sitracel.conge.model.MHRTypeConge;

public class SitracelCongeModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return MHRAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return MHRAbsenceCompensation.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return MHRTypeAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return MHRHoliday.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return MHRTypeConge.class;
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return MHRAutorisationAbsence.class;
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return MHRAutorisationConge.class;
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return MHRPublicHoliday.class;
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return MHREmployeeChildren.class;
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), Record_ID, trxName);
		}
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAbsence.Table_Name)) {
			return new MHRAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAbsenceCompensation.Table_Name)) {
			return new MHRAbsenceCompensation(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeAbsence.Table_Name)) {
			return new MHRTypeAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRHoliday.Table_Name)) {
			return new MHRHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeConge.Table_Name)) {
			return new MHRTypeConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationAbsence.Table_Name)) {
			return new MHRAutorisationAbsence(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRAutorisationConge.Table_Name)) {
			return new MHRAutorisationConge(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPublicHoliday.Table_Name)) {
			return new MHRPublicHoliday(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeChildren.Table_Name)) {
			return new MHREmployeeChildren(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
