package com.kcview.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.kcview.vo.FormuleClubId;

@Entity
@Table(name="kc_formule_duree")
public class FormuleDuree   {

	private static final long serialVersionUID = 1L;
	
	@Id
	private int id;
	
	@Column(name = "duree")
	private int duree;
	
	@ManyToOne
    @JoinColumn(name = "id_formule")
    private Formule formule;

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}
	
}
