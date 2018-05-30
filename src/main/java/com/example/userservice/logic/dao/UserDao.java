package com.example.userservice.logic.dao;

import com.example.userservice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDao {

    List<User> users = new ArrayList<>();

    public void save(User user) {
        Optional<User> existingUser = findUserById(Integer.toString(user.getId()));
        if(existingUser.isPresent()) {
            throw new UserAlreadyExistsException(Integer.toString(user.getId()));
        }
        users.add(user);
    }

    private Optional<User> findUserById(String userId) {
        int id = Integer.parseInt(userId);

        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public User get(String id) {
        Optional<User> optionalUser = findUserById(id);
        return optionalUser.orElseThrow(() -> new UserNotFoundException(id));
    }

    public void delete(String id) {
        Optional<User> optionalUser = findUserById(id);
        optionalUser.ifPresent(user -> users.remove(user));
    }

    public List<User> getAll() {
        return users;

    }
}
