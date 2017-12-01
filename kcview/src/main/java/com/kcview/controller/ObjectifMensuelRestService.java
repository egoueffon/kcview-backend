package com.kcview.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcview.dao.ClubRepository;
import com.kcview.entity.Club;
import com.kcview.entity.ObjectifMensuel;
import com.kcview.vo.SyntheseClub;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/", produces = APPLICATION_JSON_VALUE)
public class ObjectifMensuelRestService {
	
	@Autowired
	private ClubRepository clubRepository;
	
}
