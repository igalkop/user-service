package com.example.userservice.logic.dao;

import org.omg.SendingContext.RunTime;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException  extends RuntimeException {
    public UserNotFoundException(String id) {
        super("user " + id + " was not found");
    }
}
