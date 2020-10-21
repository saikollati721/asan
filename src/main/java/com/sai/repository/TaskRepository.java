package com.sai.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sai.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	public List<Task> findByProjectId(long id);
	public Task findById(long id);
}
