package com.group0505team1.repository;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

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
    public List<User> findByName(String name) {
        List<User> foundUsers = new ArrayList<>();
        for (User user : users) {
            if (user.getName().equals(name)) {
                foundUsers.add(user);
            }
        }
        return foundUsers;
    }

    @Override
    public User findByLogin(String login) {
        for (User user : users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<Task> findTasksByUserId(int id) {
        User foundUser = findById(id);
        return foundUser.getUserTasks();
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
