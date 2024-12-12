package org.sitracel.discipline.model.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.discipline.model.MHRDelaiReponse;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRDossierDisciplinaire;
import org.sitracel.discipline.model.MHRDureeSanction;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.discipline.model.MHRSanctionAutorisation;
import org.sitracel.discipline.model.MHRTypeSanction;

public class SitracelDisciplineModelFactory implements IModelFactory{

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
