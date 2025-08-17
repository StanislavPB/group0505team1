package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.dto.ProjectDTO;
import com.group0505team1.dto.ResponseDTO;

import java.util.List;

public class StatisticService implements StatisticServiceInterface{

    UserServiceInterface userService;
    TaskServiceInterface taskService;
    ProjectServiceInterface projectService;

    public StatisticService(UserServiceInterface userService, TaskServiceInterface taskService, ProjectServiceInterface projectService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
    }


    @Override
    public ResponseDTO getProjectsTotalQuantity() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        return new ResponseDTO(200, "Ok", ((List<ProjectDTO>) projectService.getAllProjects().getDataObject()).size());
    }

    @Override
    public ResponseDTO getProjectsDoneQuantity() {
        if (!SessionContext.isAuthenticated()) {
            return new ResponseDTO(401, "Authentication required", null);
        }
        return null;
    }

    @Override
    public ResponseDTO getProjectsInProcessQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getTaskTotalQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getTaskDoneQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getTaskInProcessQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getTaskOverTimeQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getUserTotalQuantity() {
        return null;
    }

    @Override
    public ResponseDTO getUsersByTasksQuantity() {
        return null;
    }
}
