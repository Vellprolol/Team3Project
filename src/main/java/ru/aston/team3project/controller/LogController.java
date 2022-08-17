package ru.aston.team3project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.aston.team3project.entity.Log;
import ru.aston.team3project.service.LogService;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;

    @Autowired
    public LogController(LogService logService) {
        this.logService = logService;
    }

    @PostMapping
    public void saveOrUpdateLog(@RequestBody Log log) {
        logService.saveOrUpdateLog(log);
    }

    @GetMapping("/{studentId}")
    public List<Log> getStudentLogs(@PathVariable Long studentId) {
        return logService.getStudentLogs(studentId);
    }
}
