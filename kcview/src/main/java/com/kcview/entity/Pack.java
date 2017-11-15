package com.kcview.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="pack2")
public class Pack implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	//@GeneratedValue(strategy=GenerationType.AUTO)
	
	@Id
	public int id;
	
	private String value;
	
	@ManyToOne
    @JoinColumn(name = "id_club")
	@JsonIgnoreProperties({"employes", "packs", "formules"})
	private Club club;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

}
