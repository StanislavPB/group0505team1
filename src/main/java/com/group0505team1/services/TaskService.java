package com.group0505team1.services;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskStatus;
import com.group0505team1.exception.TaskNotFoundException;
import com.group0505team1.repository.ProjectRepository;
import com.group0505team1.repository.TaskRepository;
import com.group0505team1.repository.UserRepository;

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
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }

    public void addTask(String title, String description, TaskStatus status, int priority, LocalDate deadline, int projectId) {
        Task task = new Task(title, description, status, priority, deadline, projectId);
        taskRepository.save(task);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public List<Task> getAllTasksByFilter(String projectTitle, TaskStatus status, Integer priority) {
        return findAll().stream()
                //фильтр по названию проекта
                .filter(task -> projectTitle == null ||
                        Optional.ofNullable(projectRepository.findByID(task.getProjectId()))
                                .map(project -> project.getTitle().equals(projectTitle))
                                .orElse(false))//если проект не найден то false
                .filter(task -> status == null || task.getStatus() == status) //фильтр по статусу
                .filter(task -> priority == null || task.getPriority() == priority) //фильтр по приоритету
                .toList();

    }

    public Task getTaskById(int id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с id " + id + " не найдена!"));
    }

    public void taskStatusUpdate(int taskId, TaskStatus newStatus) {
        Task task = getTaskById(taskId);
        task.setStatus(newStatus);
        taskRepository.save(task);
    }
}
