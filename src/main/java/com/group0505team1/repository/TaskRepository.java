package com.group0505team1.repository;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskPriority;
import com.group0505team1.entity.TaskStatus;

import java.util.ArrayList;
import java.util.List;

public class TaskRepository implements TaskRepositoryInterface {
    private final List<Task> tasks;

    public TaskRepository() {
        this.tasks = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public Task findById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    @Override
    public List<Task> findByFilter(Boolean active, TaskPriority priority) {
        return tasks.stream()
                .filter(t -> active
                        ? t.getStatus() == TaskStatus.TODO || t.getStatus() == TaskStatus.IN_PROGRESS
                        : t.getStatus() == TaskStatus.DONE)
                .filter(t -> t.getPriority() == priority)
                .toList();
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }
}
