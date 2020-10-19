package com.sai;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class UserRegistration {

	
	@Autowired
	private UserRepository userrepo;
	
//	@Autowired
//    private SecurityService securityService;
//	
	public UserRegistration() {
		// TODO Auto-generated constructor stub
	}
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") User req) {
		userrepo.save(req);
//		securityService.autoLogin(req.getEmail(), req.getPassword());
		return "redirect:/";
	}

}
