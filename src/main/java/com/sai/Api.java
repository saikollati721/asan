package com.sai;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;

//	@GetMapping("/hello")
//	public Optional<Project> hello() {
//		System.out.println("get users request recieved");
//		Long id = assignedProjectRepo.findByUserId(2).getProjectId();
//		return projectrepo.findById(id);
//	}
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userrepo.findAll();
	}
	
	@PostMapping(value="/setUser", consumes = "application/json")
	public User setUser(@RequestBody User user) {
		return userrepo.save(user);
	}

	
	@GetMapping("/getAllProjects")
	public List<Project> getAllProjects() {
		return projectrepo.findAll();
	}
	
	@PostMapping(value="/setProject", consumes = "application/json")
	public Project setProject(@RequestBody Project project) {
		return projectrepo.save(project);
	}
	
	
	@GetMapping("/getAllTasks")
	public List<Task> getAllTasks() {
		return taskrepo.findAll();
	}
	@PostMapping("/setTask")
	public Task setTask(@RequestBody Task req) {
		return taskrepo.save(req);
	}
	
	@GetMapping("/getAllAssignedProjects")
	public List<AssignedProjects> getAllAssignedProjects() {
		return assignedProjectRepo.findAll();
	}
	@PostMapping("/setAssignedProject")
	public AssignedProjects setAssignedProject(@RequestBody AssignedProjects req) {
		return assignedProjectRepo.save(req);
	}
	
	@GetMapping("/getProjectByUserId")
	public List<Project> getProjectByUserId(){
		return projectrepo.findByUserId(1);
		
	}
	
	@GetMapping("/getProjectByProjectId")
	public Project getProjectByProjectId() {
		return projectrepo.findByProjectId(1);
	}
	
	@GetMapping("/getTaskByProjectId")
	public List<Task> getTaskByProjectId(){
		return taskrepo.findByProjectId(1);
	}
	
}
