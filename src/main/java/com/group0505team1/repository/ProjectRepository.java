package com.group0505team1.repository;

import com.group0505team1.entity.Project;

import java.util.List;

public interface ProjectRepository {
    void addProject(Project project);
    void setStatus(int projectId, boolean status);
    Project findByID(int id);
    List<Project> getAllProject();
}
