package com.samirbenbouker.ninjatalent.service;

import com.samirbenbouker.ninjatalent.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getUsers();

    User createUser(User user);

    User getUserById(Optional<Integer> userId);

    User updateUserById(Optional<Integer> userId, User user);

    ResponseEntity<String> deleteUserById(Optional<Integer> userId);
}
