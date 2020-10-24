package com.sai.api.controllers;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
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

import com.sai.api.dto.TaskDto;
import com.sai.api.requests.TaskRequest;
import com.sai.exception.TaskNotfoundException;
import com.sai.model.AssignedProject;
import com.sai.model.Project;
import com.sai.model.Task;
import com.sai.model.User;
import com.sai.repository.AssignedProjectsRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping()
public class TaskController {

	
	
	@Autowired
	private TaskRepository taskrepo;

	@Autowired
	private AssignedProjectsRepository assignedProjectRepo;
	
	@Autowired
    private ModelMapper modelMapper;

	

	@GetMapping("/tasks")
	@ResponseBody
	public ResponseEntity<List<TaskDto>> getAllTasks() {
		List<Task> tasks= taskrepo.findAll();
		List<TaskDto> response=new ArrayList<TaskDto>();
		for(Task task:tasks) {
			TaskDto taskDto=new TaskDto(task.getId(),task.getTaskName(),task.getTaskStatus(),task.getStatus(),task.getDue(),task.getPriority()
					,task.getProjectId(), task.getAssignId());
			response.add(taskDto);
		}
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	@PostMapping("/tasks")
	@ResponseBody
	public ResponseEntity<Task> setTask(@RequestBody TaskRequest req) {
		Task task=modelMapper.map(req, Task.class);
		task.setProjectId(req.projectId);
		return ResponseEntity.status(HttpStatus.CREATED).body(taskrepo.save(task));
	}
	
	
	
	@GetMapping("/tasks/{projectId}")
	public ResponseEntity getTaskByProjectId(@PathVariable Long projectId){
		Map<String, String> message= new HashMap<String, String>();
		List<Task> tasks= taskrepo.findByProjectId(projectId);
		List<TaskDto> response=new ArrayList<TaskDto>();
		if(tasks.size()!=0)
		{
			for(Task task:tasks) {
				TaskDto taskDto=new TaskDto(task.getId(),task.getTaskName(),task.getTaskStatus(),task.getStatus(),task.getDue(),task.getPriority()
						,task.getProjectId(), task.getAssignId());
				response.add(taskDto);
			}
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}
			
//		message.put("status","error");
//		message.put("message", "No record found");
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		throw new TaskNotfoundException();
	}
	
	

	@PutMapping("/tasks/{taskId}")
	public ResponseEntity updateTaskByTaskId(@RequestBody Task req,@PathVariable Long taskId){
		Map<String, String> message= new HashMap<String, String>();
		Optional<Task> task= Optional.ofNullable(taskrepo.findById(taskId).orElseThrow(() -> new TaskNotfoundException()));
		req.setId(taskId);
		if(task!=null)
		{
			taskrepo.deleteById(taskId);
			taskrepo.save(req);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body("updated sucessfully");
		}
		return null;
		
	}

}
