package ru.aston.team3project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.aston.team3project.dtos.RoleToUserDTO;
import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;
import ru.aston.team3project.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/user/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
    }

    @PostMapping("/role/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role){
        userService.saveRole(role);
    }

    @PostMapping("/role/addtouser")
    @ResponseStatus(HttpStatus.OK)
    public void addRoleToUser(@RequestBody RoleToUserDTO form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
    }


}
