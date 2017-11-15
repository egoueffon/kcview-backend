package com.kcview.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.poi.ss.util.NumberToTextConverter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Iterables;

@Entity
@Table(name="kc_suivi_comptant")
public class SuiviComptant implements Serializable   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private double num_adherent;
	private String prenom_adherent;
	private String nom_adherent;
	private String nom_contrat;
	private double num_telephone;
	private Date date_expiration;
	private int Statut;
	
	@OneToMany(mappedBy = "suivi", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"suivi"})
	@OrderBy("date_relance DESC")
	private Set<RelanceComptant> relances;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public double getNum_adherent() {
		return num_adherent;
	}
	public void setNum_adherent(double num_adherent) {
		this.num_adherent = num_adherent;
	}
	
	public String num_adherentString() {
		return NumberToTextConverter.toText(num_adherent);
	}
	
	
	public String getPrenom_adherent() {
		return prenom_adherent;
	}
	public void setPrenom_adherent(String prenom_adherent) {
		this.prenom_adherent = prenom_adherent;
	}
	public String getNom_adherent() {
		return nom_adherent;
	}
	public void setNom_adherent(String nom_adherent) {
		this.nom_adherent = nom_adherent;
	}
	public String getNom_contrat() {
		return nom_contrat.replaceAll("CMPT", "").replaceAll("TWO ", "T").replaceAll("COOL", "C");
	}
	public void setNom_contrat(String nom_contrat) {
		this.nom_contrat = nom_contrat;
	}
	public double getNum_telephone() {
		return num_telephone;
	}
	public void setNum_telephone(double num_telephone) {
		this.num_telephone = num_telephone;
	}
	public Date getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}
	public int getStatut() {
		return Statut;
	}
	public void setStatut(int statut) {
		Statut = statut;
	}

	public Set<RelanceComptant> getRelances() {
		return relances;
	}

	public void setRelances(Set<RelanceComptant> relances) {
		this.relances = relances;
	}
	
	public Date getDateDerniereRelance() {
		
		if(relances.size() > 0) {
			return Iterables.getFirst(relances, null).getDate_relance();
		}else{
			return null;
		}
		
	}

	
}
