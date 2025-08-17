package com.group0505team1.service;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.dto.ProjectDTO;
import com.group0505team1.dto.ResponseDTO;
import com.group0505team1.dto.UserDTO;
import com.group0505team1.entity.TaskStatus;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class StatisticService implements StatisticServiceInterface {

    private final UserServiceInterface userService;
    private final TaskServiceInterface taskService;
    private final ProjectServiceInterface projectService;

    public StatisticService(UserServiceInterface userService, TaskServiceInterface taskService, ProjectServiceInterface projectService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
    }

    private List<ProjectDTO> getAllProjectsSafe() {
        return (List<ProjectDTO>) projectService.getAllProjects().getDataObject();
    }

    private List<UserDTO> getAllUsersSafe() {
        return (List<UserDTO>) userService.getAllUsers().getDataObject();
    }

    private boolean isAuthenticated() {
        return SessionContext.isAuthenticated();
    }

    @Override
    public ResponseDTO getProjectsTotalQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        return new ResponseDTO(200, "Ok", getAllProjectsSafe().size());
    }

    @Override
    public ResponseDTO getProjectsDoneQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        long doneProject = getAllProjectsSafe()
                .stream()
                .filter(p ->
                        p.getTasks() != null &&
                                !p.getTasks().isEmpty() &&
                                p.getTasks()
                                        .stream()
                                        .allMatch(t -> t.getStatus() == TaskStatus.DONE))
                .count();
        return new ResponseDTO(200, "Ok", (int) doneProject);
    }

    @Override
    public ResponseDTO getProjectsInProcessQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        long inProgress = getAllProjectsSafe()
                .stream()
                .filter(p -> p.getTasks() != null &&
                        !p.getTasks().isEmpty() &&
                        p.getTasks()
                                .stream()
                                .anyMatch(t -> t.getStatus() == TaskStatus.IN_PROGRESS || t.getStatus() == TaskStatus.TODO))
                .count();

        return new ResponseDTO(200, "Ok", inProgress);
    }

    @Override
    public ResponseDTO getTaskTotalQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        int taskAll = getAllProjectsSafe()
                .stream()
                .mapToInt(p -> p.getTasks() == null ? 0 : p.getTasks().size())
                .sum();
        return new ResponseDTO(200, "Ok", taskAll);
    }

    @Override
    public ResponseDTO getTaskDoneQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        int taskDone = getAllProjectsSafe()
                .stream()
                .mapToInt(p -> p.getTasks() == null ? 0
                        : (int) p.getTasks()
                        .stream()
                        .filter(t -> t.getStatus() == TaskStatus.DONE).count())
                .sum();
        return new ResponseDTO(200, "Ok", taskDone);
    }

    @Override
    public ResponseDTO getTaskInProcessQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        int taskInProcess = getAllProjectsSafe()
                .stream()
                .mapToInt(p -> p.getTasks() == null ? 0
                        : (int) p.getTasks()
                        .stream()
                        .filter(t -> t.getStatus() != TaskStatus.DONE).count())
                .sum();
        return new ResponseDTO(200, "Ok", taskInProcess);
    }

    @Override
    public ResponseDTO getTaskOverTimeQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        int taskOverTime = getAllProjectsSafe()
                .stream()
                .mapToInt(p -> p.getTasks() == null ? 0
                        : (int) p.getTasks()
                        .stream()
                        .filter(t ->t.getDeadline() != null && t.getDeadline().isBefore(LocalDate.now()))
                        .count())
                .sum();
        return new ResponseDTO(200, "Ok", taskOverTime);
    }

    @Override
    public ResponseDTO getUserTotalQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        int userQuantity = getAllUsersSafe().size();
        return new ResponseDTO(200, "Ok", userQuantity);
    }

    @Override
    public ResponseDTO getUsersByTasksQuantity() {
        if (!isAuthenticated()) return new ResponseDTO(401, "Authentication required", null);
        List<UserDTO> usersSortedByTaskDesc = getAllUsersSafe().stream()
                .sorted(Comparator.comparingInt((UserDTO u) -> u.getUserTasks().size()).reversed())
                .collect(Collectors.toList());
        return new ResponseDTO(200, "Ok", usersSortedByTaskDesc);
    }
}
