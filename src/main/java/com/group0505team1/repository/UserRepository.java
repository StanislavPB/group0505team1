package com.group0505team1.repository;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import com.group0505team1.exception.UserNotFoundException;



import java.util.ArrayList;
import java.util.List;


public class UserRepository implements UserRepositoryInterface {

    private final List<User> users;

    public UserRepository() {
        this.users = new ArrayList<>();
    }


    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override

    public void addTask(User user, Task task) {
        user.addTask(task);

    }

    @Override
    public User findById(int id) {

        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void setRole(User user, RoleUser role) {
        user.setRoleUser(role);
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

}
