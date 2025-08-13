package com.group0505team1.repository;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.exception.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;


public class UserRepository implements UserRepositoryInterface {
    private final List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void addTask(int userId, Task task) {
        User user = findById(userId);
        user.getUserTasks().add(task);

    }

    @Override
    public User findById(int id) {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Пользователь с id " + id + " не найден!"));
    }

    @Override
    public User findByName(String name) {
        return users.stream()
                .filter(u -> u.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new UserNotFoundException("Пользователь с именем " + name + " не найден!"));
    }

    @Override
    public void setRole(int userId, RoleUser role) {
        User user = findById(userId);
        user.setRoleUser(role);
    }
}
