package org.sitracel.bean;

import java.math.BigDecimal;

public class BeanEvaluationCompetence {
	private int candidatEvaluationID;
	private int competenceID;
	private BigDecimal score;
	private BigDecimal scoreMax;
	private int ponderation;

	public BeanEvaluationCompetence() {

	}

	public int getCandidatEvaluationID() {
		return candidatEvaluationID;
	}

	public void setCandidatEvaluationID(int candidatEvaluationID) {
		this.candidatEvaluationID = candidatEvaluationID;
	}

	public int getCompetenceID() {
		return competenceID;
	}

	public void setCompetenceID(int competenceID) {
		this.competenceID = competenceID;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public BigDecimal getScoreMax() {
		return scoreMax;
	}

	public void setScoreMax(BigDecimal scoreMax) {
		this.scoreMax = scoreMax;
	}

	public int getPonderation() {
		return ponderation;
	}

	public void setPonderation(int ponderation) {
		this.ponderation = ponderation;
	}
}
