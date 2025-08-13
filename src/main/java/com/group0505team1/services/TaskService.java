package com.group0505team1.services;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskStatus;
import com.group0505team1.exception.TaskNotFoundException;
import com.group0505team1.repository.ProjectRepositoryInterface;
import com.group0505team1.repository.TaskRepositoryInterface;
import com.group0505team1.repository.UserRepositoryInterface;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/*
Tasks:

- getTaskById
- getAllTasksByFilter (Active, inactive, priority)
- taskStatusUpdate
- assignUserToTask
 */
public class TaskService {
    private final TaskRepositoryInterface taskRepositoryInterface;
    private final UserRepositoryInterface userRepositoryInterface;
    private final ProjectRepositoryInterface projectRepositoryInterface;

    public TaskService(TaskRepositoryInterface taskRepositoryInterface, UserRepositoryInterface userRepositoryInterface, ProjectRepositoryInterface projectRepositoryInterface) {
        this.taskRepositoryInterface = taskRepositoryInterface;
        this.userRepositoryInterface = userRepositoryInterface;
        this.projectRepositoryInterface = projectRepositoryInterface;
    }

    public void addTask(String title, String description, TaskStatus status, int priority, LocalDate deadline, int projectId) {
        Task task = new Task(title, description, status, priority, deadline, projectId);
        taskRepositoryInterface.add(task);
    }

    public List<Task> findAll() {
        return taskRepositoryInterface.findAll();
    }

    public List<Task> getAllTasksByFilter(String projectTitle, TaskStatus status, Integer priority) {
        return findAll().stream()
                //фильтр по названию проекта
                .filter(task -> projectTitle == null ||
                        Optional.ofNullable(projectRepositoryInterface.findByID(task.getProjectId()))
                                .map(project -> project.getTitle().equals(projectTitle))
                                .orElse(false))//если проект не найден то false
                .filter(task -> status == null || task.getStatus() == status) //фильтр по статусу
                .filter(task -> priority == null || task.getPriority() == priority) //фильтр по приоритету
                .toList();

    }

    public Task getTaskById(int id) {
        return taskRepositoryInterface.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с id " + id + " не найдена!"));
    }

    public void taskStatusUpdate(int taskId, TaskStatus newStatus) {
        Task task = getTaskById(taskId);
        task.setStatus(newStatus);
        taskRepositoryInterface.add(task);
    }
}
