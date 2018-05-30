package com.example.userservice.logic;

import com.example.userservice.domain.User;
import com.example.userservice.domain.UserCreatedResponse;
import com.example.userservice.logic.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public UserCreatedResponse createUser(User user) {
        userDao.save(user);
        return new UserCreatedResponse(UUID.randomUUID().toString());
    }

    public User getUserById(String id) {
        return userDao.get(id);
    }

    public void deleteUserById(String id) {
        userDao.delete(id);
    }

    public List<User> getUsers() {
        return userDao.getAll();
    }
}
