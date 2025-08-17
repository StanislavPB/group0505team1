package com.group0505team1.userInterface;

import ch.qos.logback.core.encoder.JsonEscapeUtil;
import com.group0505team1.Utils.UserInputStatic;
import com.group0505team1.auth.SessionContext;
import com.group0505team1.dto.*;
import com.group0505team1.entity.Project;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.User;
import com.group0505team1.service.*;


import java.time.LocalDate;
import java.util.List;

public class UtilsProcess {
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final MessageService messageService;
    private final StatisticServiceInterface statisticService;

    public UtilsProcess(UserService userService, TaskService taskService, ProjectService projectService, MessageService messageService, StatisticServiceInterface statisticService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.messageService = messageService;
        this.statisticService = statisticService;
    }


    public void login() {
        String login = UserInputStatic.inputText("Enter your login: ");
        String password = UserInputStatic.inputText("Enter your password: ");
        RequestAuthDTO requestAuthDTO = new RequestAuthDTO(login, password);
        ResponseDTO response = userService.authenticate(requestAuthDTO);
        System.out.println(response.getMessage());
    }

    public void register() {
        String name = UserInputStatic.inputText("Enter your name: ");
        String login = UserInputStatic.inputText("Enter your login: ");
        String password = UserInputStatic.inputText("Enter your password: ");
        String confirmPassword = UserInputStatic.inputText("Confirm your password: ");
        RequestRegisterDTO requestRegisterDTO = new RequestRegisterDTO(name, login, password, confirmPassword);
        ResponseDTO response = userService.registerUser(requestRegisterDTO);
        System.out.println(response.getMessage());
    }

    public void logout() {
        ResponseDTO response = userService.logout();
        System.out.println(response.getMessage());
    }

    public void showMyProjectsProcess() {
        System.out.println("My projects:");
        ResponseDTO response = projectService.getMyProjects();
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<ProjectDTO> projects = (List<ProjectDTO>) response.getDataObject();
        for (ProjectDTO project : projects) {
            System.out.println(project.toString());
        }
        System.out.println("Total projects: " + projects.size());
    }

    public void showAllProjectsProcess() {
        System.out.println("All projects:");
        ResponseDTO response = projectService.getAllProjects();
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<ProjectDTO> projects = (List<ProjectDTO>) response.getDataObject();
        for (ProjectDTO project : projects) {
            System.out.println(project.toString());
        }
        System.out.println("Total projects: " + projects.size());
    }


    public void createNewProjectProcess() {
        String title = UserInputStatic.inputText("Enter the title of the project: ");
        String description = UserInputStatic.inputText("Enter the description of the project: ");
        RequestProjectDTO requestProjectDTO = new RequestProjectDTO(title, description);
        ResponseDTO response = projectService.addProject(requestProjectDTO);
        System.out.println(response.getMessage());
    }


    public void assignUserToProjectProcess() {
        int idProject = UserInputStatic.inputInt("Enter the id of the project: ");
        int idUser = UserInputStatic.inputInt("Enter the id of the user: ");
        ResponseDTO response = projectService.addUserToProject(idProject, idUser);
        System.out.println(response.getMessage());
    }

    public void assignTaskToProjectProcess() {
        int idProject = UserInputStatic.inputInt("Enter the id of the project: ");
        int idTask = UserInputStatic.inputInt("Enter the id of the task: ");
        ResponseDTO response = projectService.addTaskToProject(idProject, idTask);
        System.out.println(response.getMessage());
    }

