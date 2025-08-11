package com.group0505team1.entity;

import java.util.List;

public class User {
    private static int counter = 0;
    private int id;
    private String name;
    private String password;
    private Role role;
    private List<Integer> userTasks; //хранить только ID задач

    public User( String name, String password, Role role, List<Integer> userTasks) {
        this.id = ++counter;
        this.name = name;
        this.password = password;
        this.role = role;
        this.userTasks = userTasks;
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

    public Role getRole() {
        return role;
    }

    public List<Integer> getUserTasks() {
        return userTasks;
    }

    @Override
    public String toString() {
        String tasksInfo = userTasks.isEmpty() ? "empty" : String.valueOf(userTasks.size());
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", role=" + role +
                ", tasks in process =  " +
                tasksInfo +
                '}';
    }
}
