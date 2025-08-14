package com.group0505team1.service;

import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepositoryInterface;

import java.util.List;

public class ProjectService {
    private final ProjectRepositoryInterface projectRepository;

    public ProjectService(ProjectRepositoryInterface projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(Project project) {
        try {
            if (project == null) {
                throw new IllegalArgumentException("Project cannot be null");
            }
            projectRepository.add(project);
            System.out.println("Project successfully added!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding project: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }

    public Project findByID(int id) {
        try {
            Project project = projectRepository.findByID(id);
            if (project == null) {
                System.out.println("No project found with ID: " + id);
            }
            return project;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }

    public Project findByName(String name) {
        try {
            if (name == null || name.isBlank()) {
                throw new IllegalArgumentException("Project name cannot be empty");
            }
            Project project = projectRepository.findByName(name);
            if (project == null) {
                System.out.println("No project found with name: " + name);
            }
            return project;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return null;
        }
    }

    public List<Project> getAllProjects() {
        try {
            return projectRepository.getAllProject();
        } catch (Exception e) {
            System.out.println("Unexpected error while fetching projects: " + e.getMessage());
            return List.of();
        }
    }

    public boolean addUserToProject(int idProject, User user) {
        try {
            if (user == null) {
                throw new IllegalArgumentException("User cannot be null");
            }
            boolean result = projectRepository.addUserToProject(idProject, user);
            if (result) {
                System.out.println("User successfully added to the project!");
            } else {
                System.out.println("Failed to add user to the project.");
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }

    public boolean addTaskToProject(int idProject, Task task) {
        try {
            if (task == null) {
                throw new IllegalArgumentException("Task cannot be null");
            }
            boolean result = projectRepository.addTaskToProject(idProject, task);
            if (result) {
                System.out.println("Task successfully added to the project!");
            } else {
                System.out.println("Failed to add task to the project.");
            }
            return result;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }
}
