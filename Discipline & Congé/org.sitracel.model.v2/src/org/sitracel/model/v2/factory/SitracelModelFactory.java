package org.sitracel.model.v2.factory;

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.model.v2.MHRAbsence;
import org.sitracel.model.v2.MHRAbsenceCompensation;
import org.sitracel.model.v2.MHRAmpliation;
import org.sitracel.model.v2.MHRAutorisationAbsence;
import org.sitracel.model.v2.MHRAutorisationConge;
import org.sitracel.model.v2.MHRCategorieResponsabilite;
import org.sitracel.model.v2.MHRDelaiQuantite;
import org.sitracel.model.v2.MHRDelaiReponse;
import org.sitracel.model.v2.MHRDemandeExplication;
import org.sitracel.model.v2.MHRDossierDisciplinaire;
import org.sitracel.model.v2.MHRDureeSanction;
import org.sitracel.model.v2.MHREmployeeChildren;
import org.sitracel.model.v2.MHREmployeeJob;
import org.sitracel.model.v2.MHRHoliday;
import org.sitracel.model.v2.MHROrganigramme;
import org.sitracel.model.v2.MHRParametreNumerique;
import org.sitracel.model.v2.MHRPublicHoliday;
import org.sitracel.model.v2.MHRPunishment;
import org.sitracel.model.v2.MHRSanctionAutorisation;
import org.sitracel.model.v2.MHRTypeAbsence;
import org.sitracel.model.v2.MHRTypeConge;
import org.sitracel.model.v2.MHRTypeSanction;

public class SitracelModelFactory implements IModelFactory{

	@Override
	public Class<?> getClass(String tableName) {
		// TODO Auto-generated method stub
		if(tableName.equalsIgnoreCase(MHRAmpliation.Table_Name)) {
			return MHRAmpliation.class;
		}
		if(tableName.equalsIgnoreCase(MHRDelaiQuantite.Table_Name)) {
			return MHRAmpliation.class;
		}
		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return MHRDemandeExplication.class;
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return MHRPunishment.class;
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return MHRTypeSanction.class;
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return MHRDureeSanction.class;
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return MHRDelaiReponse.class;
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return MHROrganigramme.class;
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return MHREmployeeJob.class;
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return MHRDossierDisciplinaire.class;
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return MHRCategorieResponsabilite.class;
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return MHRSanctionAutorisation.class;
		}
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
		if(tableName.equalsIgnoreCase(MHRDelaiQuantite.Table_Name)) {
			return new MHRDelaiQuantite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), Record_ID, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), Record_ID, trxName);
		}
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
		if(tableName.equalsIgnoreCase(MHRDelaiQuantite.Table_Name)) {
			return new MHRDelaiQuantite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDemandeExplication.Table_Name)) {
			return new MHRDemandeExplication(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRPunishment.Table_Name)) {
			return new MHRPunishment(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRTypeSanction.Table_Name)) {
			return new MHRTypeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDureeSanction.Table_Name)) {
			return new MHRDureeSanction(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDelaiReponse.Table_Name)) {
			return new MHRDelaiReponse(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHROrganigramme.Table_Name)) {
			return new MHROrganigramme(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHREmployeeJob.Table_Name)) {
			return new MHREmployeeJob(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRDossierDisciplinaire.Table_Name)) {
			return new MHRDossierDisciplinaire(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRCategorieResponsabilite.Table_Name)) {
			return new MHRCategorieResponsabilite(Env.getCtx(), rs, trxName);
		}
		if(tableName.equalsIgnoreCase(MHRSanctionAutorisation.Table_Name)) {
			return new MHRSanctionAutorisation(Env.getCtx(), rs, trxName);
		}
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
		if(tableName.equalsIgnoreCase(MHRParametreNumerique.Table_Name)) {
			return new MHRParametreNumerique(Env.getCtx(), rs, trxName);
		}
		return null;
	}

}
