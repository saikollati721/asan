package com.sai;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class Api {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;

	@GetMapping(path="/users/getAllUsers")
	@ResponseBody
	public ResponseEntity<List<User>> getAllUsers() {
		return new ResponseEntity<List<User>> (userrepo.findAll(),HttpStatus.OK);

	}
	
	@PostMapping(path="/users/setUser", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<User> setUser(@RequestBody User user) {
		return new ResponseEntity<User> (userrepo.save(user),HttpStatus.CREATED);
	}

	
	@GetMapping(path="/projects/getAllProjects")
	@ResponseBody
	public ResponseEntity<List<Project>> getAllProjects() {
		return new ResponseEntity<List<Project>> (projectrepo.findAll(),HttpStatus.OK);
	}
	
	@PostMapping(value="/projects/setProject", consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Project> setProject(@RequestBody Project project) {
		return new ResponseEntity<Project> (projectrepo.save(project),HttpStatus.CREATED);
	}
	
	@GetMapping(value="/projects/deleteProjectByProjectId/{id}")
	@ResponseBody
	public ResponseEntity deleteProjectByProjectId(@PathVariable Long id) {
		Project project=projectrepo.findByProjectId(id);
		if(project!=null)
		{
			projectrepo.deleteById(id);
			List<AssignedProject> assignedprojects=assignedProjectRepo.findByProjectId(id);
			assignedProjectRepo.deleteAll(assignedprojects);
			List<Task> tasks=taskrepo.findByProjectId(id);
			taskrepo.deleteAll(tasks);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/projects/getProjectByUserId/{id}")
	public ResponseEntity<List<Project>> getProjectByUserId(@PathVariable Long id){
		List<Project> project= projectrepo.findByUserId(id);
		if(project.size()!=0)
			return new ResponseEntity<List<Project>>(project,HttpStatus.OK);
		return new ResponseEntity<List<Project>>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/projects/getProjectByProjectId/{id}")
	@ResponseBody
	public ResponseEntity<Project> getProjectByProjectId(@PathVariable Long id) {
		
		Project project=projectrepo.findByProjectId(id);
		if(project!=null)
			return new ResponseEntity<Project>(project,HttpStatus.OK);
		return new ResponseEntity<Project>(HttpStatus.NOT_FOUND);
		
	}
	
	@GetMapping("/tasks/getAllTasks")
	@ResponseBody
	public ResponseEntity<List<Task>> getAllTasks() {
		return new ResponseEntity<List<Task>> (taskrepo.findAll(),HttpStatus.OK);
	}
	@PostMapping("/tasks/setTask")
	@ResponseBody
	public ResponseEntity<Task> setTask(@RequestBody Task req) {
		return new ResponseEntity<Task> (taskrepo.save(req),HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/tasks/getTaskByProjectId/{id}")
	public ResponseEntity<List<Task>> getTaskByProjectId(@PathVariable Long id){
		List<Task> task= taskrepo.findByProjectId(id);
		if(task.size()!=0)
			return new ResponseEntity<List<Task>>(task,HttpStatus.OK);
		return new ResponseEntity<List<Task>>(HttpStatus.NOT_FOUND);
	}
	
	
	@GetMapping("/assignedprojects/getAllAssignedProjects")
	@ResponseBody
	public ResponseEntity<List<AssignedProject>> getAllAssignedProjects() {
		return new ResponseEntity<List<AssignedProject>> (assignedProjectRepo.findAll(),HttpStatus.OK);

	}
	
	@PostMapping("/assignedprojects/setAssignedProject")
	@ResponseBody
	public ResponseEntity<AssignedProject> setAssignedProject(@RequestBody AssignedProject req) {
		return new ResponseEntity<AssignedProject> (assignedProjectRepo.save(req),HttpStatus.CREATED);
	}
	
	@GetMapping("/assignedprojects/getAssignedProjectByProjectId/{id}")
	public ResponseEntity<List<AssignedProject>> getAssignedProjectByProjectId(@PathVariable Long id){
		List<AssignedProject> assignedproject= assignedProjectRepo.findByProjectId(id);
		if(assignedproject.size()!=0)
			return new ResponseEntity<List<AssignedProject>>(assignedproject,HttpStatus.OK);
		return new ResponseEntity<List<AssignedProject>>(HttpStatus.NOT_FOUND);
	}
	
}
