package ru.aston.team3project.service;

import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;

import java.util.Optional;

public interface UserService {
    void saveUser(User user);

    void saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    Optional<User> getUser(String username);
}
