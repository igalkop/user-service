package com.example.userservice.logic.dao;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyExistsException extends RuntimeException {

    UserAlreadyExistsException(String userId) {
        super("user " + userId + " already exists");
    }

}
