package com.kcview.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "clubs")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Club implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "PC")
	private Boolean pc;
	@Column(name = "city_code")
	private String cityCode;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	@OrderBy("value ASC")
	private Set<Pack> packs;

	@OneToMany(mappedBy = "id.club", cascade = CascadeType.ALL)
	@OrderBy("formule_id ASC")
	private Set<FormuleClub> formuleClub;

	@OneToMany(mappedBy = "club", cascade = CascadeType.ALL)
	@JsonIgnoreProperties({ "club" })
	private Set<Employe> employes;

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

	public Boolean getPc() {
		return pc;
	}

	public void setPc(Boolean pc) {
		this.pc = pc;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Set<Employe> getEmployes() {
		return employes;
	}

	public void setEmployes(Set<Employe> employes) {
		this.employes = employes;
	}

	public Set<Pack> getPacks() {
		return packs;
	}

	public void setPacks(Set<Pack> packs) {
		this.packs = packs;
	}

	public Set<FormuleClub> getFormuleClub() {
		return formuleClub;
	}

	public void setFormuleClub(Set<FormuleClub> formuleClub) {
		this.formuleClub = formuleClub;
	}

}