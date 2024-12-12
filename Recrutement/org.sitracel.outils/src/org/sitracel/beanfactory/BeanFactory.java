package org.sitracel.beanfactory;

import org.sitracel.bean.BeanAbsence;
import org.sitracel.bean.BeanCandidatEvaluation;
import org.sitracel.bean.BeanConge;
import org.sitracel.bean.BeanEvaluationCompetence;
import org.sitracel.bean.BeanIdentifiant;
import org.sitracel.bean.BeanIndemniteConge;
import org.sitracel.bean.BeanInfoAbsence;
import org.sitracel.bean.BeanInfoCongeDepartement;
import org.sitracel.bean.BeanMiseaPieds;
import org.sitracel.bean.BeanNotification;
import org.sitracel.bean.BeanNotificationConge;
import org.sitracel.bean.BeanPeriode;
import org.sitracel.bean.BeanPeriodeConge;
import org.sitracel.bean.BeanResumeAbsence;

public class BeanFactory {	
	public static BeanPeriode getBeanPeriode() {
		return new BeanPeriode();
	}

	public static BeanPeriodeConge getBeanPeriodeConge() {
		return new BeanPeriodeConge();
	}
	
	public static BeanConge getBeanConge() {
		return new BeanConge();
	}
	
	public static BeanResumeAbsence getBeanResumeAbsence() {
		return new BeanResumeAbsence();
	}
	
	public static BeanIndemniteConge getBeanIndemniteConge() {
		return new BeanIndemniteConge();
	}	

	public static BeanInfoCongeDepartement getBeanInfoCongeDepartement() {
		return new BeanInfoCongeDepartement();
	}
	
	public static BeanAbsence getBeanAbsence() {
		return new BeanAbsence();
	}
	
	public static BeanInfoAbsence getBeanInfoAbsence() {
		return new BeanInfoAbsence();
	}
	
	public static BeanIdentifiant getBeanIdentifiant() {
		return new BeanIdentifiant();
	}
	
	public static BeanNotification getBeanNotification() {
		return new BeanNotification();
	}

	public static BeanMiseaPieds getBeanMiseaPieds() {
		return new BeanMiseaPieds();
	}
	
	public static BeanNotificationConge getBeanNotificationConge() {
		return new BeanNotificationConge();
	}
	
	public static BeanEvaluationCompetence getBeanEvaluationCompetence() {
		return new BeanEvaluationCompetence();
	}

	public static BeanCandidatEvaluation getBeanCandidatEvaluation() {
		return new BeanCandidatEvaluation();
	}
}
