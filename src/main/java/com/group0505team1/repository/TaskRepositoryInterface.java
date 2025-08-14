package com.group0505team1.repository;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskPriority;
import com.group0505team1.entity.User;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {

    void add(Task task);

    Task findById(int id);

    List<Task> findByFilter(Boolean active, TaskPriority priority);

    List<Task> findAll();
}
