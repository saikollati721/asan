package com.sai;

import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/createproject")
public class CreateProjectController {
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	
	@ModelAttribute("project")
	public Project user() {
		return new Project();
	}
	
	@GetMapping
	public String showRegistrationForm() {
		return "createproject";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("projectName") String  projectName, Principal principal) {
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
		
		projectrepo.save(project);
		return "redirect:/";
	}

}
