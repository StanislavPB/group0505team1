package com.group0505team1.repository;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.List;

public interface ProjectRepositoryInterface {
    void add(Project project);

    Project findByID(int id);

    Project findByName(String name);

    List<Project> getAllProject();

    boolean addUserToProject(int idProject, User user);

    boolean addTaskToProject(int idProject, Task task);
}
