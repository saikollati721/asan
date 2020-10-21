package com.sai.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.sai.model.AssignedProject;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@Controller
public class TasksController {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;

	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;

	
	public TasksController() {
		// TODO Auto-generated constructor stub
	}
	
	@ModelAttribute("task")
	public Task task() {
		return new Task();
	}
	
	@GetMapping("/createtask/{id}")
	public String createtask(@PathVariable("id") long id,Model mv) {
		mv.addAttribute("projectId",id);
		return "createtask";
	}
	
	@PostMapping("/createtask/{projectId}")
	public String createtaskinsert(@PathVariable("projectId") long projectId,Model mv,@ModelAttribute("task") Task task, Principal principal) {
		mv.addAttribute("projectId",projectId);
		int length=taskrepo.findAll().size();
		task.setId(length+1);
		task.setProjectId(projectId);
		taskrepo.save(task);
		
		
		User user=userrepo.findByEmail(principal.getName());
		mv.addAttribute("user",user);
		
		System.out.println("*****************  project id :"+projectId);
		
		assignedProjectRepo.save(new AssignedProject(task.getAssignId(),projectId,user.getFirstName()));
		
		System.out.println("*****************  project id after  :"+projectId);
		
		return "redirect:/viewproject/{projectId}";
	}

	@GetMapping("/edittask/{projectId}/{taskId}")
	public String edittasktask(@PathVariable("taskId") long taskId, @PathVariable("projectId") long projectId,Model mv) {
		Task task=taskrepo.findById(taskId);
		task.setProjectId(projectId);
		mv.addAttribute("task",task);
		return "edittask";
	}
	
	@PostMapping("/edittask/{projectId}/{taskId}")
	public String edittasktaskupdate(@PathVariable("taskId") long taskId,Model mv,@PathVariable("projectId") long projectId,@ModelAttribute("task") Task task, Principal principal) {
		
		taskrepo.deleteById(taskId);
		task.setId(taskId);
		
		taskrepo.save(task);
		
		return "redirect:/viewproject/{projectId}";
	}
}
