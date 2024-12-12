package org.sitracel.bean;

import java.util.ArrayList;

public class BeanInfoAbsence {
	
	private String date;
	private Integer CBPartnerID;
	private ArrayList<Integer> listAbsenceID;
	private int nombreJour;
	
	public BeanInfoAbsence() {
		
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getNombreJour() {
		return nombreJour;
	}

	public void setNombreJour(int nombreJour) {
		this.nombreJour = nombreJour;
	}

	public Integer getCBPartnerID() {
		return CBPartnerID;
	}

	public void setCBPartnerID(Integer cBPartnerID) {
		CBPartnerID = cBPartnerID;
	}

	public ArrayList<Integer> getListAbsenceID() {
		return listAbsenceID;
	}

	public void setListAbsenceID(ArrayList<Integer> listAbsenceID) {
		this.listAbsenceID = listAbsenceID;
	}

}
