package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.taskmanager.dto.ProjectRequest;
import com.example.taskmanager.entity.*;
import com.example.taskmanager.service.*;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // List all projects
 // Replace the listProjects method in ProjectController.java
    @GetMapping
    public String listProjects(Model model, Authentication auth) {
        User loggedUser = userService.getUserByEmail(auth.getName());
        
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
        
        List<Project> projects = isAdmin
                ? projectService.getAllProjects()
                : projectService.getProjectsByUser(loggedUser.getId());
        
        model.addAttribute("projects", projects);
        return "projects/list";
    }

    // Show create project form (ADMIN only)
    @GetMapping("/add")
    public String showCreateForm(Model model, Authentication auth) {
        User loggedUser = userService.getUserByEmail(auth.getName());
        model.addAttribute("projectRequest", new ProjectRequest());
        model.addAttribute("priorities", Priority.values());
        model.addAttribute("loggedUserId", loggedUser.getId());
        return "projects/form";
    }

    // Handle create project (ADMIN only)
    @PostMapping("/add")
    public String createProject(@ModelAttribute ProjectRequest projectRequest,
                                RedirectAttributes redirectAttributes) {
        try {
            projectService.createProject(projectRequest);
            redirectAttributes.addFlashAttribute("success", "Project created successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    // View single project detail
    @GetMapping("/{id}")
    public String viewProject(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        model.addAttribute("project", project);
        model.addAttribute("allUsers", userService.getAllUsers());
        return "projects/detail";
    }

    // Show edit form (ADMIN only)
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Project project = projectService.getProjectById(id);
        ProjectRequest req = new ProjectRequest();
        req.setName(project.getName());
        req.setDescription(project.getDescription());
        req.setDueDate(project.getDueDate());
        req.setPriority(project.getPriority());
        model.addAttribute("projectRequest", req);
        model.addAttribute("projectId", id);
        model.addAttribute("priorities", Priority.values());
        return "projects/edit";
    }

    // Handle edit
    @PostMapping("/edit/{id}")
    public String updateProject(@PathVariable Long id,
                                @ModelAttribute ProjectRequest projectRequest,
                                RedirectAttributes redirectAttributes) {
        try {
            projectService.updateProject(id, projectRequest);
            redirectAttributes.addFlashAttribute("success", "Project updated successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    // Delete project (ADMIN only)
    @PostMapping("/delete/{id}")
    public String deleteProject(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            projectService.deleteProject(id);
            redirectAttributes.addFlashAttribute("success", "Project deleted.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/projects";
    }

    // Assign user to project
    @PostMapping("/{projectId}/assign")
    public String assignUser(@PathVariable Long projectId,
                             @RequestParam Long userId,
                             RedirectAttributes redirectAttributes) {
        try {
            projectService.assignUser(projectId, userId);
            redirectAttributes.addFlashAttribute("success", "User assigned to project!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/projects/" + projectId;
    }
}
