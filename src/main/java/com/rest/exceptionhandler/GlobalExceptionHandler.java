package com.rest.exceptionhandler;

import com.rest.exception.PersonAlreadyExistException;
import com.rest.exception.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<String> handleNotFoundException(PersonNotFoundException e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({PersonAlreadyExistException.class})
    public ResponseEntity<String> handleAlreadyExistsException(PersonAlreadyExistException e) {
        return error(CONFLICT, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}
