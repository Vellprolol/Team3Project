package ru.aston.team3project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.aston.team3project.entity.Student;
import ru.aston.team3project.exception_handling.NoSuchDataException;
import ru.aston.team3project.repository.StudentRepository;
import ru.aston.team3project.service.StudentService;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveOrUpdateStudent(Student student) {
        logger.info("Saved student {}", student);
        studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        logger.info("Getting all students");
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            logger.info("Student {}", student.get());
            return student;
        } else {
            throw new NoSuchDataException("Не существует студента с номером " + id);
        }
    }

    @Override
    public void deleteStudent(Student student) {
        logger.info("Student to delete {}", student);
        studentRepository.delete(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student student = findStudentById(id).get();
        logger.info("Student to delete {}", student);
        studentRepository.delete(student);
    }
}