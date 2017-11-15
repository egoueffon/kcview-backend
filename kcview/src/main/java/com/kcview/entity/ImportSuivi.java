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
import javax.persistence.Table;

import org.apache.poi.ss.util.NumberToTextConverter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.common.collect.Iterables;

@Entity
@Table(name="kc_importation_suivi")
public class ImportSuivi implements Serializable   {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private Date date_import;
	private String type;
	private int id_club;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDate_import() {
		return date_import;
	}
	public void setDate_import(Date date_import) {
		this.date_import = date_import;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getId_club() {
		return id_club;
	}
	public void setId_club(int id_club) {
		this.id_club = id_club;
	}
	
	
}
