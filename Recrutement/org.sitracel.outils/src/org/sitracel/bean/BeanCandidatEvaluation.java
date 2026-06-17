package org.sitracel.bean;

import java.math.BigDecimal;

public class BeanCandidatEvaluation implements Comparable<BeanCandidatEvaluation>{
	private String nameCandidature;
	private int candidatureID;
	private BigDecimal scoreTotal;
	private BigDecimal scoreMax;
	private int rang;
	private double noteSur20;
	public BeanCandidatEvaluation() {
		super();
	}

	public String getNameCandidature() {
		return nameCandidature;
	}

	public void setNameCandidature(String nameCandidature) {
		this.nameCandidature = nameCandidature;
	}

	public int getCandidatureID() {
		return candidatureID;
	}

	public void setCandidatureID(int candidatureID) {
		this.candidatureID = candidatureID;
	}

	public BigDecimal getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(BigDecimal scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public BigDecimal getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(BigDecimal scoreMax) {
		this.scoreMax = scoreMax;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public double getNoteSur20() {
		return noteSur20;
	}

	public void setNoteSur20(double noteSur20) {
		this.noteSur20 = noteSur20;
	}

	@Override
	public int compareTo(BeanCandidatEvaluation candidatEvaluation2) {
		// TODO Auto-generated method stub
		return -1*this.getScoreTotal().compareTo(candidatEvaluation2.getScoreTotal());
	}

}
