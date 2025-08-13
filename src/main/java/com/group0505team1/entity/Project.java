package com.group0505team1.entity;

import java.time.LocalDateTime;
import java.util.*;

public class Project {
    private int counter = 0;
    private int projectId;
    private String title;
    private String description;
    private List<User> users;
    private List<Task> tasks;
    private Map<Integer, Map<LocalDateTime, String>> projectChat;

    public Project(String title, String description) {
        this.projectId = ++counter;
        this.title = title;
        this.description = description;
        this.users = new ArrayList<>();
        this.tasks =new ArrayList<>();
        this.projectChat = new TreeMap<>();
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

    public Map<Integer, Map<LocalDateTime, String>> getProjectChat() {
        return projectChat;
    }

    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", users=" +  users.size()  +
                ", tasks=" +  tasks.size()  +
                ", projectChat=" +  projectChat.size() +
                '}';
    }
}
