package com.group0505team1.entity;

import java.time.LocalDateTime;
import java.util.*;

public class Project {
    private static int counter = 0;
    private int projectId;
    private String title;
    private String description;
    private List<User> users;
    private List<Task> tasks;

    public Project(String title, String description) {
        this.projectId = ++counter;
        this.title = title;
        this.description = description;
        this.users = new ArrayList<>();
        this.tasks =new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
    public void addUser(User user) {
        users.add(user);
    }
    public int getProjectId() {
        return projectId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Task> getTasks() {
        return tasks;
    }



    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", users=" +  users.size()  +
                ", tasks=" +  tasks.size()  +
                '}';
    }
}
