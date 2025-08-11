package com.group0505team1.services;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskStatus;
import com.group0505team1.repository.TaskRepository;
import com.group0505team1.repository.UserRepository;

import java.time.LocalDate;

/*
Tasks:
- addTask
- getTaskById
- getAllTasksByFilter (Active, inactive, priority)
- taskStatusUpdate
- assignUserToTask
 */
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }


    public void addTask(String title, String description, TaskStatus status, int priority, LocalDate deadline, int projectId) {
        Task task = new Task(title, description, status, priority, deadline, projectId);
        taskRepository.save(task);
    }
}
