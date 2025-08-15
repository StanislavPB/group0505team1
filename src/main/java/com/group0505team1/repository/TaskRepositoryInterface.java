package com.group0505team1.repository;

import com.group0505team1.entity.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {

    void add(Task task);

    Task findById(int id);

    List<Task> findByFilter(Boolean active, TaskPriority priority);

    List<Task> findAll();

    void setTaskStatus(Task task, TaskStatus taskStatus);

    void setTaskPriority(Task task, TaskPriority taskPriority);

    void setDeadline(Task task, LocalDate deadline);

    void assignTaskToProject(Task task, int projectId);

}
