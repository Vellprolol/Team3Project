package ru.aston.team3project.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.aston.team3project.dto.RoleToUserDTO;
import ru.aston.team3project.dto.UserCreateDTO;
import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;
import ru.aston.team3project.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public ApiController(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserCreateDTO userCreateDTO){
        User user = new User(userCreateDTO.getUsername(), passwordEncoder.encode(userCreateDTO.getPassword()));
        userService.saveUser(user);
    }

    @PostMapping("/role/save")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveRole(@RequestBody Role role){
        role.setId(null);
        userService.saveRole(role);
    }

    @PostMapping("/role/addtouser")
    @ResponseStatus(HttpStatus.OK)
    public void addRoleToUser(@RequestBody RoleToUserDTO form){
        userService.addRoleToUser(form.getUsername(), form.getRolename());
    }


}
