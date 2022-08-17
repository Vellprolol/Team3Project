package ru.aston.team3project.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<IncorrectRequest> handleException(NoSuchDataException e) {
        IncorrectRequest incorrectData = new IncorrectRequest();
        incorrectData.setInfo(e.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectRequest> handleException(Exception e) {
        IncorrectRequest incorrectData = new IncorrectRequest();
        incorrectData.setInfo(e.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectRequest> handleException(AccessRightException e) {
        IncorrectRequest incorrectData = new IncorrectRequest();
        incorrectData.setInfo(e.getMessage());
        return new ResponseEntity<>(incorrectData, HttpStatus.UNAUTHORIZED);
    }
}
