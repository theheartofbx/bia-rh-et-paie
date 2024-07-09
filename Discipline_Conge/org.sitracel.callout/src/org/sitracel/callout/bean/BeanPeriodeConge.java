package org.sitracel.callout.bean;

import java.sql.Timestamp;

public class BeanPeriodeConge {

	private Timestamp dateDebutConge;
	private Timestamp dateFinConge;
	private double frequenceAbscent;
	
	public BeanPeriodeConge() {
		
	}

	public Timestamp getDateDebutConge() {
		return dateDebutConge;
	}

	public void setDateDebutConge(Timestamp dateDebutConge) {
		this.dateDebutConge = dateDebutConge;
	}

	public Timestamp getDateFinConge() {
		return dateFinConge;
	}

	public void setDateFinConge(Timestamp dateFinConge) {
		this.dateFinConge = dateFinConge;
	}

	public double getFrequenceAbscent() {
		return frequenceAbscent;
	}

	public void setFrequenceAbscent(double bigDecimal) {
		this.frequenceAbscent = bigDecimal;
	}


}
