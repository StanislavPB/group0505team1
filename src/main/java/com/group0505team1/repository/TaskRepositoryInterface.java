package com.group0505team1.repository;

import com.group0505team1.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepositoryInterface {

    void add(Task task);

    Optional<Task> findById(int id);

    List<Task> findByUserId(int userId);

    List<Task> findByUserIdAndFilter(int userId, Boolean active, Integer priority);

    List<Task> findAll();

}
