package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.team3project.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
