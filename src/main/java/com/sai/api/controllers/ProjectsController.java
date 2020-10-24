package com.sai.api.controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.api.dto.ProjectDto;
import com.sai.api.requests.ProjectRequest;
import com.sai.exception.ProjectNotfoundException;
import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin
@RequestMapping()
public class ProjectsController {

	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    private ModelMapper modelMapper;

	@Autowired
	private TaskRepository taskrepo;
	
	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;


	@GetMapping("/projects")
	@ResponseBody
	public ResponseEntity<List<ProjectDto>> getAllProjects() {
		List<Project> projects=projectrepo.findAll();
		List<ProjectDto> projectDto=  new ArrayList<ProjectDto>();
		for(Project pro:projects) {
			ProjectDto response=new ProjectDto(pro.getProjectId(),pro.getProjectName(),pro.getCreatedBy(),pro.getUserId());
			response.setSubmissionDate(pro.getCreatedAt(), "asia");
			projectDto.add(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(projectDto);
	}
	
	@PostMapping("/projects")
	@ResponseBody
	public ResponseEntity<Project> setProject(@RequestBody ProjectRequest project) {
//		System.out.println("******************** save project called");
		Project response = modelMapper.map(project, Project.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(projectrepo.save(response));
	}
	
	@GetMapping("/projects/{id}")
	@ResponseBody
	public ResponseEntity getProjectByUserId(@PathVariable Long id) {
		Map<String, String> message= new HashMap<String, String>();
		List<Project> projects=projectrepo.findByUserId(id);
		List<ProjectDto> projectDto=  new ArrayList<ProjectDto>();
		for(Project pro:projects) {
			ProjectDto response=new ProjectDto(pro.getProjectId(),pro.getProjectName(),pro.getCreatedBy(),pro.getUserId());
			response.setSubmissionDate(pro.getCreatedAt(), "asia");
			projectDto.add(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(projectDto);

		
	}
	

	
	@DeleteMapping("/projects/{id}")
	@ResponseBody
	public ResponseEntity deleteProjectByProjectId(@PathVariable Long id) {
		Project project=projectrepo.findByProjectId(id);
		 Map<String, String> message= new HashMap<String, String>();
		if(project!=null)
		{
			System.out.println("deleting project"+project.getProjectName());
			projectrepo.deleteById(id);
			List<AssignedProject> assignedprojects=assignedProjectRepo.findByProjectId(id);
			System.out.println("deleting project after assigned project");
			if(assignedprojects.size()!=0)
			{
				assignedProjectRepo.deleteAll(assignedprojects);
				System.out.println("deleted assigned porjets sucesfully");
			}
			List<Task> tasks=taskrepo.findByProjectId(id);
			System.out.println("deleting project after tasks");
			if(tasks.size()!=0)
			{
				taskrepo.deleteAll(tasks);
				System.out.println("deleted tasks sucesfully");

			}
			message.put("status","success");
			message.put("message", "Project deleted successfully");
			return ResponseEntity.status(HttpStatus.OK).body(message);
		}

		
		throw new ProjectNotfoundException();
	}

}
