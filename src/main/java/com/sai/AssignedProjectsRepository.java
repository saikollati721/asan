package com.sai;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedProjectsRepository extends JpaRepository<AssignedProjects, Long> {
//	@Query("FROM AssignedProjects where userId= :userId")
	public List<AssignedProjects> findByUserId(@Param(value="userId") long id);
}
