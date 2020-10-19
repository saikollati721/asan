package com.sai;

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
@Table(name="project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long projectId;
	private String projectName;
	private String createdBy;
	

	@OneToMany(targetEntity = Task.class, fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="projectId", referencedColumnName = "projectId")
	private List<Task> tasks;

	

	public Project() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Project(long projectId, String projectName, String createdBy) {
		super();
		this.projectId = projectId;
		this.projectName = projectName;
		this.createdBy = createdBy;
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

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
