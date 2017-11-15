package com.kcview.controller;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kcview.dao.ClubRepository;
import com.kcview.dao.ObjectifMensuelRepository;
import com.kcview.entity.Club;
import com.kcview.entity.ObjectifMensuel;
import com.kcview.vo.StatAboVP;
import com.kcview.vo.SyntheseClub;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClubRestService {
	
	@Autowired
	private ClubRepository clubRepository;
	
	@Autowired
	private ObjectifMensuelRepository objectifMensuelRepository;

	@CrossOrigin(origins = "*")
	@GetMapping("club/{id}")
	public ResponseEntity<Club> getClubById(@PathVariable("id") Integer id) {
		Club club = clubRepository.getOne(id);
		return new ResponseEntity<Club>(club, HttpStatus.OK);
	}
	
	@CrossOrigin(origins = "*")
	@GetMapping("clubs")
	public ResponseEntity<List<Club>> getAllClubs() {
		List<Club> list = clubRepository.findAll();
		return new ResponseEntity<List<Club>>(list, HttpStatus.OK);
	}
	
	@GetMapping("getClubAbosByMonth")
	public ResponseEntity<Integer> countClubAbosByMonth(int clubId, int month, int year) {
		Integer list = clubRepository.countClubAbosByMonth(clubId, month, year);
		return new ResponseEntity<Integer>(list, HttpStatus.OK);
	}
	
	@GetMapping("test")
	public ResponseEntity<StatAboVP> countClubAbosVPByDayForMonth(int clubId, int month, int year) {
		List<Object[]> list = clubRepository.countClubAbosVPByDayForMonth(clubId, month, year);
		List<Object[]> listnm1 = clubRepository.countClubAbosVPByDayForMonth(clubId, month, year-1);
		StatAboVP stat = new StatAboVP(list,listnm1);
		return new ResponseEntity<StatAboVP>(stat, HttpStatus.OK);
	}
	
	@GetMapping("test2")
	public ResponseEntity<StatAboVP> countClubAbosVPByDayForWeek(int clubId, int week, int year) {
		List<Object[]> list = clubRepository.countClubAbosVPByDayForWeek(clubId, week, year);
		List<Object[]> listnm1 = clubRepository.countClubAbosVPByDayForWeek(clubId, week, year-1);
		StatAboVP stat = new StatAboVP(list, listnm1);
		return new ResponseEntity<StatAboVP>(stat, HttpStatus.OK);
	}
	
	@GetMapping("getClubSynthese")
	public ResponseEntity<SyntheseClub> getClubSynthese(int clubId, @DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		
		 //Date date= new Date();
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(date);
		 int day = cal.get(Calendar.DAY_OF_MONTH);
		 int week = cal.get(Calendar.WEEK_OF_YEAR);
		 int month = cal.get(Calendar.MONTH);
		 month++;
		 int year = cal.get(Calendar.YEAR);

		
		SyntheseClub syntheseJour = new SyntheseClub();
		
		syntheseJour.setClub(clubRepository.findOne(clubId));
		
		
		syntheseJour.setActualDate(date);
		syntheseJour.setNbAbosMois(clubRepository.countClubAbosByMonthAtDay(clubId, day, month, year));
		syntheseJour.setNbAbosMoisN1(clubRepository.countClubAbosByMonth(clubId, month, year - 1));
		syntheseJour.setNbAbosSemaine(clubRepository.countClubAbosByWeek(clubId, week, year));
		syntheseJour.setNbAbosSemaineN1(clubRepository.countClubAbosByWeek(clubId, week, year - 1));
		syntheseJour.setCAMois(clubRepository.getCaByMonthAtDay(clubId, day, month, year));
		syntheseJour.setCAMoisN1(clubRepository.getCaByMonthAtDay(clubId, day, month, year - 1));
		syntheseJour.setCASemaine(clubRepository.getCaByWeek(clubId, week, year));
		syntheseJour.setCASemaineN1(clubRepository.getCaByWeek(clubId, week, year - 1));
		syntheseJour.setNbAbosJour(clubRepository.countClubAbosByDay(clubId, day, month, year));
		
		ObjectifMensuel objectifMensuel = objectifMensuelRepository.findObjectifMensuel(clubId, month, year);
		
		syntheseJour.setObjectifAbosMois(objectifMensuel.getAbos());
		syntheseJour.setObjectifCAMois(objectifMensuel.getCa());
		return new ResponseEntity<SyntheseClub>(syntheseJour, HttpStatus.OK);
	}
	
}
