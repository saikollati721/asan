package com.sai.controllers;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;
import com.sai.services.SecurityService;

@Controller
public class UsersContoller {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private TaskRepository taskrepo;

	
	@Autowired
	private AssignedProjectsRepository assignedprojectrepo;
	
	@Autowired
    private SecurityService securityService;

	
	@RequestMapping("/")
	public String home(Principal principal,Model mv) {
		User user=userrepo.findByEmail(principal.getName());
		mv.addAttribute("user",user);
		
		long id=user.getId();
		List<Project> projects = projectrepo.findByUserId(id);
		mv.addAttribute("projects", projects);
		
		List<AssignedProject> assign=assignedprojectrepo.findByUserId(id);
		List<Project> assignedProject = new ArrayList<Project>();
		if(assign!=null) {
			for(AssignedProject ap:assign) {
				long projectid=ap.getProjectId();
				assignedProject.add(projectrepo.findByProjectId(projectid));
			}
		}	
		mv.addAttribute("assignedProject", assignedProject);	
		return "home";
	}
	
	
	
	@GetMapping("/registration")
	public String showRegistrationForm() {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String registerUserAccount(@ModelAttribute("user") User req) {
		userrepo.save(req);
		securityService.autoLogin(req.getEmail(), req.getPassword());
		return "redirect:/";
	}
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	
	
	public List<Project> getProjectByUserId(long id){
		return projectrepo.findByUserId(id);
		
	}

	
	
	
//	@GetMapping("/getOnlyUsers")
//	public List<JoinInfo> getOnlyUsers(){
//		return (List<JoinInfo>) userrepo.getOnlyUserInfo("sai@gmail.com");
//	}
//	
}
