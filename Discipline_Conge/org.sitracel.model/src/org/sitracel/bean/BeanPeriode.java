package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanPeriode {

	private Timestamp dateDebutConge;
	private Timestamp dateFinConge;
	private Long employeRestant;
	
	public BeanPeriode() {
		
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

	public Long getEmployeRestant() {
		return employeRestant;
	}

	public void setEmployeRestant(Long employeRestant) {
		this.employeRestant = employeRestant;
	}
}
