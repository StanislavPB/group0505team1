package com.group0505team1.services;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(String title, String description, List<User> users, List<Task> tasks, Map<Integer, Map<LocalDateTime, String>> projectChat){
        Project project = new Project(title,description,users,tasks,projectChat );
        projectRepository.addProject(project);
    }

    public List<Project> findAllProjects(){
        return projectRepository.getAllProject();
    }
}
