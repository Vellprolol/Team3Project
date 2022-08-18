package ru.aston.team3project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;


@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id", nullable = false)
    private Long logId;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm:ss")
    @Column(name = "updateDateTime")
    private LocalDateTime updateDateTime;

    @Lob
    @Column(name = "message", nullable = false)
    private String message;


    public Log() {
    }

    public Log(String message) {
        this.message = message;
    }

    public Log(Student student, String message) {
        this.student = student;
        this.message = message;
    }

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public Student getStudent() {
        return student;
    }

    public void setUser(Student user) {
        this.student = student;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Log)) return false;
        Log log = (Log) o;
        return Objects.equals(getLogId(), log.getLogId()) && Objects.equals(getStudent(), log.getStudent()) && Objects.equals(getUpdateDateTime(), log.getUpdateDateTime()) && Objects.equals(getMessage(), log.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLogId(), getStudent(), getUpdateDateTime(), getMessage());
    }

    @Override
    public String toString() {
        return "Log{" +
                "logId=" + logId +
                ", updateDateTime=" + updateDateTime +
                ", message='" + message + '\'' +
                '}';
    }
}
