package com.group0505team1.repository;

import com.group0505team1.entity.User;

import java.util.List;

public interface UserRepositoryInterface {
    void add(User user);                     // Создать или обновить пользователя
    User findById(int id);                    // Найти пользователя по ID
    User findByName(String name);             // Найти пользователя по имени
    List<User> findAll();                     // Получить всех пользователей
    boolean delete(int id);                   // Удалить пользователя по ID

}
