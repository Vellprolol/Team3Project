package ru.aston.team3project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.aston.team3project.dto.LogResponseDTO;
import ru.aston.team3project.dto.LogUpdateDTO;
import ru.aston.team3project.entity.Log;
import ru.aston.team3project.mapper.LogMapper;
import ru.aston.team3project.service.LogService;

@RestController
@RequestMapping("/logs")
public class LogController {

    private final LogService logService;
    private final LogMapper logMapper;

    public LogController(LogService logService, LogMapper logMapper) {
        this.logService = logService;
        this.logMapper = logMapper;
    }

    @PostMapping("/{id}/update")
    public void updateLog(@RequestBody LogUpdateDTO logUpdateDTO, @PathVariable Long id) {
        Log log = logService.findLogById(id).get();
        if (logUpdateDTO.getMessage() != null) {
            log.setMessage(logUpdateDTO.getMessage());
        }
        logService.saveOrUpdateLog(log);
    }

    @GetMapping("/{id}")
    public LogResponseDTO getLogById(@PathVariable Long id) {
        return logMapper.mapToResponseDTO(logService.findLogById(id).get());
    }
}
