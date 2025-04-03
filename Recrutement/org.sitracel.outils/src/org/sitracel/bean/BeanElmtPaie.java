package org.sitracel.bean;

import java.math.BigDecimal;

public class BeanElmtPaie {
	private Integer cbpartnerID;
	private String value;
	private Integer conceptID;
	private String formule;
	private Integer baseCalculID;
	private BigDecimal taux;
	public BeanElmtPaie() {
		super();
	}
	public Integer getCbpartnerID() {
		return cbpartnerID;
	}
	public void setCbpartnerID(Integer cbpartnerID) {
		this.cbpartnerID = cbpartnerID;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getConceptID() {
		return conceptID;
	}
	public void setConceptID(Integer conceptID) {
		this.conceptID = conceptID;
	}
	public String getFormule() {
		return formule;
	}
	public void setFormule(String formule) {
		this.formule = formule;
	}
	public Integer getBaseCalculID() {
		return baseCalculID;
	}
	public void setBaseCalculID(Integer baseCalculID) {
		this.baseCalculID = baseCalculID;
	}
	public BigDecimal getTaux() {
		return taux;
	}
	public void setTaux(BigDecimal taux) {
		this.taux = taux;
	}
}
