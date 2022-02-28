package com.example.firststepsspring.exceptions.sweet;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SweetNotFoundException extends RuntimeException {
    public SweetNotFoundException(String id) {
        super("Unable to find sweet with id " + id);
    }
}
