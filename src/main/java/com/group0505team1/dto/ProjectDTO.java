package com.group0505team1.dto;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectDTO {
    private int projectId;
    private String title;
    private String description;
    private List<User> users;
    private List<Task> tasks;

    public  ProjectDTO(int projectId, String title, String description, List<User> users, List<Task> tasks) {
        this.projectId = projectId;
        this.title = title;
        this.description = description;
        this.users = users;
        this.tasks = tasks;
    }

    public static ProjectDTO fromProject(Project project){
        return new ProjectDTO(project.getProjectId(),project.getTitle(),project.getDescription(), project.getUsers(),project.getTasks());
    }
    public static List<ProjectDTO> fromProjectList(List<Project> projects){
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project p : projects){
            projectDTOs.add(fromProject(p));
        }
        return projectDTOs;
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
}
