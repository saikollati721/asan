package com.sai.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sai.model.User;
import com.sai.repository.UserRepository;

@Controller
public class SessionsController {

	@Autowired
	private UserRepository userrepo;
	
	public SessionsController() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	
	
	@GetMapping("/login")
	public String login(Principal principal,Model mv,HttpServletResponse response) throws IOException {
		if(principal==null)
			return "login";
		System.out.println("Logdin user is **************************   : "+principal.getName());
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("in home page: ******************: "+user.getEmail()+" name"+user.getFirstName());
		mv.addAttribute("user",user);
		long id=user.getId();
//		response.sendRedirect("/");
		return "redirect:http://localhost:4200/";
		
//		return null;
	}
	
	

	
	@GetMapping("/")
	public String home(Principal principal) {
//		System.out.println("user logeed in and their name is :"+principal.getName());
//		User user=userrepo.findByEmail(principal.getName());
//		System.out.println("in home page: ******************: "+user.getEmail()+" name"+user.getFirstName());
//		long id=user.getId();
		return "redirect:http://localhost:4200/";
	}
}
