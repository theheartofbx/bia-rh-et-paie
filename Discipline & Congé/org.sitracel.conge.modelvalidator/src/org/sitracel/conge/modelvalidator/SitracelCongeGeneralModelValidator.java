package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.Env;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.model.MHRHoliday;
import org.sitracel.conge.modelvalidator.controller.absence.ModelValidatorControllerAbsence;
import org.sitracel.conge.modelvalidator.controller.conge.ModelValidatorControllerConge;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;
import org.sitracel.model.MHREmployeeJob;

public class SitracelCongeGeneralModelValidator {
	
	public static void absence(PO po, MHRAbsence absence, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW == type) {
			absence.setDate_Emission(new Timestamp(System.currentTimeMillis()));	
			ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
		}
		if(ModelValidator.TYPE_BEFORE_DELETE == type) {
			ModelValidatorControllerAbsence.annulerDemandeExplicationSuiteAbsence(absence);
		}
		if(ModelValidator.TYPE_AFTER_CHANGE == type) {
			boolean newIsDemandeExplication =absence.isDemandeExplication();
			boolean oldIsDemandeexplication = (boolean)po.get_ValueOld(MHRAbsence.COLUMNNAME_IsDemandeExplication);
			if(newIsDemandeExplication && !oldIsDemandeexplication) {
				ModelValidatorControllerAbsence.annulerDemandeExplicationSuiteAbsence(absence);	
				ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
			}
			ModelValidatorControllerAbsence.traiterDemandeExplicationSuiteAbsence(absence);
		}
	}
	
	public static void conge(MHRHoliday holidays, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW==type) {
			holidays.setIsApprobation_Createur(false);
			holidays.setIsValidation_Createur(false);
			holidays.setDate_Emission(new Timestamp(System.currentTimeMillis()));
		}
	}
	
	public static void compenseAbence(MHRAbsenceCompensation absenceCompense, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW == type) {
			ModelValidatorControllerConge.activerCompensationConge(absenceCompense);
		}
		if(ModelValidator.TYPE_BEFORE_DELETE == type) {			
			ModelValidatorControllerConge.desactiverCompensationConge(absenceCompense);
		}
		if(ModelValidator.TYPE_AFTER_CHANGE == type) {			
			ModelValidatorControllerConge.desactiverCompensationConge(absenceCompense);
			ModelValidatorControllerConge.activerCompensationConge(absenceCompense);
		}
	}
	
	public static void employeeJob(MHREmployeeJob employeeJob, int type) {
		if(ModelValidator.TYPE_AFTER_CHANGE == type || ModelValidator.TYPE_AFTER_NEW == type) {
			ModelValidatorControllerConge.updateDepartment(employeeJob.getHR_EmployeeJob_ID(), employeeJob.getC_BPartner_ID());				
		}
	}
}
