package com.example.taskmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.entity.*;
import com.example.taskmanager.service.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    // List all tasks
 // Replace the listTasks method in TaskController.java
    @GetMapping
    public String listTasks(Model model, Authentication auth) {
        User loggedUser = userService.getUserByEmail(auth.getName());

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Task> tasks = isAdmin
                ? taskService.getAllTasks()
                : taskService.getTasksByUser(loggedUser.getId());

        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    // Show create task form (ADMIN only)
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("taskRequest", new TaskRequest());
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("statuses", Status.values());
        return "tasks/form";
    }

    // Handle create task (ADMIN only)
    @PostMapping("/create")
    public String createTask(@ModelAttribute TaskRequest taskRequest,
                             RedirectAttributes redirectAttributes) {
        try {
            taskService.createTask(taskRequest);
            redirectAttributes.addFlashAttribute("success", "Task created successfully!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        TaskRequest req = new TaskRequest();
        req.setTitle(task.getTitle());
        req.setDescription(task.getDescription());
        req.setDueDate(task.getDueDate());
        req.setStatus(task.getStatus());
        req.setProjectId(task.getProject().getId());
        req.setUserId(task.getAssignedTo().getId());

        model.addAttribute("taskRequest", req);
        model.addAttribute("taskId", id);
        model.addAttribute("projects", projectService.getAllProjects());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("statuses", Status.values());
        return "tasks/edit";
    }

    // Handle edit task
    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id,
                             @ModelAttribute TaskRequest taskRequest,
                             RedirectAttributes redirectAttributes) {
        try {
            taskService.updateTask(id, taskRequest);
            redirectAttributes.addFlashAttribute("success", "Task updated!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }

    // Update status only
    @PostMapping("/status/{id}")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam Status status,
                               RedirectAttributes redirectAttributes) {
        try {
            taskService.updateStatus(id, status);
            redirectAttributes.addFlashAttribute("success", "Task status updated!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }

    // Delete task
    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            taskService.deleteTask(id);
            redirectAttributes.addFlashAttribute("success", "Task deleted.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/tasks";
    }
}
