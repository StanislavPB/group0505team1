package com.group0505team1.repository;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository implements ProjectRepositoryInterface {

    List<Project> projects = new ArrayList<>();

    @Override
    public void add(Project project) {
        projects.add(project);
    }

    @Override
    public Project findByID(int id) {
        for (Project p : projects) {
            if (p.getProjectId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public Project findByName(String name) {
        for (Project p : projects) {
            if (p.getTitle().equals(name)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Project> getAllProject() {
        return new ArrayList<>(projects);
    }

    @Override
    public boolean addUserToProject(int idProject, User user) {
        Project project = findByID(idProject);
        if (project.getUsers().contains(user)) {
            return false;
        }
        project.addUser(user);
        return true;
    }

    @Override
    public boolean addTaskToProject(int idProject, Task task) {
        Project project = findByID(idProject);
        if (project.getTasks().contains(task)) {
            return false;
        }
        project.getTasks().add(task);
        return true;
    }
}

