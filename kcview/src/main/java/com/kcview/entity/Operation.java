package com.kcview.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name="Operation")
public class Operation implements Serializable  {

	private static final long serialVersionUID = 1L;


	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String type;
	
	@ManyToOne
    @JoinColumn(name = "id_fiche")
	@JsonIgnoreProperties("listOperation")
	private Fiche fiche;
	
	private String source;
	private String nom;
	private String prenom;
	
	private String sexe;
	
	@Type(type="numeric_boolean")
	private Boolean rvp;
	
	@Type(type="numeric_boolean")
	private Boolean reconduction;
	
	private String qualite;
	
	private Double montant_abo;
	private Double pack;
	private String pea;
	private String date_prelevement;
	
	@OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
	private Set<Encaissement> encaissements;
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Operation(){
		//setDatetime(new Date());
	}

	public Fiche getFiche() {
		return fiche;
	}

	public void setFiche(Fiche fiche) {
		this.fiche = fiche;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public Boolean isRvp() {
		return rvp;
	}

	public void setRvp(Boolean rvp) {
		this.rvp = rvp;
	}

	public Boolean isReconduction() {
		return reconduction;
	}

	public void setReconduction(Boolean reconduction) {
		this.reconduction = reconduction;
	}

	public String getQualite() {
		return qualite;
	}

	public void setQualite(String qualite) {
		this.qualite = qualite;
	}

	public Double getMontant_abo() {
		return montant_abo;
	}

	public void setMontant_abo(Double montant_abo) {
		this.montant_abo = montant_abo;
	}

	public String getPea() {
		return pea;
	}

	public void setPea(String pea) {
		this.pea = pea;
	}

	public String getDate_prelevement() {
		return date_prelevement;
	}

	public void setDate_prelevement(String date_prelevement) {
		this.date_prelevement = date_prelevement;
	}

	public Set<Encaissement> getEncaissements() {
		return encaissements;
	}

	public void setEncaissements(Set<Encaissement> encaissements) {
		
		for (Encaissement encaissement : encaissements) {
			encaissement.setOperation(this);
		}
		
		this.encaissements = encaissements;
	}

	public Double getPack() {
		return pack;
	}

	public void setPack(Double pack) {
		this.pack = pack;
	}

	

}
