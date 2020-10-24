package com.sai.api.requests;

import java.util.Date;

public class ProjectRequest {


	private String projectName;
	private String createdBy;
	private long userId;
	
	public ProjectRequest() {
		// TODO Auto-generated constructor stub
	}

	

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
