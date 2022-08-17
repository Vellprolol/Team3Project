package ru.aston.team3project.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.team3project.entity.Role;
import ru.aston.team3project.entity.User;
import ru.aston.team3project.repository.RoleRepository;
import ru.aston.team3project.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Transactional
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
        if (user.isPresent()){
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
        log.info("saving user: {}", user);
        userRepository.save(user);
    }

    @Override
    public void saveRole(Role role) {
        log.info("saving role: {}", role);
        roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent()){
            Optional<Role> role = roleRepository.findByName(roleName);
            if (role.isPresent()){
                log.info("adding role({}) to user({})", roleName, username);
                user.get().getRoles().add(role.get());

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

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

}
