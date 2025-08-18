package com.group0505team1.userInterface;

import com.group0505team1.dto.RequestMessageDTO;
import com.group0505team1.dto.RequestRegisterDTO;
import com.group0505team1.dto.RequestTaskDTO;
import com.group0505team1.entity.Project;
import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.User;
import com.group0505team1.repository.ProjectRepositoryInterface;
import com.group0505team1.repository.UserRepositoryInterface;
import com.group0505team1.service.*;

import java.time.LocalDate;

public class InitDB {

    private final UserServiceInterface userService;
    private final UserRepositoryInterface userRepository;
    private final ProjectRepositoryInterface projectRepository;
    private final TaskServiceInterface taskService;
    private final MessageServiceInterface messageService;

    public InitDB(UserServiceInterface userService,
                  UserRepositoryInterface userRepository,
                  ProjectRepositoryInterface projectRepository,
                  TaskServiceInterface taskService,
                  MessageServiceInterface messageService) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskService = taskService;
        this.messageService = messageService;
    }

    public void init(){
        userService.registerUser(new RequestRegisterDTO("Administrator", "admin", "passAdmin", "passAdmin"));
        User userAdmin = userRepository.findByLogin("admin");
        int adminId = userAdmin.getId();
        userRepository.setRole(userAdmin, RoleUser.ADMIN);
        userService.registerUser(new RequestRegisterDTO("User1", "user1", "passUser1", "passUser1"));
        userService.registerUser(new RequestRegisterDTO("User2", "user2", "passUser2", "passUser2"));

//        SessionContext.setCurrentUser(userRepository.findById(adminId));
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

        userService.assignTaskToUser(1, 3);
        userService.assignTaskToUser(2, 3);
        userService.assignTaskToUser(6, 3);

        messageService.sendMessage(new RequestMessageDTO("Hello User1", 1));
        messageService.sendMessage(new RequestMessageDTO("Hello User2", 2));
        messageService.sendMessage(new RequestMessageDTO("Hi !!!", 2));
        messageService.sendMessage(new RequestMessageDTO("Super!!!", 2));
    }
}
