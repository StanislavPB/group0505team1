package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.dto.ProjectDTO;
import com.group0505team1.dto.RequestProjectDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepositoryInterface;

import java.util.List;

public class ProjectService {
    private final ProjectRepositoryInterface projectRepository;
    private final TaskService taskService;
    private final UserService userService;

    public ProjectService(ProjectRepositoryInterface projectRepository, TaskService taskService, UserService userService) {
        this.projectRepository = projectRepository;
        this.taskService = taskService;
        this.userService = userService;
    }

    public ResponseDTO addProject(RequestProjectDTO requestProjectDTO) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }

        String name = requestProjectDTO.getTitle();
        String description = requestProjectDTO.getDescription();
        if ((name == null || name.isBlank()) || (description == null || description.isBlank())) {
            return new ResponseDTO(400, "Wrong data!", null);
        }
        Project project = projectRepository.findByName(name);
        if (project != null) {
            return new ResponseDTO(400, "Project name is already exist!", null);
        }
        Project newProject = new Project(name, description);
        projectRepository.add(newProject);
        return new ResponseDTO<>(200, "Project add successfully!", null);
    }

    public ResponseDTO findById(int id) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        Project project = projectRepository.findByID(id);
        if (project == null) {
            return new ResponseDTO(400, "Project isn't exist!", null);
        }

        return new ResponseDTO<>(200, "Project found!", ProjectDTO.fromProject(project));
    }

    public ResponseDTO findByName(String name) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        Project project = projectRepository.findByName(name);
        if (project == null) {
            return new ResponseDTO(400, "Project isn't exist!", null);
        }

        return new ResponseDTO<>(200, "Project found!", ProjectDTO.fromProject(project));

    }

    public ResponseDTO getAllProjects() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        List<Project> projects = projectRepository.getAllProject();
        return new ResponseDTO<>(200, "Projects found!", ProjectDTO.fromProjectList(projects));

    }

    public ResponseDTO addUserToProject(int projectId, int userId) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        ResponseDTO responseProject = findById(projectId);
        ResponseDTO responseUser = userService.getUserById(userId);
        projectRepository.addUserToProject(projectId, (User) responseUser.getDataObject());
        return new ResponseDTO<>(200, "User added to project successfully!", null);
    }

    public ResponseDTO addTaskToProject(int projectId, int taskId) {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        if (!SessionContext.isAdmin()) {
            return new ResponseDTO(403, "Access denied. Admin rights are required", null);
        }
        ResponseDTO responseProject = findById(projectId);
        ResponseDTO responseTask = taskService.findTaskById(taskId);
        projectRepository.addTaskToProject(projectId, (Task) responseTask.getDataObject());
        return new ResponseDTO<>(200, "Task added to project successfully!", null);
    }
}
