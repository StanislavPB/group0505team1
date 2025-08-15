package com.group0505team1.userInterface;

public class MainProcess {
    public void run() {
        boolean isRunning = true;
        while (isRunning) {
        }
    }


    private void showAuthMenu(){
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Logout");
        System.out.println("0. Exit program");
    }

    private void showProjectMenu(){
        System.out.println("1. Show my projects");
        System.out.println("2. Show all projects");
        System.out.println("3. Create new project (Admin only)");
        System.out.println("4. Assign user to project (Admin only)");
        System.out.println("5. Assign task to project (Admin only)");
        System.out.println("6. Show tasks for project (Admin only)");
        System.out.println("7. Show users for project (Admin only)");
        System.out.println("0. Exit menu");
    }

    private void showTaskMenu(){
        System.out.println("1. Show my tasks");
        System.out.println("2. Create new task");
        System.out.println("3. Show all tasks (Admin only)");
        System.out.println("4. Show tasks for user (Admin only)");
        System.out.println("5. Assign task to user (Admin only)");
        System.out.println("6. Show users for task (Admin only)");
        System.out.println("0. Exit menu");
    }

    private void showUserMenu(){
        System.out.println("1. Show my profile");
        System.out.println("2. Show all users");
        System.out.println("3. Set user role (Admin only)");
        System.out.println("0. Exit menu");
    }

    private void showMessageMenu(){
        System.out.println("1. Send message");
        System.out.println("2. Show my incoming messages");
        System.out.println("3. Show my outgoing messages");
        System.out.println("1. Show all messages (Admin only)");
        System.out.println("0. Exit menu");
    }

    private void showStatisticMenu(){
        System.out.println("1. Show common projects statistics");
        System.out.println("2. Show common tasks statistics");
        System.out.println("3. Show common users statistics");
        System.out.println("0. Exit menu");
    }

}
