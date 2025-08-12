package com.group0505team1.services;

import com.group0505team1.dto.RegistrationResult;
import com.group0505team1.entity.Role;
import com.group0505team1.entity.Task;
import com.group0505team1.entity.TaskStatus;
import com.group0505team1.entity.User;
import com.group0505team1.exception.AuthenticationException;
import com.group0505team1.exception.UserAlreadyExistsException;
import com.group0505team1.exception.UserNotFoundException;
import com.group0505team1.repository.TaskRepository;
import com.group0505team1.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    public UserService(UserRepository userRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    public RegistrationResult register(String name, String password, Role role) {
        if (userRepository.findByName(name) != null) {
            throw new UserAlreadyExistsException("Пользователь с таким именем уже существует");
        }
        User user = new User(name, password, role, new ArrayList<>());
        userRepository.save(user);
        return new RegistrationResult(true, "Регистраия прошла успешно!");
    }

    public User auth(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            throw new AuthenticationException("Пользователь не найден");
        }
        if (!user.getPassword().equals(password)) {
            throw new AuthenticationException("Неверный пароль!");
        }
        return user;
    }

    public User findUserById(int id) {
        return Optional.ofNullable(userRepository.findById(id))
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID : " + id + " не найден!"));
    }


    public User findByName(String name) {
        return Optional.ofNullable(userRepository.findByName(name))
                .orElseThrow(() -> new UserNotFoundException("Пользователь с именем '" + name + "' не найден!"));
    }

    public List<Task> getAllUserTasks(String name) {
        User user = findByName(name);
        return taskRepository.findByUserId(user.getId());
    }

    //    public List<Task> getTasksByFilter(int userId, TaskStatus statusFilter, Integer priority) {
//        List<Task> tasks = getAllUserTasks(findUserById(userId).getName());
//
//        return tasks.stream()
//                .filter(task -> (statusFilter == null || task.getStatus() == statusFilter))
//                .filter(task -> (priority == null || task.getPriority() == priority))
//                .collect(Collectors.toList());
//    }

    public List<Task> getTasksByFilter(int userId, TaskStatus statusFilter, Integer priority) {
        return getAllUserTasks(findUserById(userId).getName()).stream()
                .filter(task -> Optional.ofNullable(statusFilter)
                        .map(status -> task.getStatus() == status)
                .orElse(true))
                .filter(task ->  Optional.ofNullable(priority)
                        .map(p -> task.getPriority() == p)
                        .orElse(true))
                .toList();
    }

}
