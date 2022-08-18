package ru.aston.team3project.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;
import ru.aston.team3project.service.UserServiceImpl;

@Component
public class InitializingBeanDB implements InitializingBean {

    private static Logger log = LoggerFactory.getLogger(InitializingBeanDB.class);

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    public InitializingBeanDB(UserServiceImpl userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("initializing startup roles and users");
        userService.saveRole(new Role("ROLE_USER"));
        userService.saveRole(new Role("ROLE_MODERATOR"));
        userService.saveRole(new Role("ROLE_ADMIN"));

        userService.saveUser(new User("danil9", passwordEncoder.encode("123")));
        userService.saveUser(new User("testModerator", passwordEncoder.encode("123")));
        userService.saveUser(new User("testAdmin", passwordEncoder.encode("123")));

        userService.addRoleToUser("testModerator", "ROLE_MODERATOR");
        userService.addRoleToUser("testAdmin", "ROLE_MODERATOR");
        userService.addRoleToUser("testAdmin", "ROLE_ADMIN");
    }
}
