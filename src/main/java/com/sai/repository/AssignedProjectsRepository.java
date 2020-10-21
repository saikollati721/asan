package com.sai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sai.model.AssignedProject;

@Repository
public interface AssignedProjectsRepository extends JpaRepository<AssignedProject, Long> {
//	@Query("FROM AssignedProjects where userId= :userId")
	public List<AssignedProject> findByUserId(@Param(value="userId") long id);
	
	public List<AssignedProject> findByProjectId(long id);
}
