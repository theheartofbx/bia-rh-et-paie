package org.sitracel.callout.beanfactory;

import org.sitracel.callout.bean.BeanAbsence;
import org.sitracel.callout.bean.BeanConge;
import org.sitracel.callout.bean.BeanIdentifiant;
import org.sitracel.callout.bean.BeanIndemniteConge;
import org.sitracel.callout.bean.BeanInfoAbsence;
import org.sitracel.callout.bean.BeanInfoCongeDepartement;
import org.sitracel.callout.bean.BeanMiseaPieds;
import org.sitracel.callout.bean.BeanNotification;
import org.sitracel.callout.bean.BeanNotificationConge;
import org.sitracel.callout.bean.BeanPeriode;
import org.sitracel.callout.bean.BeanPeriodeConge;
import org.sitracel.callout.bean.BeanResumeAbsence;

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
	
}
