package com.kcview.controller;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcview.dao.UserRepository;
import com.kcview.entity.Club;
import com.kcview.entity.Employe;
import com.kcview.entity.Operation;
import com.kcview.vo.SyntheseClub;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/api/v1/", produces = APPLICATION_JSON_VALUE)
public class OperationRestService {
	
	@Autowired
	private com.kcview.dao.OperationRepository operationRepository;

	
	@RequestMapping(value = "/saveOperation", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Operation> saveOperation(@RequestBody Operation operation) {
		System.out.println("test");
		Operation op = operationRepository.save(operation);
		return new ResponseEntity<Operation>(op, HttpStatus.OK);
	}
	
	
	
	@RequestMapping(value = "/save/", method = {GET, POST}, produces = APPLICATION_JSON_VALUE )
	public ResponseEntity<Operation> save(@RequestBody Operation operation) {
		System.out.println("test");
		Operation op = operationRepository.save(operation);
		return new ResponseEntity<Operation>(op, HttpStatus.OK);
	}
}
