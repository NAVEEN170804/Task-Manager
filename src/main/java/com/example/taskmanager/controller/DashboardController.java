// DashboardController.java
package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.taskmanager.entity.*;
import com.example.taskmanager.service.*;
import java.util.List;

@Controller
public class DashboardController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root() {
        return "redirect:/dashboard";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Authentication auth) {
        User loggedUser = userService.getUserByEmail(auth.getName());

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Project> projects = isAdmin
                ? projectService.getAllProjects()
                : projectService.getProjectsByUser(loggedUser.getId());

        List<Task> tasks = isAdmin
                ? taskService.getAllTasks()
                : taskService.getTasksByUser(loggedUser.getId());

        model.addAttribute("totalProjects", projects.size());
        model.addAttribute("totalTasks", tasks.size());
        model.addAttribute("totalUsers", userService.getAllUsers().size());
        model.addAttribute("myTasks", tasks);          // admin sees all tasks, member sees own
        model.addAttribute("recentProjects", projects); // admin sees all, member sees own
        model.addAttribute("loggedUser", loggedUser);

        return "dashboard";
    }
}