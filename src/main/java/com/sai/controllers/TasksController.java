package com.sai.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.AssignedProject;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@RestController
@CrossOrigin
@RequestMapping()
public class TasksController {

	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;

	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;

	@GetMapping("/tasks")
	@ResponseBody
	public ResponseEntity<List<Task>> getAllTasks() {
		return ResponseEntity.status(HttpStatus.OK).body(taskrepo.findAll());
	}
	@PostMapping("/tasks")
	@ResponseBody
	public ResponseEntity<Task> setTask(@RequestBody Task req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskrepo.save(req));
	}
	
	
	
	@GetMapping("/tasks/{projectId}")
	public ResponseEntity getTaskByProjectId(@PathVariable Long projectId){
		Map<String, String> message= new HashMap<String, String>();
		List<Task> task= taskrepo.findByProjectId(projectId);
		if(task.size()!=0)
			return ResponseEntity.status(HttpStatus.OK).body(task);
		message.put("status","error");
		message.put("message", "No record found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
	

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity updateTaskByProjectId(@RequestBody Task req,@PathVariable Long taskId){
		Map<String, String> message= new HashMap<String, String>();
		Optional<Task> task= taskrepo.findById(taskId);
		req.setId(taskId);
		if(task!=null)
		{
			taskrepo.deleteById(taskId);
			taskrepo.save(req);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(task);
		}
		message.put("status","error");
		message.put("message", "No record found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
//	public TasksController() {
//		// TODO Auto-generated constructor stub
//	}
//	
//	@ModelAttribute("task")
//	public Task task() {
//		return new Task();
//	}
//	
//	@GetMapping("/createtask/{id}")
//	public String createtask(@PathVariable("id") long id,Model mv) {
//		mv.addAttribute("projectId",id);
//		return "createtask";
//	}
//	
//	@PostMapping("/createtask/{projectId}")
//	public String createtaskinsert(@PathVariable("projectId") long projectId,Model mv,@ModelAttribute("task") Task task, Principal principal) {
//		mv.addAttribute("projectId",projectId);
//		int length=taskrepo.findAll().size();
//		task.setId(length+1);
//		task.setProjectId(projectId);
//		taskrepo.save(task);
//		
//		
//		User user=userrepo.findByEmail(principal.getName());
//		mv.addAttribute("user",user);
//		
//		System.out.println("*****************  project id :"+projectId);
//		
//		assignedProjectRepo.save(new AssignedProject(task.getAssignId(),projectId,user.getFirstName()));
//		
//		System.out.println("*****************  project id after  :"+projectId);
//		
//		return "redirect:/viewproject/{projectId}";
//	}
//
//	@GetMapping("/edittask/{projectId}/{taskId}")
//	public String edittasktask(@PathVariable("taskId") long taskId, @PathVariable("projectId") long projectId,Model mv) {
//		Task task=taskrepo.findById(taskId);
//		task.setProjectId(projectId);
//		mv.addAttribute("task",task);
//		return "edittask";
//	}
//	
//	@PostMapping("/edittask/{projectId}/{taskId}")
//	public String edittasktaskupdate(@PathVariable("taskId") long taskId,Model mv,@PathVariable("projectId") long projectId,@ModelAttribute("task") Task task, Principal principal) {
//		
//		taskrepo.deleteById(taskId);
//		task.setId(taskId);
//		
//		taskrepo.save(task);
//		
//		return "redirect:/viewproject/{projectId}";
//	}
}
