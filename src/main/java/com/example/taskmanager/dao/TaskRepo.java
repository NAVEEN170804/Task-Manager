package com.example.taskmanager.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanager.entity.Task;
import java.util.List;

public interface TaskRepo extends JpaRepository<Task, Long> {
    List<Task> findByProjectId(Long projectId);
    List<Task> findByAssignedToId(Long userId);
}
