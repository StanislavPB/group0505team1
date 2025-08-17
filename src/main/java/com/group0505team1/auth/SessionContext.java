package com.group0505team1.auth;

import com.group0505team1.entity.RoleUser;
import com.group0505team1.entity.User;

public class SessionContext {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }

    public static boolean isAuthenticated() {
        return currentUser != null;
    }

    public static boolean isAdmin() {
        return currentUser.getRoleUser()== RoleUser.ADMIN;
    }
}