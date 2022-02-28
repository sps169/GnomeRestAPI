package com.example.firststepsspring.exceptions.gnome;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GnomeNotFoundException extends RuntimeException {
    public GnomeNotFoundException(String id) {
        super("Unable to find gnome with id " + id);
    }
}
