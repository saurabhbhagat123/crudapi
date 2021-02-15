package com.rest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class PersonAlreadyExistException extends RuntimeException {

    private String message;

    public PersonAlreadyExistException(String message){
        super(message);
    }

}
