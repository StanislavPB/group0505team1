package com.group0505team1.dto;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskPriority;
import com.group0505team1.entity.TaskStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDTO {
    private int id = 0;
    private String title;
    private String description;
    private TaskStatus status;
    private TaskPriority priority;
    private LocalDate deadline;
    private int projectId;

    public TaskDTO(int id, int projectId, LocalDate deadline, TaskPriority priority, TaskStatus status, String description, String title) {
        this.id = id;
        this.projectId = projectId;
        this.deadline = deadline;
        this.priority = priority;
        this.status = status;
        this.description = description;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static TaskDTO fromTask(Task task){
        return new TaskDTO(task.getId(), task.getProjectId(),  task.getDeadline(), task.getPriority(), task.getStatus(), task.getDescription(), task.getTitle());
    }

    public static List<TaskDTO> fromTaskList(List<Task> tasks){
        List<TaskDTO> taskDTOs = new ArrayList<>();
        for(Task task : tasks){
            taskDTOs.add(fromTask(task));
        }
        return taskDTOs;
    }
}
