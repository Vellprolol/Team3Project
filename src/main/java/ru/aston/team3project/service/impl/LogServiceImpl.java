package ru.aston.team3project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.aston.team3project.entity.Log;
import ru.aston.team3project.repository.LogRepository;
import ru.aston.team3project.service.LogService;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    @Autowired
    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void saveOrUpdateLog(Log log) {
        logRepository.save(log);
    }

    @Override
    public List<Log> getStudentLogs(Long id) {
        return logRepository.getLogsByStudentStudentId(id).orElseThrow();
    }

    @Override
    public Log findLogById(Long id) {
        return logRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteLog(Log log) {
        logRepository.delete(log);
    }

    @Override
    public void deleteLogById(Long id) {
        logRepository.deleteById(id);
    }
}
