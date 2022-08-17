package ru.aston.team3project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.team3project.entity.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {

}
