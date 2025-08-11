package com.group0505team1.repository;

import com.group0505team1.entity.Task;

import java.util.List;

public interface TaskRepository {

    void save(Task task);
    Task findById(int id);
    List<Task> findByUserId(int userId);
    List<Task> findByUserIdAndFilter(int userId, Boolean active, Integer priority);

}
