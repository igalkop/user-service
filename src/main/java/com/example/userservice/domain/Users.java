package com.example.userservice.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Users {
    List<User> userList = new ArrayList<>();
}
