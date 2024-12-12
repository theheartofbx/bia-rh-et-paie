package org.sitracel.discipline.callout.sanction.controller;

public class CalloutControllerDiscipline {
	
	public static String getAmpliation(String ampliation, String initial, String annee) {
		if(ampliation!=null && initial!=null) {
			ampliation = ampliation.replaceAll("\\s+", "");
			if(ampliation.length()>2) {
				ampliation = ampliation.replaceAll("/"+ampliation.substring(ampliation.length()-2), ""); 
			}
			if(!ampliation.isEmpty()) {
				if(ampliation.contains("/"+initial)){
					ampliation = ampliation.replaceAll("/"+initial, "");
				}
				else {
					ampliation = ampliation+"/"+initial;
				}
			}
			else {
				ampliation = ampliation+"/"+initial;
			}
			if(annee.length()>2) {
				ampliation = ampliation + "/"+annee.substring(annee.length()-2);
			}
		}
		return ampliation;
	}	
}
