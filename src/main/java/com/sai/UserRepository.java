package com.sai;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT new com.sai.JoinInfo(email,password,role) FROM User where email= :email")
	public JoinInfo getOnlyUserInfo(@Param("email") String email);
	
//	@Modifying
//	@Query()
//	public User updateOnlyUser();
	
	

	public User findByEmail(String username);
	
	@Query("SELECT new com.sai.Project(projectId,projectName,createdBy) FROM Project where userId= :userId")
	public List<Project> getProjectByUserId(@Param("userId") long userId);	
}

