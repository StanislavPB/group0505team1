package com.group0505team1.userInterface;

import com.group0505team1.Utils.UserInputStatic;
import com.group0505team1.auth.SessionContext;

public class MainProcess {
    private final UtilsProcess utilsProcess;

    public MainProcess(UtilsProcess utilsProcess) {
        this.utilsProcess = utilsProcess;
    }

    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            showAuthMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.login();
                case "2" -> utilsProcess.register();
                case "3" -> utilsProcess.logout();
                case "P" -> projectsMenu();
                case "T" -> tasksMenu();
                case "U" -> usersMenu();
                case "M" -> messagesMenu();
                case "S" -> statisticsMenu();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private void projectsMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showProjectMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.showMyProjectsProcess();
                case "2" -> utilsProcess.showAllProjectsProcess();
                case "3" -> utilsProcess.createNewProjectProcess();
                case "4" -> utilsProcess.assignUserToProjectProcess();
                case "5" -> utilsProcess.assignTaskToProjectProcess();
                case "6" -> utilsProcess.showTasksForProjectProcess();
                case "7" -> utilsProcess.showUsersForProjectProcess();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");

            }
        }

    }

    private void usersMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showUserMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.showUserProfileProcess();
                case "2" -> utilsProcess.showAllUsersProcess();
                case "3" -> utilsProcess.setRoleProcess();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private void tasksMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showTaskMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.showUserTaskProcess();
                case "2" -> utilsProcess.createNewTaskProcess();
                case "3" -> utilsProcess.showAllTaskProcess();
                case "4" -> utilsProcess.getTasksForUserByIdProcess();
                case "5" -> utilsProcess.assignTaskToUserProcess();
                case "6" -> utilsProcess.showAllUsersFromTaskIdProcess();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");
            }
        }
    }


    private void showAuthMenu() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        if (SessionContext.isAuthenticated()) {
            System.out.println("P. Show project menu");
            System.out.println("T. Show task menu");
            System.out.println("U. Show user menu");
            System.out.println("M. Show message menu");
            System.out.println("S. Show statistic menu");
            System.out.println("3. Logout");
        }
        System.out.println("0. Exit program");
    }

    private void showProjectMenu() {
        System.out.println("1. Show my projects");
        System.out.println("2. Show all projects");
        System.out.println("3. Create new project (Admin only)");
        System.out.println("4. Assign user to project (Admin only)");
        System.out.println("5. Assign task to project (Admin only)");
        System.out.println("6. Show tasks for project (Admin only)");
        System.out.println("7. Show users for project (Admin only)");
        System.out.println("0. Exit menu");
    }


    private void showTaskMenu() {
        System.out.println("1. Show my tasks");
        if (SessionContext.isAdmin()) {
            System.out.println("2. Create new task (Admin only)");
            System.out.println("3. Show all tasks (Admin only)");
            System.out.println("4. Show tasks for user (Admin only)");
            System.out.println("5. Assign task to user (Admin only)");
            System.out.println("6. Show users for task (Admin only)");
        }
        System.out.println("0. Exit menu");
    }

    private void showUserMenu() {
        System.out.println("1. Show my profile");
        System.out.println("2. Show all users ");
        if (SessionContext.isAdmin()) {
            System.out.println("3. Set user role (Admin only)");
        }
            System.out.println("0. Exit menu");
    }

    private void showMessageMenu() {
        System.out.println("1. Send message");
        System.out.println("2. Show my incoming messages");
        System.out.println("3. Show my outgoing messages");
        if (SessionContext.isAdmin()) {
            System.out.println("4. Show all messages (Admin only)");
        }
        System.out.println("0. Exit menu");
    }

    private void messagesMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showMessageMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.sendMessageProcess();
                case "2" -> utilsProcess.showIncomingMessages();
                case "3" -> utilsProcess.showOutgoingMessages();
                case "4" -> utilsProcess.showAllMessages();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private void showStatisticMenu() {
        System.out.println("1. Show common projects statistics");
        System.out.println("2. Show common tasks statistics");
        System.out.println("3. Show common users statistics");
        System.out.println("0. Exit menu");
    }

    private void statisticsMenu() {
        boolean isRunning = true;
        while (isRunning) {
            showStatisticMenu();
            printSeparator();
            String command = UserInputStatic.inputText("Enter the command:").toUpperCase();
            switch (command) {
                case "1" -> utilsProcess.showCommonProjectStatistics();
                case "2" -> utilsProcess.showCommonTaskStatistics();
                case "3" -> utilsProcess.showCommonUserStatistics();
                case "0" -> isRunning = false;
                default -> System.out.println("Invalid command!");
            }
        }
    }

    private void printSeparator() {
        System.out.println("----------------------------------------");
    }

}
