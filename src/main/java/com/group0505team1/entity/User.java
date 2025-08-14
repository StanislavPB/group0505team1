package com.group0505team1.entity;

import com.group0505team1.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;

public class User {
    private static int counter = 0;
    private int id;
    private String name;
    private String passwordHash;
    private RoleUser roleUser;
    private List<Task> userTasks;

    public User(String name, String password, RoleUser roleUser) {
        this.id = ++counter;
        this.name = name;
        this.passwordHash = PasswordUtil.hashPassword(password);
        this.roleUser = roleUser;
        this.userTasks = new ArrayList<>();
    }
    public void addTask(Task task) {
        userTasks.add(task);
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return passwordHash;
    }

    public RoleUser getRole() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    @Override
    public String toString() {
        String tasksInfo = userTasks.isEmpty() ? "empty" : String.valueOf(userTasks.size());
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + roleUser +
                ", tasks in process =  " +
                tasksInfo +
                '}';
    }
}
