package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanMiseaPieds {
	
	private String isValide;
	private String isReject;
	private String typeSanction;
	private int  delaiSanction;
	private Timestamp dateDebutApplication;
	
	public BeanMiseaPieds() {
		
	}

	public String getIsValide() {
		return isValide;
	}

	public void setIsValide(String isValide) {
		this.isValide = isValide;
	}

	public String getIsReject() {
		return isReject;
	}

	public void setIsReject(String isReject) {
		this.isReject = isReject;
	}


	public String getTypeSanction() {
		return typeSanction;
	}

	public void setTypeSanction(String typeSanction) {
		this.typeSanction = typeSanction;
	}

	public int getDelaiSanction() {
		return delaiSanction;
	}

	public void setDelaiSanction(int delaiSanction) {
		this.delaiSanction = delaiSanction;
	}

	public Timestamp getDateDebutApplication() {
		return dateDebutApplication;
	}

	public void setDateDebutApplication(Timestamp dateDebutApplication) {
		this.dateDebutApplication = dateDebutApplication;
	}
	
	
}
