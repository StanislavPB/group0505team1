package com.group0505team1.repository;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.List;

public interface UserRepositoryInterface {
    void add(User user);

    void addTask(User user, Task task);

    User findById(int id);

    List<User> findByName(String name);

    User findByLogin(String login);

    List<User> findAll();

    void setRole(User user, RoleUser role);
}
