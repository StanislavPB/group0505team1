package com.group0505team1;

import com.group0505team1.auth.UserSecurity;
import com.group0505team1.entity.*;
import com.group0505team1.repository.*;
import com.group0505team1.service.*;
import com.group0505team1.userInterface.InitDB;
import com.group0505team1.userInterface.MainProcess;
import com.group0505team1.userInterface.UtilsProcess;

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

        InitDB initDB = new InitDB(userService, userRepository, projectRepository, taskService, messageService);
        initDB.init();

        mainProcess.run();
    }
}