    public void showTasksForProjectProcess() {
        int idProject = UserInputStatic.inputInt("Enter the id of the project: ");
        ResponseDTO response = projectService.getTaskByProject(idProject);
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<TaskDTO> tasks = (List<TaskDTO>) response.getDataObject();
        for (TaskDTO task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("Total tasks: " + tasks.size());
    }

    public void showUsersForProjectProcess() {
        int idProject = UserInputStatic.inputInt("Enter the id of the project: ");
        ResponseDTO response = projectService.getUsersByProject(idProject);
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<UserDTO> users = (List<UserDTO>) response.getDataObject();
        for (UserDTO user : users) {
            System.out.println(user.toString());
        }
    }

   /* public void createNewUserProcess() {
        String name = UserInputStatic.inputText("Enter the name of the user: ");
        String login = UserInputStatic.inputText("Enter the login: ");
        String password = UserInputStatic.inputText("Enter the password: ");
        String confirmPassword = UserInputStatic.inputText("Confirm your password: ");
        RequestRegisterDTO requestRegisterDTO = new RequestRegisterDTO(name, login, password, confirmPassword);
        ResponseDTO response = userService.registerUser(requestRegisterDTO);
        System.out.println(response.getMessage());
    }*/

    public void showUserProfileProcess() {
        int userId = SessionContext.getCurrentUser().getId();
        ResponseDTO response = userService.getUserById(userId);
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        UserDTO infoAboutUser = (UserDTO) response.getDataObject();
        System.out.println(infoAboutUser.toString());
    }

    public void showAllUsersProcess() {
        System.out.println("All Users:");
        ResponseDTO response = userService.getAllUsers();
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<UserDTO> users = (List<UserDTO>) response.getDataObject();
        for (UserDTO user : users) {
            System.out.println(user.toString());
        }
        System.out.println("Total users: " + users.size());
    }

    public void setRoleProcess() {
        int userId = UserInputStatic.inputInt("Enter the id of the user : ");
        String role = UserInputStatic.inputText("Enter the role(ADMIN/USER) : ");
        ResponseDTO response = userService.setUserRole(userId, role);
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        System.out.println("Role: " + response.getMessage());
    }

    public void showUserTaskProcess() {
        ResponseDTO response = userService.getMyTasks();
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
        }
        List<TaskDTO> tasks = (List<TaskDTO>) response.getDataObject();
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        for (TaskDTO task : tasks) {
            System.out.println(task.toString());
        }
    }

    public void createNewTaskProcess() {
        String title = UserInputStatic.inputText("Enter the title of the task: ");
        String description = UserInputStatic.inputText("Enter the description of the task: ");
        LocalDate deadline = UserInputStatic.inputDate("Enter the deadline of the task: ");
        int projectId = UserInputStatic.inputInt("Enter the id of the project: ");
        RequestTaskDTO requestTaskDTO = new RequestTaskDTO(title, description, deadline, projectId);
        ResponseDTO responseDTO = taskService.addTask(requestTaskDTO);
        System.out.println(responseDTO.getMessage());
    }

    public void showAllTaskProcess() {
        System.out.println("All Tasks:");
        ResponseDTO response = taskService.findAllTasks();
        if (response.getCode() != 200) {
            System.out.println(response.getMessage());
            return;
        }
        List<TaskDTO> tasks = (List<TaskDTO>) response.getDataObject();
        for (TaskDTO task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("Total tasks: " + tasks.size());
    }

    public void getTasksForUserByIdProcess() {
        int userId = UserInputStatic.inputInt("Enter the id of the user : ");
        ResponseDTO responseDTO = userService.getTasksByUserId(userId);
        if (responseDTO.getCode() != 200) {
            System.out.println(responseDTO.getMessage());
            return;
        }
        List<TaskDTO> tasks = (List<TaskDTO>) responseDTO.getDataObject();
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }
        for (TaskDTO task : tasks) {
            System.out.println(task.toString());
        }
        System.out.println("Total tasks: " + tasks.size());
    }

    public void assignTaskToUserProcess() {
        int idTask = UserInputStatic.inputInt("Enter the id of the task: ");
        int idUser = UserInputStatic.inputInt("Enter the id of the user: ");
        ResponseDTO response = userService.assignTaskToUser(idTask, idUser);
        System.out.println(response.getMessage());
    }

    public void showAllUsersFromTaskIdProcess() {
        int idTask = UserInputStatic.inputInt("Enter the id of the task: ");
        ResponseDTO responseDTO = userService.getAllUserFromTaskId(idTask);
        if (responseDTO.getCode() != 200) {
            System.out.println(responseDTO.getMessage());
            return;
        }

        List<UserDTO> users = (List<UserDTO>) responseDTO.getDataObject();
        if (users == null || users.isEmpty()) {
            System.out.println("No users found for task with id: " + idTask);
            return;
        }
        System.out.println("Users for the tasks with id " + idTask + ": " + users );
        System.out.println("Total users: " + users.size());
    }



}
