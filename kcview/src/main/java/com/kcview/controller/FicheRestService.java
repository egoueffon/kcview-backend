package com.kcview.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kcview.dao.FicheRepository;
import com.kcview.dao.UserRepository;
import com.kcview.entity.Club;
import com.kcview.entity.Employe;
import com.kcview.entity.Fiche;
import com.kcview.entity.Operation;
import com.kcview.vo.SyntheseFicheVO;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FicheRestService {
	
	@Autowired
	private FicheRepository ficheRepository;


	@CrossOrigin(origins = "*")
	@RequestMapping("/Fiche")
	public ResponseEntity<Fiche> Fiche(int clubId,@DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		Fiche list = ficheRepository.findFiche(clubId, date);
		return new ResponseEntity<Fiche>(list, HttpStatus.OK);
	}
	

	@RequestMapping("/FicheById")
	public ResponseEntity<Fiche> Fiche(int ficheId) {
		Fiche fiche = ficheRepository.findOne(ficheId);
		return new ResponseEntity<Fiche>(fiche, HttpStatus.OK);
	}
	

	@RequestMapping("/syntheseFiche")
	public ResponseEntity<SyntheseFicheVO> syntheseFiche(int clubId,@DateTimeFormat(pattern="yyyy-MM-dd") Date date) {
		Fiche fiche = ficheRepository.findFiche(clubId, date);
		
		SyntheseFicheVO syntheseFiche = new SyntheseFicheVO(); 
		syntheseFiche.setFiche(fiche);
		syntheseFiche.setEncaissement(ficheRepository.getEncaissement(fiche.getId()));
		syntheseFiche.setEncaissementCB(ficheRepository.getEncaissementCB(fiche.getId()));
		syntheseFiche.setEncaissementEspece(ficheRepository.getEncaissementEspece(fiche.getId()));
		syntheseFiche.setEncaissementCheque(ficheRepository.getEncaissementCheque(fiche.getId()));
		syntheseFiche.setEncaissementAutre(ficheRepository.getEncaissementAutre(fiche.getId()));
		return new ResponseEntity<SyntheseFicheVO>(syntheseFiche, HttpStatus.OK);
	}
}
