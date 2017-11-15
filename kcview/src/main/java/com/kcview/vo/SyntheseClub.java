package com.kcview.vo;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kcview.entity.Club;

public class SyntheseClub   {

	private static final long serialVersionUID = 1L;
	
	@JsonIgnoreProperties("employes")
	private Club club;
	private Date actualDate;

	private int nbAbosMois;
	private int nbAbosJour;
	private float objectifAbosMois;
	private int nbAbosMoisN1;
	private int nbAbosSemaineN1;
	private int nbAbosSemaine;
	
	private BigDecimal cAMois = new BigDecimal(0);
	private BigDecimal objectifCAMois = new BigDecimal(0);
	private BigDecimal cAMoisN1 = new BigDecimal(0);
	private BigDecimal cASemaineN1 = new BigDecimal(0);
	private BigDecimal cASemaine = new BigDecimal(0);
	
	public int getNbAbosMois() {
		return nbAbosMois;
	}
	public void setNbAbosMois(int nbAbosMois) {
		this.nbAbosMois = nbAbosMois;
	}
	public float getObjectifAbosMois() {
		return objectifAbosMois;
	}
	public void setObjectifAbosMois(int objectifAbosMois) {
		this.objectifAbosMois = objectifAbosMois;
	}
	public int getNbAbosMoisN1() {
		return nbAbosMoisN1;
	}
	public void setNbAbosMoisN1(int nbAbosMoisN1) {
		this.nbAbosMoisN1 = nbAbosMoisN1;
	}
	public int getNbAbosSemaineN1() {
		return nbAbosSemaineN1;
	}
	public void setNbAbosSemaineN1(int nbAbosSemaineN1) {
		this.nbAbosSemaineN1 = nbAbosSemaineN1;
	}
	public int getNbAbosSemaine() {
		return nbAbosSemaine;
	}
	public void setNbAbosSemaine(int nbAbosSemaine) {
		this.nbAbosSemaine = nbAbosSemaine;
	}
	public BigDecimal getObjectifCAMois() {
		return objectifCAMois;
	}
	public void setObjectifCAMois(BigDecimal d) {
		this.objectifCAMois = d;
	}
	public BigDecimal getCAMois() {
		return cAMois;
	}
	public void setCAMois(BigDecimal cAMois) {
		this.cAMois = cAMois;
	}
	public BigDecimal getCAMoisN1() {
		return cAMoisN1;
	}
	public void setCAMoisN1(BigDecimal cAMoisN1) {
		this.cAMoisN1 = cAMoisN1;
	}
	public BigDecimal getCASemaineN1() {
		return cASemaineN1;
	}
	public void setCASemaineN1(BigDecimal cASemaineN1) {
		this.cASemaineN1 = cASemaineN1;
	}
	public BigDecimal getCASemaine() {
			return cASemaine;	
	}
	
	public void setCASemaine(BigDecimal cASemaine) {
		if(cASemaine != null) {
			this.cASemaine = cASemaine;
		}else {
			this.cASemaine = new BigDecimal(0);
		}
		
	}
	public int getNbAbosJour() {
		return nbAbosJour;
	}
	
	public void setNbAbosJour(int nbAbosJour) {
		this.nbAbosJour = nbAbosJour;
	}
	public Club getClub() {
		return club;
	}
	public void setClub(Club club) {
		this.club = club;
	}
	
	public int getProgAbosMois() {
		return nbAbosMois - nbAbosMoisN1;
	}
	
	
	
	public int getProgAbosSemaine() {
		return nbAbosSemaine - nbAbosSemaineN1;
	}
	
	public BigDecimal getProgCaMois() {
		return cAMois.subtract(cAMoisN1);
	}
	
	public BigDecimal getProgCaSemaine() {
		if(cASemaine != null && cASemaineN1 != null) {
			return cASemaine.subtract(cASemaineN1);
		}else {
			return null;
		}
		
	}
	
	public float getAboPourObjectif() {
		Float iManqueAbo = objectifAbosMois - nbAbosMois;
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);
		 //int day = cal.get(Calendar.DAY_OF_MONTH);
		 
		int nbJourRestant = cal.getActualMaximum(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);
		
		return (iManqueAbo/nbJourRestant);
		
	}
	
	public BigDecimal getCaPourFaireN1() {
		BigDecimal iManqueCA = cAMoisN1.subtract(cAMois);
		
		if( iManqueCA.intValue() <0 ) return new BigDecimal(0);
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);
		 
		int nbJourRestant = cal.getActualMaximum(Calendar.DAY_OF_MONTH) - cal.get(Calendar.DAY_OF_MONTH);
		
		return iManqueCA.divide(new BigDecimal(nbJourRestant),10,BigDecimal.ROUND_HALF_DOWN);
		
	}
	
	public void setActualDate(Date date) {
		this.actualDate = date;
		
	}
	public Date getActualDate() {
		return actualDate;
	}
	
	////////////////////////////////////////////////////////////
	//DEBUT TENDANCE/////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
	
	public float getTendanceAboMois() {
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);

		 return getTendanceAboJour() * cal.getActualMaximum(Calendar.DAY_OF_MONTH); 
	}
	
	public float getTendanceAboJour() {
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);
		 return (nbAbosMois / cal.get(Calendar.DAY_OF_MONTH)); 
	}
	
	public BigDecimal getTendanceCaMois() {
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);
		 BigDecimal te = new BigDecimal(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		 
		 return getTendanceCaJour().multiply(te); 
		 
	}
	
	public BigDecimal getTendanceCaJour() {
		
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(actualDate);
		 int day = cal.get(Calendar.DAY_OF_MONTH);
		 
		 BigDecimal ca = cAMois.divide(new BigDecimal(day),10,BigDecimal.ROUND_HALF_DOWN);
		 return ca; 
	}
	
	
	////////////////////////////////////////////////////////////
	//FIN TENDANCE/////////////////////////////////////////////////
	//////////////////////////////////////////////////////////
}
