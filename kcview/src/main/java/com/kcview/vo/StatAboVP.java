package com.kcview.vo;

import java.util.ArrayList;
import java.util.List;

public class StatAboVP {
	
	public StatAboVP(List<Object[]> list, List<Object[]> listnm1) {

		
		for (Object o[] : list) {
			Integer jour = (Integer) o[0];
			Integer abo = ((Long) o[1]).intValue();
			Integer vp = (Integer) o[2];
			
			//this.jours.add(jour);
			this.abos.add(abo);
			this.vps.add(vp);
		}
		
		for (Object o[] : listnm1) {
			Integer jour = (Integer) o[0];
			Integer abo = ((Long) o[1]).intValue();
			Integer vp = (Integer) o[2];
			
			this.jours.add(jour);
			this.abosnm1.add(abo);
			this.vpsnm1.add(vp);
		}
	}
	public List<Integer> jours = new ArrayList<Integer>();
	public List<Integer> abos = new ArrayList<Integer>();
	public List<Integer> vps = new  ArrayList<Integer>();
	public List<Integer> abosnm1 = new ArrayList<Integer>();
	public List<Integer> vpsnm1 = new  ArrayList<Integer>();
	

}
