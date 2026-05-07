package com.example.taskmanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.taskmanager.dao.*;
import com.example.taskmanager.dto.ProjectRequest;
import com.example.taskmanager.entity.*;
import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepo projectRepo;

    @Autowired
    private UserRepo userRepo;

    public Project createProject(ProjectRequest request) {
        User user = userRepo.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Project project = new Project();
        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setDueDate(request.getDueDate());
        project.setPriority(request.getPriority());
        project.setCreatedBy(user);

        return projectRepo.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project updateProject(Long id, ProjectRequest request) {
        Project project = projectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        project.setName(request.getName());
        project.setDescription(request.getDescription());
        project.setDueDate(request.getDueDate());
        project.setPriority(request.getPriority());

        return projectRepo.save(project);
    }

    public void deleteProject(Long id) {
        projectRepo.deleteById(id);
    }

    public Project assignUser(Long projectId, Long userId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!project.getMembers().contains(user)) {
            project.getMembers().add(user);
        }
        return projectRepo.save(project);
    }
 // Add this method to ProjectService.java
    public List<Project> getProjectsByUser(Long userId) {
        return projectRepo.findProjectsByUserId(userId);
    }
}
