package com.kcview.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.kcview.entity.Club;
import com.kcview.entity.Formule;

@Embeddable
public class FormuleClubId implements Serializable {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ManyToOne
    @JoinColumn(name = "formule_id")
    private Formule formule;
 
	@ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
 
    public FormuleClubId() {
    }

	public Formule getFormule() {
		return formule;
	}

	public void setFormule(Formule formule) {
		this.formule = formule;
	}  
}