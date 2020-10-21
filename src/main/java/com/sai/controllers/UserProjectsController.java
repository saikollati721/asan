package com.sai.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.model.AssignedProject;
import com.sai.repository.AssignedProjectsRepository;

@RestController
@CrossOrigin
@RequestMapping()
public class UserProjectsController {

	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;
	
	public UserProjectsController() {
		// TODO Auto-generated constructor stub
	}

	
	@GetMapping("/user_projects")
	@ResponseBody
	public ResponseEntity<List<AssignedProject>> getAllAssignedProjects() {
		return ResponseEntity.status(HttpStatus.OK).body(assignedProjectRepo.findAll());

	}
	
	@PostMapping("/user_projects")
	@ResponseBody
	public ResponseEntity<AssignedProject> setAssignedProject(@RequestBody AssignedProject req) {
		return ResponseEntity.status(HttpStatus.CREATED).body(assignedProjectRepo.save(req));
	}
	
	@GetMapping("user_projects/{userId}")
	public ResponseEntity getAssignedProjectByProjectId(@PathVariable Long userId){
		Map<String, String> message= new HashMap<String, String>();
		List<AssignedProject> assignedproject= assignedProjectRepo.findByUserId(userId);
		if(assignedproject.size()!=0)
			return ResponseEntity.status(HttpStatus.OK).body(assignedproject);
		message.put("status","error");
		message.put("message", "No record found");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	}
	
}
