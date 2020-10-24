package com.sai.api.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class ProjectDto {

	

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	private Long id;
	private String projectName;
	private String createdBy;
	private long userId;
	private String createdAt;
	
	public ProjectDto() {
		// TODO Auto-generated constructor stub
	}

	public ProjectDto(Long id, String projectName, String createdBy, long userId) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.createdBy = createdBy;
		this.userId = userId;
	}
	
	
	 public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public void setSubmissionDate(Date date, String timezone) {
		  System.out.println("in dto function *************** : "+date.getClass());
	      dateFormat.setTimeZone(TimeZone.getTimeZone(timezone));
	      System.out.println("in dto function  after *************** : "+date);
	      this.createdAt = dateFormat.format(date);
	  }
}
