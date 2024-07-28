package org.sitracel.discipline.modelvalidator;

import java.sql.Timestamp;

import org.compiere.model.ModelValidator;
import org.compiere.util.Env;
import org.sitracel.discipline.model.MHRDemandeExplication;
import org.sitracel.discipline.model.MHRPunishment;

public class ModelValidatorDisciplineController {
	
	public static void demandeExplication(MHRDemandeExplication demandeExplication, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW == type) {
			demandeExplication.setDate_Emission(new Timestamp(System.currentTimeMillis()));
		}
		if(ModelValidator.TYPE_BEFORE_CHANGE == type) {
			MHRDemandeExplication oldDemandeExplication = new MHRDemandeExplication(Env.getCtx(), demandeExplication.get_ID(), null);
			if(oldDemandeExplication!=null) {
				if(oldDemandeExplication.getReponse_Demande_Explication()!=null && demandeExplication.getReponse_Demande_Explication()!=null) {
					if(!oldDemandeExplication.getReponse_Demande_Explication().equals(demandeExplication.getReponse_Demande_Explication())) {
						demandeExplication.setDate_Reponse(new Timestamp(System.currentTimeMillis()));
					}
				}
				else {
					if(demandeExplication.getReponse_Demande_Explication()!=null) {
						demandeExplication.setDate_Reponse(new Timestamp(System.currentTimeMillis()));
					}
				}
			}
		}
	}
	
	public static void discipline(MHRPunishment punishment, int type) {
		if(ModelValidator.TYPE_BEFORE_NEW == type) {
			punishment.setIsApprobation_Createur(false);
			punishment.setIsValidation_Createur(false);
			punishment.setDate_Emission(new Timestamp(System.currentTimeMillis()));
		}	
	}	
	
}
