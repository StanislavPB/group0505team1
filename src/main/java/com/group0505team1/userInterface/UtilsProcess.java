package com.group0505team1.userInterface;

import com.group0505team1.Utils.UserInputStatic;
import com.group0505team1.dto.*;
import com.group0505team1.entity.Project;
import com.group0505team1.service.MessageService;
import com.group0505team1.service.ProjectService;
import com.group0505team1.service.TaskService;
import com.group0505team1.service.UserService;

import java.util.List;

public class UtilsProcess {
    private final UserService userService;
    private final TaskService taskService;
    private final ProjectService projectService;
    private final MessageService messageService;

    public UtilsProcess(UserService userService, TaskService taskService, ProjectService projectService, MessageService messageService) {
        this.userService = userService;
        this.taskService = taskService;
        this.projectService = projectService;
        this.messageService = messageService;
    }


    public void login() {
        String login = UserInputStatic.inputText("Enter your login: ");
        String password = UserInputStatic.inputText("Enter your password: ");
        RequestAuthDTO requestAuthDTO = new RequestAuthDTO(login, password);
        ResponseDTO response = userService.authenticate(requestAuthDTO);
        System.out.println(response.getMessage());
    }

    public void register(){
        String name = UserInputStatic.inputText("Enter your name: ");
        String login = UserInputStatic.inputText("Enter your login: ");
        String password = UserInputStatic.inputText("Enter your password: ");
        String confirmPassword = UserInputStatic.inputText("Confirm your password: ");
        RequestRegisterDTO requestRegisterDTO = new RequestRegisterDTO(name, login, password, confirmPassword);
        ResponseDTO response = userService.registerUser(requestRegisterDTO);
        System.out.println(response.getMessage());
    }

    public void logout(){
        ResponseDTO response = userService.logout();
        System.out.println(response.getMessage());
    }

    public void showMyProjectsProcess(){
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

    public void showAllProjectsProcess(){
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
}
