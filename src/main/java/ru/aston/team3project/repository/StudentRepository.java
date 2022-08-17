package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import ru.aston.team3project.entity.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Override
    @NonNull
    List<Student> findAll();

}
