package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.team3project.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
