package com.kcview.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity(name = "Formule")
@Table(name="kc_formule")
public class Formule   {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private int formuleId;
	
	@Column(name="NOM")
	private String nomFormule;
	
	@Column(name="QUALITE")
	private String qualite;
	
	@Column(name="PRELEVEMENT")
	private Boolean prelevement;
	
	@Column(name="COMPTANT")
	private Boolean comptant;
	
	@OneToMany(mappedBy="id.formule", cascade = CascadeType.ALL)
	private Set<FormuleClub> formuleClub;
	
	
	@OneToMany(mappedBy="formule", cascade = CascadeType.ALL)
	@OrderBy("duree ASC")
	private Set<FormuleDuree> durees;

	public Formule() {
	}


	public String getNomFormule() {
		return nomFormule;
	}

	public void setNomFormule(String nomFormule) {
		this.nomFormule = nomFormule;
	}

	public Boolean getPrelevement() {
		return prelevement;
	}

	public void setPrelevement(Boolean prelevement) {
		this.prelevement = prelevement;
	}

	public Boolean getComptant() {
		return comptant;
	}

	public void setComptant(Boolean comptant) {
		this.comptant = comptant;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getFormuleId() {
		return formuleId;
	}

	public void setFormuleId(int formuleId) {
		this.formuleId = formuleId;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}


	public Set<FormuleDuree> getDurees() {
		return durees;
	}


	public void setDurees(Set<FormuleDuree> durees) {
		this.durees = durees;
	}	
}
