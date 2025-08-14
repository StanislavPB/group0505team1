package com.group0505team1.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int counter = 0;
    private int id;
    private String name;
    private String login;
    private String passwordHash;
    private RoleUser roleUser;
    private List<Task> userTasks; //хранить только ID задач

    public User(String name,String login, String passwordHash) {
        this.id = ++counter;
        this.name = name;
        this.login = login;
        this.passwordHash = passwordHash;
        this.roleUser = RoleUser.USER;
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

    public String getLogin() {
        return login;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public List<Task> getUserTasks() {
        return userTasks;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    @Override
    public String toString() {
        String tasksInfo = userTasks.isEmpty() ? "empty" : String.valueOf(userTasks.size());
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", role=" + roleUser +
                ", tasks in process =  " +
                tasksInfo +
                '}';
    }
}
