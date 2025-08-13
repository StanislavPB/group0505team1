package com.group0505team1.entity;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int counter = 0;
    private int id;
    private String name;
    private String password;
    private RoleUSer roleUSer;
    private List<Task> userTasks; //хранить только ID задач

    public User(String name, String password, RoleUSer roleUSer) {
        this.id = ++counter;
        this.name = name;
        this.password = password;
        this.roleUSer = roleUSer;
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
        return password;
    }

    public RoleUSer getRole() {
        return roleUSer;
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
                ", role=" + roleUSer +
                ", tasks in process =  " +
                tasksInfo +
                '}';
    }
}
