package com.sai;

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

@Controller
public class UserContoller {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private TaskRepository taskrepo;

	
	@Autowired
	private AssignedProjectsRepository assignedprojectrepo;
	
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

	
	@RequestMapping("/")
	public String home(Principal principal,Model mv) {
		User user=userrepo.findByEmail(principal.getName());
		mv.addAttribute("user",user);
		
		long id=user.getId();
		List<Project> projects = projectrepo.findByUserId(id);
		mv.addAttribute("projects", projects);
		
		List<AssignedProjects> assign=assignedprojectrepo.findByUserId(id);
		List<Project> assignedProject = new ArrayList<Project>();
		if(assign!=null) {
			for(AssignedProjects ap:assign) {
				long projectid=ap.getProjectId();
				assignedProject.add(projectrepo.findByProjectId(projectid));
			}
		}
		
		mv.addAttribute("assignedProject", assignedProject);
//		
		return "home";
	}
	
	
	
	
	@ModelAttribute("user")
	public User user() {
		return new User();
	}
	
	
	
	public List<Project> getProjectByUserId(long id){
		return projectrepo.findByUserId(id);
		
	}

	
	
	
	@GetMapping("/getOnlyUsers")
	public List<JoinInfo> getOnlyUsers(){
		return (List<JoinInfo>) userrepo.getOnlyUserInfo("sai@gmail.com");
	}
	
}
