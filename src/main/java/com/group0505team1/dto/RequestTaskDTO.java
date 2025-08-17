package com.group0505team1.dto;

import com.group0505team1.entity.TaskPriority;
import com.group0505team1.entity.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class RequestTaskDTO {

    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate deadline;
    private int projectId;

    public RequestTaskDTO(String title, String description, LocalDate deadline, int projectId) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.TODO;
        this.priority = TaskPriority.LOW;
        this.deadline = deadline;
        this.projectId = projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public RequestTaskDTO(LocalDate deadline) {
        this.deadline = deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setPriority(TaskPriority priority) {
        this.priority = priority;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }
}
