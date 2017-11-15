package com.kcview.vo;

import java.math.BigDecimal;

import com.kcview.entity.Fiche;

public class SyntheseFicheVO {
	
	private Fiche fiche;
	private BigDecimal encaissement = new BigDecimal(0);
	private BigDecimal encaissementCB = new BigDecimal(0);
	private BigDecimal encaissementCheque = new BigDecimal(0);
	private BigDecimal encaissementEspece = new BigDecimal(0);
	private BigDecimal encaissementAutre = new BigDecimal(0);
	
	public Fiche getFiche() {
		return fiche;
	}
	public void setFiche(Fiche fiche) {
		this.fiche = fiche;
	}
	public BigDecimal getEncaissement() {
		return encaissement;
	}
	public void setEncaissement(BigDecimal encaissement) {
		this.encaissement = encaissement;
	}
	public BigDecimal getEncaissementCB() {
		return encaissementCB;
	}
	public void setEncaissementCB(BigDecimal encaissementCB) {
		this.encaissementCB = encaissementCB;
	}
	public BigDecimal getEncaissementCheque() {
		return encaissementCheque;
	}
	public void setEncaissementCheque(BigDecimal encaissementCheque) {
		this.encaissementCheque = encaissementCheque;
	}
	public BigDecimal getEncaissementEspece() {
		return encaissementEspece;
	}
	public void setEncaissementEspece(BigDecimal encaissementEspece) {
		this.encaissementEspece = encaissementEspece;
	}
	public BigDecimal getEncaissementAutre() {
		return encaissementAutre;
	}
	public void setEncaissementAutre(BigDecimal encaissementAutre) {
		this.encaissementAutre = encaissementAutre;
	}

}
