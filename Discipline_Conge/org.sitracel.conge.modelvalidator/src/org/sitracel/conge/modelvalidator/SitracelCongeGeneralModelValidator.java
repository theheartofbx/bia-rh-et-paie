package org.sitracel.conge.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.sitracel.conge.model.MHRAbsence;
import org.sitracel.conge.model.MHRAbsenceCompensation;
import org.sitracel.conge.modelvalidator.controller.absence.ModelValidatorControllerAbsence;
import org.sitracel.conge.modelvalidator.controller.conge.ModelValidatorControllerConge;
import org.sitracel.paie.model.MHRElementBasePaieEmploye;

public class SitracelCongeGeneralModelValidator {

	private static CLogger	log = CLogger.getCLogger (PO.class);
	
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
	
	public static void conge(PO holidays, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW==type) {
			holidays.set_ValueOfColumn("isapprobation_createur", false);
			holidays.set_ValueOfColumn("isvalidation_createur",false);
			holidays.set_ValueOfColumn("date_emission",new Timestamp(System.currentTimeMillis()));
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
	
	public static void employeeJobD(MHRElementBasePaieEmploye lastElement, int type) {
		if(ModelValidator.TYPE_AFTER_CHANGE == type || ModelValidator.TYPE_AFTER_NEW == type) {
			ModelValidatorControllerConge.updateDepartment( lastElement.getC_BPartner_ID());				
		}
	}
}
