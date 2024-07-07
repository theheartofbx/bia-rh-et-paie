package org.sitracel.callout.bean;

public class BeanIdentifiant {

	private String nomPoste;
	private String nomEmploye;
	private String matriculeEmploye;
	private Integer numeroPoste;
	private Integer numEmploye;
	
	public BeanIdentifiant() {
		
	}

	public String getNomPoste() {
		return nomPoste;
	}

	public void setNomPoste(String nomPoste) {
		this.nomPoste = nomPoste;
	}

	public String getNomEmploye() {
		return nomEmploye;
	}

	public void setNomEmploye(String nomEmploye) {
		this.nomEmploye = nomEmploye;
	}

	public String getMatriculeEmploye() {
		return matriculeEmploye;
	}

	public void setMatriculeEmploye(String matriculeEmploye) {
		this.matriculeEmploye = matriculeEmploye;
	}

	public Integer getNumeroPoste() {
		return numeroPoste;
	}

	public void setNumeroPoste(Integer numeroPoste) {
		this.numeroPoste = numeroPoste;
	}

	public Integer getNumEmploye() {
		return numEmploye;
	}

	public void setNumEmploye(Integer numEmploye) {
		this.numEmploye = numEmploye;
	}	
}
