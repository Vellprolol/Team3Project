package ru.aston.team3project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;
import ru.aston.team3project.repository.RoleRepository;
import ru.aston.team3project.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            user.get().getRoles().forEach(role ->
                    authorities.add(new SimpleGrantedAuthority(role.getName()))
            );
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(), authorities);
        } else
            throw new UsernameNotFoundException("User not found");
    }

    @Override
    public void saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            addRoleToUser(user.getUsername(), "ROLE_USER");
            log.info("saved user: {}", user.getUsername());
        } else
            log.info("username: {} already exists", user.getUsername());

    }

    @Override
    public void saveRole(Role role) {
        Optional<Role> roleInDB = roleRepository.findByName(role.getName());
        if (roleInDB.isEmpty()) {
            roleRepository.save(role);
            log.info("saved role: {}", role.getName());
        } else
            log.info("role: {} already exists", role.getName());
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            Optional<Role> role = roleRepository.findByName(roleName);
            if (role.isPresent()) {
                if (user.get().getRoles().stream()
                        .filter(roleInArr -> roleInArr.getName().equals(role.get().getName()))
                        .findFirst().isEmpty()) {
                    log.info("adding role({}) to user({})", roleName, username);
                    user.get().getRoles().add(role.get());
                    userRepository.save(user.get());
                } else
                    log.info("user({}) already has role({})", username, roleName);
            } else
                log.info("role({}) not found", roleName);

        } else
            log.info("user({}) not found", username);

    }

    @Override
    public Optional<User> getUser(String username) {
        log.info("getting user: {}", username);
        return userRepository.findByUsername(username);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}
