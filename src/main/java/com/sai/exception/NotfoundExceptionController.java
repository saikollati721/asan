package com.sai.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotfoundExceptionController {

	public NotfoundExceptionController() {
		// TODO Auto-generated constructor stub
	}
	
	@ExceptionHandler(value = TaskNotfoundException.class)
   public ResponseEntity taskException(TaskNotfoundException exception) {
		Map<String, String> message= new HashMap<String, String>();
		message.put("message", "No task found with given id");
		message.put("status","error");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
   }
	
	
	@ExceptionHandler(value= ProjectNotfoundException.class)
	   public ResponseEntity projectException(ProjectNotfoundException exception) {
			Map<String, String> message= new HashMap<String, String>();
			message.put("message", "No project found with given id");
			message.put("status","error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	   }
	
	@ExceptionHandler(value= UserProjectNotfoundException.class)
	   public ResponseEntity userProjectException(UserProjectNotfoundException exception) {
			Map<String, String> message= new HashMap<String, String>();
			message.put("message", "No user project found with given user id");
			message.put("status","error");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	   }

}
