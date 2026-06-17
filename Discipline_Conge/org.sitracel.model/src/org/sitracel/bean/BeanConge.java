package org.sitracel.bean;

import java.sql.Timestamp;
import java.util.List;

public class BeanConge {
	private String genre;
	private String dateAbsence;
	private String idAbsence;
	private Integer nombreJourCongeTotal;
	private Integer nombreJourCongeUtilise;
	private Integer nombreJourCongeRestant;
	private Integer anneeAnciennete;
	private Integer moisAnciennete;
	private Integer jourAnciennete;
	private Integer nombreEnfantPetit;
	private List<Integer> anneeNaissance;
	private Integer idPoste;
	private Integer departement;
	private Integer detteConge;
	private Timestamp dateDebutDernierConge;
	private Timestamp dateFinDernierConge;
	private Timestamp dateEmbauche;

	public BeanConge() {

	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDateAbsence() {
		return dateAbsence;
	}

	public void setDateAbsence(String dateAbsence) {
		this.dateAbsence = dateAbsence;
	}

	public String getIdAbsence() {
		return idAbsence;
	}

	public void setIdAbsence(String idAbsence) {
		this.idAbsence = idAbsence;
	}

	public Integer getNombreJourCongeTotal() {
		return nombreJourCongeTotal;
	}

	public void setNombreJourCongeTotal(Integer nombreJourCongeTotal) {
		this.nombreJourCongeTotal = nombreJourCongeTotal;
	}

	public Integer getNombreJourCongeUtilise() {
		return nombreJourCongeUtilise;
	}

	public void setNombreJourCongeUtilise(Integer nombreJourCongeUtilise) {
		this.nombreJourCongeUtilise = nombreJourCongeUtilise;
	}

	public Integer getNombreJourCongeRestant() {
		return nombreJourCongeRestant;
	}

	public void setNombreJourCongeRestant(Integer nombreJourCongeRestant) {
		this.nombreJourCongeRestant = nombreJourCongeRestant;
	}

	public Integer getAnneeAnciennete() {
		return anneeAnciennete;
	}

	public void setAnneeAnciennete(Integer anneeAnciennete) {
		this.anneeAnciennete = anneeAnciennete;
	}

	public Integer getMoisAnciennete() {
		return moisAnciennete;
	}

	public void setMoisAnciennete(Integer moisAnciennete) {
		this.moisAnciennete = moisAnciennete;
	}

	public Integer getJourAnciennete() {
		return jourAnciennete;
	}

	public void setJourAnciennete(Integer jourAnciennete) {
		this.jourAnciennete = jourAnciennete;
	}

	public Integer getNombreEnfantPetit() {
		return nombreEnfantPetit;
	}

	public void setNombreEnfantPetit(Integer nombreEnfantPetit) {
		this.nombreEnfantPetit = nombreEnfantPetit;
	}

	public List<Integer> getAnneeNaissance() {
		return anneeNaissance;
	}

	public void setAnneeNaissance(List<Integer> anneeNaissance) {
		this.anneeNaissance = anneeNaissance;
	}

	public Integer getIdPoste() {
		return idPoste;
	}

	public void setIdPoste(Integer idPoste) {
		this.idPoste = idPoste;
	}

	public Integer getDepartement() {
		return departement;
	}

	public void setDepartement(Integer departement) {
		this.departement = departement;
	}

	public Integer getDetteConge() {
		return detteConge;
	}

	public void setDetteConge(Integer detteConge) {
		this.detteConge = detteConge;
	}

	public Timestamp getDateDebutDernierConge() {
		return dateDebutDernierConge;
	}

	public void setDateDebutDernierConge(Timestamp dateDebutDernierConge) {
		this.dateDebutDernierConge = dateDebutDernierConge;
	}

	public Timestamp getDateFinDernierConge() {
		return dateFinDernierConge;
	}

	public void setDateFinDernierConge(Timestamp dateFinDernierConge) {
		this.dateFinDernierConge = dateFinDernierConge;
	}

	public Timestamp getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(Timestamp dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

}
