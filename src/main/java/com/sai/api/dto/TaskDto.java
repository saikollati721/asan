package com.sai.api.dto;

public class TaskDto {

	private Long id;
		
	private String taskName;
	private String taskStatus;
	private String status;
	private String due;
	private String priority;

	public Long projectId;
	public TaskDto(Long id, String taskName, String taskStatus, String status, String due, String priority,
			Long projectId, Long assignId) {
		super();
		this.id = id;
		this.taskName = taskName;
		this.taskStatus = taskStatus;
		this.status = status;
		this.due = due;
		this.priority = priority;
		this.projectId = projectId;
		this.assignId = assignId;
	}


	private Long assignId;
	
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getTaskStatus() {
		return taskStatus;
	}


	public void setTaskStatus(String taskStatus) {
		this.taskStatus = taskStatus;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getDue() {
		return due;
	}


	public void setDue(String due) {
		this.due = due;
	}


	public String getPriority() {
		return priority;
	}


	public void setPriority(String priority) {
		this.priority = priority;
	}


	public Long getProjectId() {
		return projectId;
	}


	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}


	public Long getAssignId() {
		return assignId;
	}


	public void setAssignId(Long assignId) {
		this.assignId = assignId;
	}


	public TaskDto() {
		// TODO Auto-generated constructor stub
	}

}
