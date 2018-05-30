package com.example.userservice.controller;

import com.example.userservice.domain.User;
import com.example.userservice.domain.UserCreatedResponse;
import com.example.userservice.domain.Users;
import com.example.userservice.logic.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserCreatedResponse> createUser(@RequestBody User user) {
        System.out.println("Creating a user " + user);
        UserCreatedResponse userCreatedResponse = userService.createUser(user);
        return new ResponseEntity<>(userCreatedResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        System.out.println("get user with id " + id);
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<Users> getUsers() {
        System.out.println("get all users");
        List<User> users = userService.getUsers();
        Users userResonse = new Users();
        userResonse.getUserList().addAll(users);
        return new ResponseEntity<>(userResonse, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable String id) {
        System.out.println("delete user with id" + id);
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
