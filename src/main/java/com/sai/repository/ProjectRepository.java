package com.sai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	public List<Project> findByUserId(long id);
	public Project findByProjectId(long id);
}
