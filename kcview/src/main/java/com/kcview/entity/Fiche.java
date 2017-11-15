package com.kcview.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Date;


import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Fiche")
public class Fiche implements Serializable   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date jour;
	private int  vp;
	private int  resiliation;
	private int  eau;
	private String meteo;
	private int objectif_abo;
	private int club_id;

	//private RecapVO recap;
	//private ObjectifMoisVO objectif;
	
	@OneToMany(mappedBy = "fiche", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("fiche")
	private Set<Operation> listOperation;

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getJour() {
		return jour;
	}

	public void setJour(Date jour) {
		this.jour = jour;
	}

	public int getVp() {
		return vp;
	}

	public void setVp(int vp) {
		this.vp = vp;
	}

	public int getResiliation() {
		return resiliation;
	}

	public void setResiliation(int resiliation) {
		this.resiliation = resiliation;
	}

	public int getEau() {
		return eau;
	}

	public void setEau(int eau) {
		this.eau = eau;
	}

	public String getMeteo() {
		return meteo;
	}

	public void setMeteo(String meteo) {
		this.meteo = meteo;
	}

	public int getObjectif_abo() {
		return objectif_abo;
	}

	public void setObjectif_abo(int objectif_abo) {
		this.objectif_abo = objectif_abo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getId_club() {
		return club_id;
	}

	public void setId_club(int club_id) {
		this.club_id = club_id;
	}

	public Set<Operation> getListOperation() {
		return listOperation;
	}

	public void setListOperation(Set<Operation> listOperation) {
		this.listOperation = listOperation;
	}
	
}
