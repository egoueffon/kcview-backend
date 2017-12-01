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

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Iterables;

@Entity
@Table(name="kc_suivi_comptant")
public class SuiviComptant implements Serializable   {

	private static final long serialVersionUID = 1L;

	public SuiviComptant() {
		super();
	}

	public SuiviComptant(Row row, int clubId) {

		setNum_adherent(row.getCell(0).getNumericCellValue());
		setNom_adherent(row.getCell(1).getStringCellValue());
		setPrenom_adherent(row.getCell(2).getStringCellValue());
		setNum_telephone(row.getCell(3).getNumericCellValue());
		setNom_contrat(row.getCell(4).getStringCellValue());
		setDate_expiration(row.getCell(6).getDateCellValue());
		setId_club(clubId);
		setStatut(0);
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Double num_adherent;
	private String prenom_adherent;
	private String nom_adherent;
	private String nom_contrat;
	private Double num_telephone;
	private Date date_expiration;
	private Date date_changement_statut;
	private Integer statut;
	private Integer id_club;
	
	@OneToMany(mappedBy = "suivi", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({"suivi"})
	@OrderBy("date_relance DESC")
	@Fetch(FetchMode.SUBSELECT)
	private Set<RelanceComptant> relances;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getNum_adherent() {
		return num_adherent;
	}
	public void setNum_adherent(Double num_adherent) {
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
		if(nom_contrat != null) {
			return nom_contrat.replaceAll("CMPT", "").replaceAll("TWO ", "T").replaceAll("COOL", "C");
		}else {
			return null;
		}
		
	}
	public void setNom_contrat(String nom_contrat) {
		this.nom_contrat = nom_contrat;
	}
	public Double getNum_telephone() {
		return num_telephone;
	}
	public void setNum_telephone(Double num_telephone) {
		this.num_telephone = num_telephone;
	}
	public Date getDate_expiration() {
		return date_expiration;
	}
	public void setDate_expiration(Date date_expiration) {
		this.date_expiration = date_expiration;
	}
	public Integer getStatut() {
		return statut;
	}
	public void setStatut(Integer statut) {
		this.statut = statut;
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

	public Integer getId_club() {
		return id_club;
	}

	public void setId_club(Integer id_club) {
		this.id_club = id_club;
	}

	public Date getDate_changement_statut() {
		return date_changement_statut;
	}

	public void setDate_changement_statut(Date date_changement_statut) {
		this.date_changement_statut = date_changement_statut;
	}

	
}
