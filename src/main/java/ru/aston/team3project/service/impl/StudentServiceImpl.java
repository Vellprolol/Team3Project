package ru.aston.team3project.service.impl;

import org.springframework.stereotype.Component;
import ru.aston.team3project.entity.Student;
import ru.aston.team3project.repository.StudentRepository;
import ru.aston.team3project.service.StudentService;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
