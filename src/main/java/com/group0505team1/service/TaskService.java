package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.RequestTaskDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.dto.TaskDTO;
import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskPriority;
import com.group0505team1.entity.TaskStatus;
import com.group0505team1.repository.TaskRepositoryInterface;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

public class TaskService {
    TaskRepositoryInterface taskRepositoryInterface;
    UserSecurity userSecurity;
    ProjectService projectService;

    public TaskService(TaskRepositoryInterface taskRepositoryInterface, UserSecurity userSecurity,ProjectService projectService) {
        this.taskRepositoryInterface = taskRepositoryInterface;
        this.userSecurity = userSecurity;
        this.projectService = projectService;
    }

    public ResponseDTO<Task> addTask(RequestTaskDTO requestTaskDTO) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO<>(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO<>(403, "Access denied. Admin rights are required", null);
        }
        taskRepositoryInterface.add(new Task(requestTaskDTO.getTitle(),
                requestTaskDTO.getDescription(),
                requestTaskDTO.getStatus(),
                requestTaskDTO.getPriority(),
                requestTaskDTO.getDeadline(),
                requestTaskDTO.getProjectId()));

        return new ResponseDTO<>(200, "Successfully added task", null);
    }

    public ResponseDTO findAllTasks() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        List<Task> tasks = taskRepositoryInterface.findAll();

        return new ResponseDTO(200, "Successfully found all tasks", TaskDTO.fromTaskList(tasks));
    }

    public ResponseDTO findTaskById(int taskId) {
        if (SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        Task task = taskRepositoryInterface.findById(taskId);
        if (task == null) {
            return new ResponseDTO(404, "Task not found", null);
        }
        return new ResponseDTO(200, "Successfully found task", TaskDTO.fromTask(task));
    }

    public ResponseDTO findTaskByFilter(Boolean active, TaskPriority priority) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        List<Task> tasks = taskRepositoryInterface.findByFilter(active, priority);
        if (tasks.isEmpty()) {
            return new ResponseDTO(404, "Task not found", null);
        }
        return new ResponseDTO(200, "Successfully found tasks", TaskDTO.fromTaskList(tasks));
    }

    public ResponseDTO setTaskStatus(int taskId, String taskStatus) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        Task task = taskRepositoryInterface.findById(taskId);
        if (task == null) {
            return new ResponseDTO(404, "Task not found", null);
        }
        if (Arrays.stream(TaskStatus.values()).noneMatch(r -> r.name().equals(taskStatus))) {
            return new ResponseDTO<>(400, "Invalid status", null);
        }
        taskRepositoryInterface.setTaskStatus(task, TaskStatus.valueOf(taskStatus));
        return new ResponseDTO(200, "Successfully set task status", task);
    }

    public ResponseDTO setTaskPriority(int taskId, String taskPriority) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        Task task = taskRepositoryInterface.findById(taskId);
        if (task == null) {
            return new ResponseDTO(404, "Task not found", null);
        }
        if (Arrays.stream(TaskPriority.values()).noneMatch(r -> r.name().equals(taskPriority))) {
            return new ResponseDTO<>(400, "Invalid priority", null);
        }

        taskRepositoryInterface.setTaskPriority(task, TaskPriority.valueOf(taskPriority));
        return new ResponseDTO(200, "Successfully set task priority", task);
    }


    public ResponseDTO setDeadline(int taskId, String deadline) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        Task task = taskRepositoryInterface.findById(taskId);
        if (task == null) {
            return new ResponseDTO(404, "Task not found", null);
        }
        if (deadline == null || deadline.isEmpty()) {
            return new ResponseDTO(400, "Parameter is required", null);
        }
        LocalDate date;
        try {
            date = LocalDate.parse(deadline);
        }catch (DateTimeParseException e){
            return new ResponseDTO(400, "Invalid date format", null);
        }
        taskRepositoryInterface.setDeadline(task, date);
        return new ResponseDTO(200, "Successfully set task deadline", task);
    }

    public ResponseDTO assignTaskToProject(int taskId, int projectId) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        Task task = taskRepositoryInterface.findById(taskId);
        if (task == null) {
            return new ResponseDTO(404, "Task not found", null);
        }
        Project project = projectService.findById(projectId);
        if (project == null) {
            return new ResponseDTO(404, "Project not found", null);
        }
        taskRepositoryInterface.assignTaskToProject(task, projectId);
        return new ResponseDTO(200, "Successfully assigned task to project", task);
    }
}

