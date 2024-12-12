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
		
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		// TODO Auto-generated method stub
		return null;
	}

}
