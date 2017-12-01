package com.kcview.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

//import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kcview.dao.UserRepository;
import com.kcview.entity.Employe;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/v1/", produces = APPLICATION_JSON_VALUE)
public class UserRestService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@RequestMapping("/saveUser")
	public Employe saveUser(Employe e) {
		return userRepository.save(e);
	}
	
	
	@RequestMapping("/users")
	public Page<Employe> users(int page, int size) {
		return userRepository.findAll(new PageRequest(page, size));
	}
	
	
	@RequestMapping("/allUsers")
	public List<Employe> AllUsers() {
		return userRepository.findAll();
	}
	
	
	@RequestMapping("/getEmp")
	public Employe login(String login, String password) {
		System.out.println("password " + password);
			Integer id = userRepository.findByLoginPassword(login, password);
			return userRepository.findOne(id);
	}
	 
//	@RequestMapping("/getLogedUser")
//	public Map<String, Object> getLogedUser(HttpSession session){
//		SecurityContext securityContext = (SecurityContext)session.getAttribute("SPRING_SECURITY_CONTEXT");
//		String username = securityContext.getAuthentication().getName();
//		List<String> roles = new ArrayList<>();
//		for(GrantedAuthority ga: securityContext.getAuthentication().getAuthorities()) {
//			roles.add(ga.getAuthority());
//		}
//		
//		
//		Map<String, Object>  params = new HashMap<>();
//		
//		params.put("username", username);
//		params.put("roles", roles); 
//		
//		return params;
//	}
}
