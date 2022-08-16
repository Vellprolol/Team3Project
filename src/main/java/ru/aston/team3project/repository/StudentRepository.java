package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.team3project.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
