package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanAbsence {
	
	private Timestamp dateAbsence;
	private String typeAbsence;
	private int idTypeAbsence;
	private int idCBpartner;
	
	public BeanAbsence() {
		
	}

	public Timestamp getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(Timestamp dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public String getTypeAbsence() {
		return typeAbsence;
	}

	public void setTypeAbsence(String typeAbsence) {
		this.typeAbsence = typeAbsence;
	}

	public int getIdTypeAbsence() {
		return idTypeAbsence;
	}

	public void setIdTypeAbsence(int idTypeAbsence) {
		this.idTypeAbsence = idTypeAbsence;
	}

	public int getIdCBpartner() {
		return idCBpartner;
	}

	public void setIdCBpartner(int idCBpartner) {
		this.idCBpartner = idCBpartner;
	}
	

}
