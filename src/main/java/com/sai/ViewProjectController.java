package com.sai;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/viewproject/{id}")
public class ViewProjectController {

	public ViewProjectController() {
		// TODO Auto-generated constructor stub
	}
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	
	@ModelAttribute("task")
	public Task user() {
		return new Task();
	}
	
	@GetMapping
	public String showRegistrationForm(@PathVariable("id") long id,Model mv) {
		System.out.println("path variable isss **********************: "+id);
		List<Task> tasks=taskrepo.findByProjectId(id);
		List<Task> todo=new ArrayList<Task>();
		List<Task> doing=new ArrayList<Task>();
		List<Task> done=new ArrayList<Task>();
		for(Task task:tasks) {
			if((task.getStatus()).equals("Todo")) {
				todo.add(task);
			}
			if((task.getStatus()).equals("Doing")) {
				doing.add(task);
			}
			if((task.getStatus()).equals("Done")) {
				done.add(task);
			}
		}
		
		mv.addAttribute("todo", todo);
		mv.addAttribute("doing", doing);
		mv.addAttribute("done", done);
		return "viewtask";
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("projectName") String  projectName, Principal principal) {
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
		
		projectrepo.save(project);
		return "redirect:/viewtask";
	}


}
