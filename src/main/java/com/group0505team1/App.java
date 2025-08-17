package com.group0505team1;

import com.group0505team1.auth.UserSecurity;
import com.group0505team1.dto.RequestProjectDTO;
import com.group0505team1.dto.RequestRegisterDTO;
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
        UtilsProcess utilsProcess = new UtilsProcess(userService, taskService, projectService, messageService);
        MainProcess mainProcess = new MainProcess(utilsProcess);

        userService.registerUser(new RequestRegisterDTO("Administrator", "admin", "passAdmin", "passAdmin"));
        User userAdmin = userRepository.findByLogin("admin");
        int adminId = userAdmin.getId();
        userRepository.setRole(userAdmin, RoleUser.ADMIN);
        userService.registerUser(new RequestRegisterDTO("User1", "user1", "passUser1", "passUser1"));
        userService.registerUser(new RequestRegisterDTO("User2", "user2", "passUser2", "passUser2"));

        projectRepository.add(new Project("Project1", "First project of team 1"));
        projectRepository.add(new Project("Project2", "First project of team 2"));
//        projectRepository.addUserToProject(1, 2);
//        projectRepository.addUserToProject(2, 3);

        taskRepository.add(new Task("Task1", "Description1", TaskStatus.TODO, TaskPriority.LOW, LocalDate.of(2025, 8, 19), 1));
        taskRepository.add(new Task("Task2", "Description2", TaskStatus.TODO, TaskPriority.LOW, LocalDate.of(2025, 7, 13), 1));
        taskRepository.add(new Task("Task3", "Description3", TaskStatus.TODO, TaskPriority.LOW, LocalDate.of(2025, 8, 15), 2));
        taskRepository.add(new Task("Task4", "Description4", TaskStatus.TODO, TaskPriority.LOW, LocalDate.of(2025, 8, 19), 2));
        taskRepository.add(new Task("Task5", "Description5", TaskStatus.TODO, TaskPriority.LOW, LocalDate.of(2025, 9, 01), 1));



        mainProcess.run();
    }
}
