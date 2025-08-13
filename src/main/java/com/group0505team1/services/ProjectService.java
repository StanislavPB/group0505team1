package com.group0505team1.services;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProjectService {
    private final ProjectInterface projectInterface;

    public ProjectService(ProjectInterface projectInterface) {
        this.projectInterface = projectInterface;
    }

    public void addProject(String title, String description, List<User> users, List<Task> tasks, Map<Integer, Map<LocalDateTime, String>> projectChat){
        Project project = new Project(title,description,users,tasks,projectChat );
        projectInterface.addProject(project);
    }

    public List<Project> findAllProjects(){
        return projectInterface.getAllProject();
    }
}
