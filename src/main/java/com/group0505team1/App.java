package com.group0505team1;

import com.group0505team1.auth.SessionContext;
import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.RequestProjectDTO;
import com.group0505team1.dto.RequestRegisterDTO;
import com.group0505team1.dto.RequestTaskDTO;
import com.group0505team1.entity.*;
import com.group0505team1.repository.*;
import com.group0505team1.service.*;
import com.group0505team1.userInterface.MainProcess;
import com.group0505team1.userInterface.UtilsProcess;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        UserRepositoryInterface userRepository = new UserRepository();
        TaskRepositoryInterface taskRepository = new TaskRepository();
        ProjectRepositoryInterface projectRepository = new ProjectRepository();
        MessageRepositoryInterface messageRepository = new MessageRepository();
        UserSecurity userSecurity = new UserSecurity(userRepository);
        TaskService taskService = new TaskService(taskRepository, userSecurity);
        UserService userService = new UserService(userRepository, taskService, userSecurity);
        ProjectService projectService = new ProjectService(projectRepository, taskService, userService);
        taskService.setProjectService(projectService);
        MessageService messageService = new MessageService(messageRepository, userSecurity, userService);
        StatisticServiceInterface statisticService = new StatisticService(userService, taskService, projectService);
        UtilsProcess utilsProcess = new UtilsProcess(userService, taskService, projectService, messageService, statisticService);
        MainProcess mainProcess = new MainProcess(utilsProcess);

        userService.registerUser(new RequestRegisterDTO("Administrator", "admin", "passAdmin", "passAdmin"));
        User userAdmin = userRepository.findByLogin("admin");
        int adminId = userAdmin.getId();
        userRepository.setRole(userAdmin, RoleUser.ADMIN);
        userService.registerUser(new RequestRegisterDTO("User1", "user1", "passUser1", "passUser1"));
        userService.registerUser(new RequestRegisterDTO("User2", "user2", "passUser2", "passUser2"));

        SessionContext.setCurrentUser(userRepository.findById(adminId));
        projectRepository.add(new Project("Project1", "First project of team 1"));
        projectRepository.add(new Project("Project2", "First project of team 2"));
        projectRepository.addUserToProject(1, userRepository.findById(2));
        projectRepository.addUserToProject(2, userRepository.findById(3));

        taskService.addTask(new RequestTaskDTO("Task1", "Description1", LocalDate.of(2025, 8, 19), 1));
        taskService.addTask(new RequestTaskDTO("Task2", "Description2", LocalDate.of(2025, 7, 13), 1));
        taskService.addTask(new RequestTaskDTO("Task3", "Description3", LocalDate.of(2025, 8, 15), 2));
        taskService.addTask(new RequestTaskDTO("Task4", "Description4", LocalDate.of(2025, 8, 19), 2));
        taskService.addTask(new RequestTaskDTO("Task5", "Description5", LocalDate.of(2025, 9, 01), 1));
        taskService.addTask(new RequestTaskDTO("Task6", "Description6", LocalDate.of(2025, 8, 19), 1));


        mainProcess.run();
    }
}
