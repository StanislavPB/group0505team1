package com.group0505team1.entity;

import java.time.LocalDate;

public class Task {
    private static int counter = 0;
    private int id = 0;
    private String title;
    private String description;
    private TaskStatus status;
    private int priority;
    private LocalDate deadline;
    private int projectId;

    public Task(String title, String description, TaskStatus status, int priority, LocalDate deadline, int projectId) {
        this.id = ++counter;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.deadline = deadline;
        this.projectId = projectId;
    }

    public int getId() {
        return id;
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

    public int getPriority() {
        return priority;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", priority=" + priority +
                ", deadline=" + deadline +
                ", projectId=" + projectId +
                '}';
    }
}
