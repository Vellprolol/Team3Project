package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.team3project.entity.Log;

public interface LogRepository extends JpaRepository<Log, Long> {
}
