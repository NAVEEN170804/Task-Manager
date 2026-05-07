package com.example.taskmanager.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.taskmanager.entity.Project;

public interface ProjectRepo extends JpaRepository<Project, Long> {
	@Query("SELECT p FROM Project p WHERE p.createdBy.id = :userId OR EXISTS (SELECT m FROM p.members m WHERE m.id = :userId)")
	List<Project> findProjectsByUserId(@Param("userId") Long userId);
}