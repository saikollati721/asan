package com.sai.api.requests;

public class UserProjectRequest {

	private Long userId;
	public Long projectId;
	private String assignedBy;
	
	public UserProjectRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

//	public Long getProjectId() {
//		return projectId;
//	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(String assignedBy) {
		this.assignedBy = assignedBy;
	}

}
