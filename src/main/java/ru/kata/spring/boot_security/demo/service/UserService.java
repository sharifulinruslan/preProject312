package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    public List<User> listUsers();

    public void saveUser(User user);

    public void deleteUser(int id);

    public User getUserById(int id);

    public void updateUser(User user);
}
