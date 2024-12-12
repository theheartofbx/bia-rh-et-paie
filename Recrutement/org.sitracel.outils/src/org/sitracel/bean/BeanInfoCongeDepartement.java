package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanInfoCongeDepartement {
	private int nombreEmployeDepartement;
	private int nombreEmployeDepartementConge;
	private Timestamp jourCritique;
	private double pourcentageEmployeConge;
	
	public BeanInfoCongeDepartement() {
		
	}

	public int getNombreEmployeDepartement() {
		return nombreEmployeDepartement;
	}

	public void setNombreEmployeDepartement(int nombreEmployeDepartement) {
		this.nombreEmployeDepartement = nombreEmployeDepartement;
	}

	public int getNombreEmployeDepartementConge() {
		return nombreEmployeDepartementConge;
	}

	public void setNombreEmployeDepartementConge(int nombreEmployeDepartementConge) {
		this.nombreEmployeDepartementConge = nombreEmployeDepartementConge;
	}

	public Timestamp getJourCritique() {
		return jourCritique;
	}

	public void setJourCritique(Timestamp jourCritique) {
		this.jourCritique = jourCritique;
	}

	public double getPourcentageEmployeConge() {
		return pourcentageEmployeConge;
	}

	public void setPourcentageEmployeConge(double pourcentageEmployeConge) {
		this.pourcentageEmployeConge = pourcentageEmployeConge;
	}

}
