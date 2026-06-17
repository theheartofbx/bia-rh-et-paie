package org.sitracel.bean;

import java.sql.Timestamp;

public class BeanNotification {

	private String nomEmetteur;
	private Integer posteEmetteur;
	private String nomValidateur;
	private Integer posteValidateur;
	private String nomEmploye;
	private Integer posteEmploye;
	private String typeSanction;
	private String motif;
	private Timestamp dateEmission;
	private Timestamp dateValidation;
	private Timestamp datereponse;
	private String delaiReponse;
	private String mailEmetteur;
	private String mailValidateur;
	private String mailEmploye;

	public BeanNotification() {

	}

	public String getNomEmetteur() {
		return nomEmetteur;
	}

	public void setNomEmetteur(String nomEmetteur) {
		this.nomEmetteur = nomEmetteur;
	}

	public Integer getPosteEmetteur() {
		return posteEmetteur;
	}

	public void setPosteEmetteur(Integer posteEmetteur) {
		this.posteEmetteur = posteEmetteur;
	}

	public String getNomValidateur() {
		return nomValidateur;
	}

	public void setNomValidateur(String nomValidateur) {
		this.nomValidateur = nomValidateur;
	}

	public Integer getPosteValidateur() {
		return posteValidateur;
	}

	public void setPosteValidateur(Integer posteValidateur) {
		this.posteValidateur = posteValidateur;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public Integer getPosteEmploye() {
		return posteEmploye;
	}

	public void setPosteEmploye(Integer posteEmploye) {
		this.posteEmploye = posteEmploye;
	}

	public String getTypeSanction() {
		return typeSanction;
	}

	public void setTypeSanction(String typeSanction) {
		this.typeSanction = typeSanction;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public Timestamp getDateEmission() {
		return dateEmission;
	}

	public void setDateEmission(Timestamp dateEmission) {
		this.dateEmission = dateEmission;
	}

	public Timestamp getDateValidation() {
		return dateValidation;
	}

	public void setDateValidation(Timestamp dateValidation) {
		this.dateValidation = dateValidation;
	}

	public Timestamp getDatereponse() {
		return datereponse;
	}

	public void setDatereponse(Timestamp datereponse) {
		this.datereponse = datereponse;
	}

	public String getDelaiReponse() {
		return delaiReponse;
	}

	public void setDelaiReponse(String delaiReponse) {
		this.delaiReponse = delaiReponse;
	}

	public String getMailEmetteur() {
		return mailEmetteur;
	}

	public void setMailEmetteur(String mailEmetteur) {
		this.mailEmetteur = mailEmetteur;
	}

	public String getMailValidateur() {
		return mailValidateur;
	}

	public void setMailValidateur(String mailValidateur) {
		this.mailValidateur = mailValidateur;
	}

	public String getMailEmploye() {
		return mailEmploye;
	}

	public void setMailEmploye(String mailEmploye) {
		this.mailEmploye = mailEmploye;
	}



}
