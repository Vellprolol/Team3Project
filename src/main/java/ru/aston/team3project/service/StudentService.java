package ru.aston.team3project.service;

import ru.aston.team3project.entity.Student;

import java.util.List;

public interface StudentService {
    void saveOrUpdateStudent(Student student);
    List<Student> getAllStudents();
    Student findStudentById(Long id);
    void deleteStudent(Student student);
    void deleteStudentById(Long id);
}
