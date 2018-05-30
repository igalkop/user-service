package com.example.userservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedResponse {
    String newUserKey;
}
