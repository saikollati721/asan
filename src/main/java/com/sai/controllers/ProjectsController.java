package com.sai.controllers;

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

import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@Controller
public class ProjectsController {

	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;

	
	@ModelAttribute("task")
	public Task task() {
		return new Task();
	}
	
	@GetMapping("/viewproject/{id}")
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
		mv.addAttribute("projectId", id);
		mv.addAttribute("todo", todo);
		mv.addAttribute("doing", doing);
		mv.addAttribute("done", done);
		return "viewproject";
	}
	
	@PostMapping("/viewproject/{id}")
	public String registerUserAccount(@PathVariable("id") long id,@ModelAttribute("projectName") String  projectName, Principal principal) {
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
		
		projectrepo.save(project);
		
		return "redirect:/viewproject/{id}";
	}
	
	
	@ModelAttribute("project")
	public Project project() {
		return new Project();
	}
	
	@GetMapping("/createproject")
	public String showRegistrationForm() {
		return "createproject";
	}
	
	@PostMapping("/createproject")
	public String createProject(@ModelAttribute("projectName") String  projectName, Principal principal) {
		User user=userrepo.findByEmail(principal.getName());
		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
		
		projectrepo.save(project);
		return "redirect:/";
	}
	
	
	@GetMapping("/deleteproject/{id}")
	public String deleteProject(@PathVariable("id") long id,Model mv) {
		System.out.println("path variable isss **********************: "+id);
		projectrepo.deleteById(id);
		List<AssignedProject> assignProject = assignedProjectRepo.findByProjectId(id);
		
		List<Task> tasks=taskrepo.findByProjectId(id);
		taskrepo.deleteAll(tasks);
		assignedProjectRepo.deleteAll(assignProject);
		
		
		return "redirect:/";
	}


}
