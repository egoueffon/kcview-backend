package com.kcview.entity;
import java.io.Serializable;
import java.util.Dictionary;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="client")
public class Franchise implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
    private int id;  
	
	@Column(name="nom")	
	private String name;
	
	@ManyToOne
    @JoinColumn(name = "id_club")
	@JsonIgnoreProperties("packs")
	private Club club;
	
		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}

}