package com.kcview.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Encaissement")
public class Encaissement implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@ManyToOne
    @JoinColumn(name = "id_operation")
	@JsonIgnoreProperties("encaissements")
	private Operation operation;
	
	private BigDecimal montant_encaisse;
	private String mode_versement;
	
	
	public BigDecimal getMontant_encaisse() {
		return montant_encaisse;
	}
	public void setMontant_encaisse(BigDecimal montant_encaisse) {
		this.montant_encaisse = montant_encaisse;
	}
	public String getMode_versement() {
		return mode_versement;
	}
	public void setMode_versement(String mode_versement) {
		this.mode_versement = mode_versement;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}

}
