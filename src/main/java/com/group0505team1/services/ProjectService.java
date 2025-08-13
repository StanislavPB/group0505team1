package com.group0505team1.services;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepositoryInterface;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProjectService {
    private final ProjectRepositoryInterface projectRepositoryInterface;

    public ProjectService(ProjectRepositoryInterface projectRepositoryInterface) {
        this.projectRepositoryInterface = projectRepositoryInterface;
    }

    public void addProject(String title, String description, List<User> users, List<Task> tasks, Map<Integer, Map<LocalDateTime, String>> projectChat){
        Project project = new Project(title,description );
        projectRepositoryInterface.add(project);
    }

    public List<Project> findAllProjects(){
        return projectRepositoryInterface.getAllProject();
    }
}
