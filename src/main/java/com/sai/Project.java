package com.sai;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="projects")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	private String projectName;
	private String createdBy;
	private Date createdAt;
	private long userId;

//	@OneToMany(targetEntity = Task.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinColumn(name="projectId", referencedColumnName = "projectId")
//	private List<Task> tasks;

	

	public long getUserId() {
		return userId;
	}

	public Project(String projectName, String createdBy, Date createdAt, long userId) {
		super();
		this.projectName = projectName;
		this.createdBy = createdBy;
		this.createdAt = createdAt;
		this.userId = userId;
	}

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	
	
//	public List<Task> getTasks() {
//		return tasks;
//	}
//
//	public void setTasks(List<Task> tasks) {
//		this.tasks = tasks;
//	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
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

	
}
