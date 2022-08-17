package ru.aston.team3project.dto;

import ru.aston.team3project.entity.Log;

import java.util.List;

public class StudentUpdateDTO {
    private String name;
    private List<Log> logs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> logs) {
        this.logs = logs;
    }
}
