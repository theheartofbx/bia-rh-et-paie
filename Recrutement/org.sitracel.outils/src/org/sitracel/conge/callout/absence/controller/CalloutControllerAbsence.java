package org.sitracel.conge.callout.absence.controller;

import java.sql.Timestamp;

import org.sitracel.controller.GeneralSqlController;

public class CalloutControllerAbsence {
	
	public static boolean isDateAbsenceConforme(Timestamp date, Integer cBpartnerID) {
		boolean resultat = false;
		if(date!=null && cBpartnerID!=null) {
			resultat = !CalloutSqlControllerAbsence.isAbsenceExist(date, cBpartnerID, null);
			if(resultat) {
				resultat = !GeneralSqlController.isJourFerie(date,null) 
						&& !GeneralSqlController.isJourCongesNonRejetebyNameConge(cBpartnerID, "Annuel", date, null) 
						&& !GeneralSqlController.isJourSuspensionNonRejete(cBpartnerID, date, null);
			}
		}
		return resultat;
	}

}
