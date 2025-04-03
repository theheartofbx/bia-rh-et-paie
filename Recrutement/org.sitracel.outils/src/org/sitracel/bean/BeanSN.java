package org.sitracel.bean;

import java.math.BigDecimal;

public class BeanSN {
	private BigDecimal salaireBrut;
	private BigDecimal chargesSalariales;
	public BeanSN() {
		super();
	}
	public BigDecimal getSalaireBrut() {
		return salaireBrut;
	}
	public void setSalaireBrut(BigDecimal salaireBrut) {
		this.salaireBrut = salaireBrut;
	}
	public BigDecimal getChargesSalariales() {
		return chargesSalariales;
	}
	public void setChargesSalariales(BigDecimal chargesSalariales) {
		this.chargesSalariales = chargesSalariales;
	}

}
