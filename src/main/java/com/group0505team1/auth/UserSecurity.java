package com.group0505team1.auth;

import com.group0505team1.entity.User;
import com.group0505team1.exception.AuthenticationException;
import com.group0505team1.exception.UserNotFoundException;
import com.group0505team1.repository.UserRepositoryInterface;

public class UserSecurity {
    private UserRepositoryInterface userRepository;

    public UserSecurity(UserRepositoryInterface userRepository) {
        this.userRepository = userRepository;
    }

    public User authenticate(String login, String password) {
        if(SessionContext.isAuthenticated()){
            throw new AuthenticationException("User already authenticated");
        }
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        String passwordHash = user.getPasswordHash();
        if (!PasswordUtil.checkPassword(password, passwordHash)) {
            throw new AuthenticationException("Invalid password");
        }
        SessionContext.setCurrentUser(user);
        return user;
    }

    public User register(String name, String login, String password, String passwordConfirm) {
        if(name == null || name.isBlank()){
            throw new AuthenticationException("Name is required");
        }
        if(login == null || login.isBlank() || login.length() < 4 || login.length() > 10){
            throw new AuthenticationException("Login is required or not in criteria");
        }
        if(password == null || password.isBlank() || password.length() < 5 || password.length() > 10){
            throw new AuthenticationException("Password is required or not in criteria");
        }
        if(!password.equals(passwordConfirm)){
            throw new AuthenticationException("Passwords do not match");
        }
        String passwordHash = PasswordUtil.hashPassword(password);
        User user = new User(name, login, passwordHash);

        return user;
    }

    public void logout(){
        if (!SessionContext.isAuthenticated()) {
            throw new AuthenticationException("User is not authenticated");
        }
        SessionContext.clear();
    }
}
