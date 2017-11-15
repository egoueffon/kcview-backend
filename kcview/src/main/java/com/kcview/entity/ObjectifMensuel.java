package com.kcview.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="objectifmois")
@IdClass(ObjectifMensuel.class)
public class ObjectifMensuel implements Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id_club;
	private int abos;
	private BigDecimal ca;
	//private int abosMoinsUN = 0;
	//private int abosMoisSuivant = 0;
	//private int abosMoisSuivantNM1 = 0;
	//private double caMoinsUN = 0;
	//private double caMoisSuivant = 0;
	//private double caMoisSuivantNM1 = 0;
	@Id
	private int mois;
	@Id
	private int annee;
	
	
	public int getId_club() {
		return id_club;
	}
	public void setId_club(int id_club) {
		this.id_club = id_club;
	}
	public int getAbos() {
		return abos;
	}
	public void setAbos(int abos) {
		this.abos = abos;
	}
	public BigDecimal getCa() {
		return ca;
	}
	public void setCa(BigDecimal ca) {
		this.ca = ca;
	}
	public int getMois() {
		return mois;
	}
	public void setMois(int mois) {
		this.mois = mois;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}

	

	
}
