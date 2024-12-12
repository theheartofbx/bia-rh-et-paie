package org.sitracel.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class BeanIndemniteConge {
	
	private BigDecimal indemnite;
	private Timestamp dateDebutConge;
	private Timestamp dateFinConge;
	
	public BeanIndemniteConge () {
		
	}
	
	public BigDecimal getIndemnite() {
		return indemnite;
	}
	public void setIndemnite(BigDecimal indemnite) {
		this.indemnite = indemnite;
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

}
