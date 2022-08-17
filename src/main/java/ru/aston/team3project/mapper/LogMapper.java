package ru.aston.team3project.mapper;

import org.springframework.stereotype.Component;
import ru.aston.team3project.dto.LogResponseDTO;
import ru.aston.team3project.entity.Log;

@Component
public class LogMapper {

    public LogResponseDTO mapToResponseDTO(Log log) {
        return new LogResponseDTO(log.getLogId(), log.getMessage(),
                log.getUpdateDateTime(), log.getStudent().getStudentId());
    }
}
