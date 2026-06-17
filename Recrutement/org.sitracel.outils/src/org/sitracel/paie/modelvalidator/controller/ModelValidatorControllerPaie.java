package org.sitracel.paie.modelvalidator.controller;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.sitracel.controller.GeneralSqlController;
import org.sitracel.model.I_HR_EmployeeJob;
import org.sitracel.model.MHREmployeeJob;
import org.sitracel.paie.model.I_HR_ElementBasePaieEmploye;
import org.sitracel.paie.model.I_HR_Historique_Paie;
import org.sitracel.paie.model.MHRCalculPaie;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;
import org.sitracel.paie.model.MHRHistoriquePaie;
import org.sitracel.paie.model.MHRPeriodeSalariale;

public class ModelValidatorControllerPaie {

	public static void updateHistorique(MHRCalculPaie calculPaie, int type) {
		if(calculPaie!=null) {
			if(type==ModelValidator.TYPE_AFTER_NEW || type==ModelValidator.TYPE_AFTER_CHANGE) {
				MHRHistoriquePaie historiquePaie = GeneralSqlController.getHistoriquePaie(calculPaie.getC_BPartner_ID(),
						calculPaie.getHR_Element_Base_Paie_ID(), calculPaie.getHR_Periode_Salariale_ID(), null);
				if(historiquePaie==null) {
					historiquePaie = new MHRHistoriquePaie(Env.getCtx(), 0, null);
					historiquePaie.setC_BPartner_ID(calculPaie.getC_BPartner_ID());
					historiquePaie.setHR_Periode_Salariale_ID(calculPaie.getHR_Periode_Salariale_ID());
					historiquePaie.setHR_Element_Base_Paie_ID(calculPaie.getHR_Element_Base_Paie_ID());
					historiquePaie.setHR_Historique_Paie_ID(DB.getNextID(Env.getCtx(), I_HR_Historique_Paie.Table_Name, null));
				}
				MHRPeriodeSalariale periodeSalariale = new MHRPeriodeSalariale(Env.getCtx(), calculPaie.getHR_Periode_Salariale_ID(), null);
				if(periodeSalariale!=null) {
					historiquePaie.setDate_Debut(periodeSalariale.getDate_Debut_Defaut());
				}
				historiquePaie.setMontant(calculPaie.getMontant());
				historiquePaie.save();
			}
		}
	}

	public static void actualiserPoste(MHRElementBasePaieEmploye elementBasePaieEmploye, int type) {
		if(elementBasePaieEmploye!=null) {
			Timestamp oldDateDebut = (Timestamp)elementBasePaieEmploye.get_ValueOld(I_HR_ElementBasePaieEmploye.COLUMNNAME_Date_Debut);
			MHREmployeeJob employeeJob = GeneralSqlController.getEmployeeJob(elementBasePaieEmploye.getC_BPartner_ID(),
					oldDateDebut, null);
			if(type==ModelValidator.TYPE_AFTER_NEW || type==ModelValidator.TYPE_AFTER_CHANGE) {
				if(employeeJob==null) {
					employeeJob = new MHREmployeeJob(Env.getCtx(), 0, null);
					employeeJob.setC_BPartner_ID(elementBasePaieEmploye.getC_BPartner_ID());
					employeeJob.setHR_EmployeeJob_ID(DB.getNextID(Env.getCtx(), I_HR_EmployeeJob.Table_Name, null));
				}
				employeeJob.setDateFrom(elementBasePaieEmploye.getDate_Debut());
				employeeJob.setHR_Job_ID(elementBasePaieEmploye.getHR_Job_ID());
				employeeJob.save();
			}
			else if(type==ModelValidator.TYPE_BEFORE_DELETE){
				if(employeeJob!=null) {
					employeeJob.delete(true);
				}
			}
		}
	}

}
