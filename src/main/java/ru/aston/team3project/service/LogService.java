package ru.aston.team3project.service;

import ru.aston.team3project.entity.Log;

import java.util.Optional;

public interface LogService {

    void saveOrUpdateLog(Log log);


    Optional<Log> findLogById(Long id);

}
