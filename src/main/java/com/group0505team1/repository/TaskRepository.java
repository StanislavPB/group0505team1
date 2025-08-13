package com.group0505team1.repository;

import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TaskRepository implements TaskRepositoryInterface {
    private final List<Task> tasks = new ArrayList<>();
    private final UserRepository userRepository;

    public TaskRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(Task task) {
        tasks.add(task);
    }

    @Override
    public Optional<Task> findById(int id) {
        return tasks.stream()
                .filter(t -> t.getId() == id)
                .findFirst();
    }

    @Override
    public List<Task> findByUserId(int userId) {
        return userRepository.findById(userId).getUserTasks();
    }

    @Override
    public List<Task> findByUserIdAndFilter(int userId, Boolean active, Integer priority) {

        return findByUserId(userId).stream()
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
