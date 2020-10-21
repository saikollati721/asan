package com.sai.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@RestController
@RequestMapping()
public class ProjectsController {

	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;


	@GetMapping("/projects")
	@ResponseBody
	public ResponseEntity<List<Project>> getAllProjects() {
		return ResponseEntity.status(HttpStatus.OK).body(projectrepo.findAll());
	}
	
	@PostMapping("/projects")
	@ResponseBody
	public ResponseEntity<Project> setProject(@RequestBody Project project) {
		return ResponseEntity.status(HttpStatus.CREATED).body(projectrepo.save(project));
	}
	
	@GetMapping("/projects/{id}")
	@ResponseBody
	public ResponseEntity getProjectByProjectId(@PathVariable Long id) {
		Map<String, String> message= new HashMap<String, String>();
		Project project=projectrepo.findByProjectId(id);
		if(project!=null)
			return ResponseEntity.status(HttpStatus.OK).body(project);
		message.put("status","error");
		message.put("message", "No record found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		
	}
	
	@DeleteMapping("/projects/{id}")
	@ResponseBody
	public ResponseEntity deleteProjectByProjectId(@PathVariable Long id) {
		Project project=projectrepo.findByProjectId(id);
		 Map<String, String> message= new HashMap<String, String>();
		if(project!=null)
		{
			projectrepo.deleteById(id);
			List<AssignedProject> assignedprojects=assignedProjectRepo.findByProjectId(id);
			assignedProjectRepo.deleteAll(assignedprojects);
			List<Task> tasks=taskrepo.findByProjectId(id);
			taskrepo.deleteAll(tasks);
			message.put("status","success");
			message.put("message", "Project deleted successfully");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}
		message.put("status","error");
		message.put("message", "No record found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	
	
	
//	@ModelAttribute("task")
//	public Task task() {
//		return new Task();
//	}
//	
//	@GetMapping("/viewproject/{id}")
//	public String showRegistrationForm(@PathVariable("id") long id,Model mv) {
//		System.out.println("path variable isss **********************: "+id);
//		List<Task> tasks=taskrepo.findByProjectId(id);
//		List<Task> todo=new ArrayList<Task>();
//		List<Task> doing=new ArrayList<Task>();
//		List<Task> done=new ArrayList<Task>();
//		for(Task task:tasks) {
//			if((task.getStatus()).equals("Todo")) {
//				todo.add(task);
//			}
//			if((task.getStatus()).equals("Doing")) {
//				doing.add(task);
//			}
//			if((task.getStatus()).equals("Done")) {
//				done.add(task);
//			}
//		}
//		mv.addAttribute("projectId", id);
//		mv.addAttribute("todo", todo);
//		mv.addAttribute("doing", doing);
//		mv.addAttribute("done", done);
//		return "viewproject";
//	}
//	
//	@PostMapping("/viewproject/{id}")
//	public String registerUserAccount(@PathVariable("id") long id,@ModelAttribute("projectName") String  projectName, Principal principal) {
//		User user=userrepo.findByEmail(principal.getName());
//		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
//		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
//		
//		projectrepo.save(project);
//		
//		return "redirect:/viewproject/{id}";
//	}
//	
//	
//	@ModelAttribute("project")
//	public Project project() {
//		return new Project();
//	}
//	
//	@GetMapping("/createproject")
//	public String showRegistrationForm() {
//		return "createproject";
//	}
//	
//	@PostMapping("/createproject")
//	public String createProject(@ModelAttribute("projectName") String  projectName, Principal principal) {
//		User user=userrepo.findByEmail(principal.getName());
//		System.out.println("**************************   user is : "+user.getFirstName()+"  id : "+user.getId());
//		Project project=new Project(projectName,user.getFirstName(),new Date(),user.getId());
//		
//		projectrepo.save(project);
//		return "redirect:/";
//	}
//	
//	
//	@GetMapping("/deleteproject/{id}")
//	public String deleteProject(@PathVariable("id") long id,Model mv) {
//		System.out.println("path variable isss **********************: "+id);
//		projectrepo.deleteById(id);
//		List<AssignedProject> assignProject = assignedProjectRepo.findByProjectId(id);
//		
//		List<Task> tasks=taskrepo.findByProjectId(id);
//		taskrepo.deleteAll(tasks);
//		assignedProjectRepo.deleteAll(assignProject);
//		
//		
//		return "redirect:/";
//	}


}
