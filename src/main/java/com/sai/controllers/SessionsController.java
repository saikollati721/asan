package com.sai.controllers;

import java.io.IOException;
import java.security.Principal;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		response.sendRedirect("/");
		
		return null;
	}

}
