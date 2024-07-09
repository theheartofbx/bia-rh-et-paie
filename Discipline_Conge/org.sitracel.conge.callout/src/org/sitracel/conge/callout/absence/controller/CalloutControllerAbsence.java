package org.sitracel.conge.callout.absence.controller;

import java.sql.Timestamp;

import org.sitracel.general.controller.GeneralController;

public class CalloutControllerAbsence {
	
	public static boolean isDateAbsenceConforme(Timestamp date, Integer cBpartnerID) {
		boolean resultat = false;
		if(date!=null && cBpartnerID!=null) {
			resultat = !CalloutSqlControllerAbsence.isAbsenceExist(date, cBpartnerID, null);
			if(resultat) {
				resultat = !GeneralController.isJourFerie(date) && !GeneralController.isCongeAnnuel(cBpartnerID, date) && !GeneralController.isJourSuspension(cBpartnerID, date);
			}
		}
		return resultat;
	}

}
