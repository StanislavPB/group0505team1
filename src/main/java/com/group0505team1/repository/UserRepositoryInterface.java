package com.group0505team1.repository;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.List;

public interface UserRepositoryInterface {
    void add(User user);

    void addTask(Task task);

    User findById(int id);

    User findByName(String name);
    void setRole(RoleUser role);

}
