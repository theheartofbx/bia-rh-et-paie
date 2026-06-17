package org.sitracel.bean;

import java.math.BigDecimal;

public class BeanBareme {
	private BigDecimal amountBareme;
	private String formuleBareme;
	public BeanBareme() {
		super();
	}
	public BigDecimal getAmountBareme() {
		return amountBareme;
	}
	public void setAmountBareme(BigDecimal amountBareme) {
		this.amountBareme = amountBareme;
	}
	public String getFormuleBareme() {
		return formuleBareme;
	}
	public void setFormuleBareme(String formuleBareme) {
		this.formuleBareme = formuleBareme;
	}

}
