package ru.aston.team3project.service;

import ru.aston.team3project.entity.Log;

import java.util.List;

public interface LogService {
    void saveOrUpdateLog(Log log);
    List<Log> getStudentLogs(Long id);
    Log findLogById(Long id);
    void deleteLog(Log log);
    void deleteLogById(Long id);
}
