package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.aston.team3project.entity.Log;

import java.util.List;
import java.util.Optional;

public interface LogRepository extends JpaRepository<Log, Long> {

    Optional<List<Log>> getLogsByStudentStudentId(Long id);

}
