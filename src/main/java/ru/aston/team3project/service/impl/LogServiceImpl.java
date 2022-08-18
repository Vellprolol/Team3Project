package ru.aston.team3project.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.aston.team3project.entity.Log;
import ru.aston.team3project.exception_handling.NoSuchDataException;
import ru.aston.team3project.repository.LogRepository;
import ru.aston.team3project.service.LogService;

import java.util.Optional;

@Service
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private static Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);

    public LogServiceImpl(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void saveOrUpdateLog(Log log) {
        logger.info("Log to save {}", log);
        logRepository.save(log);
    }

    @Override
    public Optional<Log> findLogById(Long id) {
        Optional<Log> log = logRepository.findById(id);
        if (log.isPresent()) {
            logger.info("Log {}", log.get());
            return log;
        } else {
            throw new NoSuchDataException("Не существует лога под номером " + id);
        }
    }
}