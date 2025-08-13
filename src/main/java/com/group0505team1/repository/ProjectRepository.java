package com.group0505team1.repository;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.exception.ProjectNotFoundException;

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
        return projects.stream()
                .filter(p -> p.getProjectId() == id)
                .findFirst()
                .orElseThrow(() -> new ProjectNotFoundException("Проэкт с id " + id + " не найден!"));
    }

    @Override
    public Project findByName(String name) {
        return projects.stream()
                .filter(p -> p.getTitle().equals(name))
                .findFirst()
                .orElseThrow(() -> new ProjectNotFoundException("Проэкт с названием " + name + " не найден!"));
    }

    @Override
    public List<Project> getAllProject() {
        return new ArrayList<>(projects);
    }

    @Override
    public boolean addUserToProject(int idProject, User user) {
        Project project = findByID(idProject);
        project.addUser(user);
        return true;
    }

    @Override
    public boolean addTaskToProject(int idProject, Task task) {
        Project project = findByID(idProject);
        project.addTask(task);
        return true;
    }
}
