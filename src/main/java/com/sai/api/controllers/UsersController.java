package com.sai.api.controllers;

import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sai.api.dto.UserDto;
import com.sai.api.requests.UserRequest;
import com.sai.model.User;
import com.sai.repository.ProjectRepository;
import com.sai.repository.TaskRepository;
import com.sai.repository.UserRepository;
import com.sai.services.SecurityService;

@RestController
@CrossOrigin
//@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping()
public class UsersController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private ProjectRepository projectrepo;
	
	@Autowired
	private TaskRepository taskrepo;

	
	@Autowired
    private ModelMapper modelMapper;

	
	@Autowired
    private SecurityService securityService;

	
	
	@GetMapping(path="/users")
	@ResponseBody
	public ResponseEntity getAllUsers(Principal principal) {
//		System.out.println("in users page ************************** : "+principal.getName());
		List<UserDto> usersDto =new ArrayList<UserDto>();
		List<User> users=userRepo.findAll();
		for(User user : users) {
//			System.out.println("in list users function *************** : "+user.getCreatedAt().getClass());
			UserDto response=new UserDto(user.getId(), user.getEmail(), user.getPassword(),user.getAbout(),
				user.getFirstName(),user.getImageUrl());
			response.setSubmissionDate(user.getCreatedAt() ,"asia");
			usersDto.add(response);
		}
		return ResponseEntity.status(HttpStatus.OK).body(usersDto);

	}
	
	@PostMapping(path="/users")
	@ResponseBody
	public ResponseEntity<Object> setUser(@RequestBody UserRequest user) {
		
		System.out.println("******************** save user called");
//		user.setId((new User()).getId());
		
		User newuser=modelMapper.map(user, User.class);
		return ResponseEntity.status(HttpStatus.CREATED).body(userRepo.save(newuser));
	}
	
	
	@GetMapping(path="/users/{id}")
	@ResponseBody
	public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
		Optional<User> user=userRepo.findById(id);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
		UserDto response= new UserDto(user.get().getId(), user.get().getEmail(), user.get().getPassword(),user.get().getAbout(),
				user.get().getFirstName(),user.get().getImageUrl());
//		System.out.println("in controller function *************** : "+user.get().getCreatedAt().getClass());
		response.setSubmissionDate(user.get().getCreatedAt() ,"asia");
		return ResponseEntity.status(HttpStatus.OK).body(response);

	}

}
