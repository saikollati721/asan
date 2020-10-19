package com.sai;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Api {
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedprojects;

	@GetMapping("/hello")
	public AssignedProjects hello() {
		System.out.println("get users request recieved");
		return assignedprojects.findByUserId(1);
	}

}
