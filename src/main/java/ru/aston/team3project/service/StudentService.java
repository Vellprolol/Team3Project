package ru.aston.team3project.service;

import ru.aston.team3project.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    void saveOrUpdateStudent(Student student);

    List<Student> getAllStudents();

    Optional<Student> findStudentById(Long id);

    void deleteStudent(Student student);

    void deleteStudentById(Long id);

}
