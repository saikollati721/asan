package com.sai;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

	
	
	
	@PostMapping("/setUser")
	public User setUsers(@RequestBody User req) {
		return userrepo.save(req);
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers() {
		System.out.println("get users request recieved");
		return userrepo.findAll();
	}
	
	@GetMapping("/getProjectByUserId")
	public List<Project> getProjectByUserId(){
		List<Project>  projects= userrepo.getProjectByUserId(1);
		return projects;
		
	}
	
	@GetMapping("/getAllProjects")
	public List<Project> getAllProjects() {
		return projectrepo.findAll();
	}
	@PostMapping("/setProject")
	public Project setProject(@RequestBody Project req) {
		return projectrepo.save(req);
	}
	
	
	@PostMapping("/setTask")
	public Task setTask(@RequestBody Task req) {
		System.out.println("id is: **********************  "+req.getId());
		return taskrepo.save(req);
	}
	
	
	@GetMapping("/getOnlyUsers")
	public List<JoinInfo> getOnlyUsers(){
		return (List<JoinInfo>) userrepo.getOnlyUserInfo("sai@gmail.com");
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

	
	@RequestMapping("/")
	public String home(Principal principal,Model mv) {
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("in home page: ******************: "+user.getEmail()+" name"+user.getFirstName());
		mv.addAttribute("user",user);
		
		return "home";
	}
	
}
