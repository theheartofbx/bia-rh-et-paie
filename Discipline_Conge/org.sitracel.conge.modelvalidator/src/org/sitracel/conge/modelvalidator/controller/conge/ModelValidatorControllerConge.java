package org.sitracel.conge.modelvalidator.controller.conge;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.compiere.util.Env;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.general.controller.GeneralController;
import org.sitracel.model.MCBPartner;
import org.sitracel.model.MHRJob;

public class ModelValidatorControllerConge {
	public static void updateDepartment(Integer idEmployeeJob, Integer idCBPartner) {
		if(idEmployeeJob!=null && idCBPartner!=null) {
			Integer idPoste = ModelValidatorSqlControllerConge.getHR_Job_ID_FromHREmployeeJob(idCBPartner, null);
			if(idPoste!=null) {
				MHRJob poste = new MHRJob(Env.getCtx(), idPoste, null);
				if(poste!=null) {
					MCBPartner employe = new MCBPartner(Env.getCtx(), idCBPartner, null);
					if(employe!=null) {
						employe.setHR_Department_ID(poste.getHR_Department_ID());
					}
				}
			}
		}
	}		
	
	public static void activerCompensationConge(MHRAbsenceCompensation absenceCompense) {
		absenceCompense.setDate_Emission(new Timestamp(System.currentTimeMillis()));	
		MHRHoliday conge = new MHRHoliday(Env.getCtx(), absenceCompense.getConge_ID(), null);
		MHRAbsence absence = new MHRAbsence(Env.getCtx(), absenceCompense.getAbsence_ID(), null);
		if(conge!=null && absence!=null) {
			String dateCompense = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(absence.getDate_Absence());
			if(absenceCompense.getMode_Compensation().equalsIgnoreCase(MHRAbsenceCompensation.MODE_COMPENSATION_CompenserAuDébutDuCongé)) {
				if(conge.getDate_Absence_Compense_Debut()!=null) {
					if(conge.getDate_Absence_Compense_Debut()==null) {
						conge.setDate_Debut_Compensee(GeneralController.ajusterenAjoutant(conge.getDate_Debut_Compensee()));
						conge.setDate_Absence_Compense_Debut(dateCompense);
					}
					else {
						if(!conge.getDate_Absence_Compense_Debut().contains(dateCompense)) {
							conge.setDate_Debut_Compensee(GeneralController.ajusterenAjoutant(conge.getDate_Debut_Compensee()));
							conge.setDate_Absence_Compense_Debut(conge.getDate_Absence_Compense_Debut()	+", "+dateCompense);
						}
					}
				}
			}
			else {
				if(conge.getDate_Absence_Compense_Fin()==null) {
					conge.setDate_Fin_Compensee(GeneralController.ajusterenRetirant(conge.getDate_Fin_Compensee()));
					conge.setDate_Absence_Compense_Fin(dateCompense);
				}
				else {
					if(!conge.getDate_Absence_Compense_Fin().contains(dateCompense)) {
						conge.setDate_Fin_Compensee(GeneralController.ajusterenRetirant(conge.getDate_Fin_Compensee()));
						conge.setDate_Absence_Compense_Fin(conge.getDate_Absence_Compense_Fin()	+", "+dateCompense);
					}
				}
			}
			absence.setHR_Holiday_ID(conge.getHR_Holiday_ID());		
		}
	}
	
	public static void desactiverCompensationConge(MHRAbsenceCompensation absenceCompense) {
		MHRHoliday conge = new MHRHoliday(Env.getCtx(), absenceCompense.getConge_ID(), null);
		MHRAbsence absence = new MHRAbsence(Env.getCtx(), absenceCompense.getAbsence_ID(), null);
		if(conge!=null && absence!=null) {
			String dateCompense = new SimpleDateFormat("dd MMMM yyyy", Locale.FRENCH).format(absence.getDate_Absence());
			if(absenceCompense.getMode_Compensation().equalsIgnoreCase(MHRAbsenceCompensation.MODE_COMPENSATION_CompenserAuDébutDuCongé)) {
				if(conge.getDate_Absence_Compense_Debut()!=null) {
					if(conge.getDate_Absence_Compense_Debut().contains(dateCompense)) {
						conge.setDate_Debut_Compensee(GeneralController.ajusterenRetirant(conge.getDate_Debut_Compensee()));
						conge.setDate_Absence_Compense_Debut(conge.getDate_Absence_Compense_Debut().replace(", "+dateCompense, ""));
						conge.setDate_Absence_Compense_Debut(conge.getDate_Absence_Compense_Debut().replace(dateCompense+", ", ""));
						conge.setDate_Absence_Compense_Debut(conge.getDate_Absence_Compense_Debut().replace(dateCompense, ""));
					}
				}
			}
			else {
				if(conge.getDate_Absence_Compense_Fin()!=null){
					if(conge.getDate_Absence_Compense_Fin().contains(dateCompense)) {
						conge.setDate_Fin_Compensee(GeneralController.ajusterenAjoutant(conge.getDate_Fin_Compensee()));
						conge.setDate_Absence_Compense_Fin(conge.getDate_Absence_Compense_Fin().replace(", "+dateCompense, ""));
						conge.setDate_Absence_Compense_Fin(conge.getDate_Absence_Compense_Fin().replace(dateCompense+", ", ""));
						conge.setDate_Absence_Compense_Fin(conge.getDate_Absence_Compense_Fin().replace(dateCompense, ""));
					}
				}
				
			}
			absence.setHR_Holiday_ID(0);
		}
	}

}
