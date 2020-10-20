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
@RequestMapping("/deleteproject/{id}")
public class DeleteProjectController {


	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
	private TaskRepository taskrepo;
	
	
	@ModelAttribute("task")
	public Project user() {
		return new Project();
	}
	
	@GetMapping
	public String showRegistrationForm(@PathVariable("id") long id,Model mv) {
		System.out.println("path variable isss **********************: "+id);
		projectrepo.deleteById(id);
		return "redirect:/";
	}
	
}
