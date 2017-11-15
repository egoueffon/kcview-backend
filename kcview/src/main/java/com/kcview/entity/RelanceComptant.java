package com.kcview.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="kc_relance_comptant")
public class RelanceComptant implements Serializable   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int type;
	private String commentaire;
	private Date date_relance;
	
	@ManyToOne
    @JoinColumn(name = "id_suivi")
	@JsonIgnoreProperties({"relances"})
	private SuiviComptant suivi;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public SuiviComptant getSuivi() {
		return suivi;
	}

	public void setSuivi(SuiviComptant suivi) {
		this.suivi = suivi;
	}

	public Date getDate_relance() {
		return date_relance;
	}

	public void setDate_relance(Date date_relance) {
		this.date_relance = date_relance;
	}
	
}
