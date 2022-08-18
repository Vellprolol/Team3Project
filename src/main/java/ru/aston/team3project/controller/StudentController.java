package ru.aston.team3project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.team3project.dto.LogUpdateDTO;
import ru.aston.team3project.dto.StudentUpdateDTO;
import ru.aston.team3project.entity.Log;
import ru.aston.team3project.entity.Student;
import ru.aston.team3project.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id).get();
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @PostMapping("/create")
    public void saveStudent(@RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.saveOrUpdateStudent(new Student(studentUpdateDTO.getName()));
    }

    @PostMapping("/{id}/update")
    public void updateStudent(@PathVariable Long id, @RequestBody StudentUpdateDTO studentUpdateDTO) {
        Student student = studentService.findStudentById(id).get();
        if (studentUpdateDTO.getName() != null) {
            student.setName(studentUpdateDTO.getName());
        }
        if (studentUpdateDTO.getLogs() != null) {
            student.setLogs(studentUpdateDTO.getLogs());
        }
        studentService.saveOrUpdateStudent(student);
    }

    @PostMapping("/{id}/logs/add")
    public void addLogToStudent(@PathVariable Long id, @RequestBody LogUpdateDTO logUpdateDTO) {
        Optional<Student> student = studentService.findStudentById(id);

        if (student.isPresent()) {
            student.get().getLogs().add(new Log(student.get(), logUpdateDTO.getMessage()));
            studentService.saveOrUpdateStudent(student.get());
        }

    }

    @GetMapping("/{id}/logs")
    public List<Log> getStudentLogs(@PathVariable Long id) {
        Student student = studentService.findStudentById(id).get();
        return student.getLogs();
    }

    @DeleteMapping("/{id}/delete")
    public void deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
    }
}

