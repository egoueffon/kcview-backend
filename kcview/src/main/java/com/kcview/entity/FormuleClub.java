package com.kcview.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.kcview.vo.FormuleClubId;

@Entity
@Table(name="kc_formule_club")
public class FormuleClub   {

	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private FormuleClubId id;
	
	public Formule getFormule() {
		return id.getFormule();
	}
	
	@Column(name = "pack")
	private BigDecimal pack;
	
	@Column(name = "mois")
	private BigDecimal mois;

	public BigDecimal getPack() {
		return pack;
	}

	public void setPack(BigDecimal pack) {
		this.pack = pack;
	}

	public BigDecimal getMois() {
		return mois;
	}

	public void setMois(BigDecimal mois) {
		this.mois = mois;
	}
	
	

}
