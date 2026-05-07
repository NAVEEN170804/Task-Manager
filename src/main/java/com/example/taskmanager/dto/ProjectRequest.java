package com.example.taskmanager.dto;

import com.example.taskmanager.entity.Priority;
import java.time.LocalDate;

public class ProjectRequest {
    private String name;
    private String description;
    private LocalDate dueDate;
    private Priority priority;
    private Long userId;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}
