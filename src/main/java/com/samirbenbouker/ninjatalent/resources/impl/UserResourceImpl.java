package com.samirbenbouker.ninjatalent.resources.impl;

import com.samirbenbouker.ninjatalent.model.User;
import com.samirbenbouker.ninjatalent.resources.UserResource;
import com.samirbenbouker.ninjatalent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class UserResourceImpl implements UserResource {

    @Autowired
    private UserService userService;

    @Override
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @Override
    public User createUsers(User user) {
        return userService.createUser(user);
    }

    @Override
    public User getUserById(Optional<Integer> userId) {
        return userService.getUserById(userId);
    }

    @Override
    public User updateUserById(Optional<Integer> userId, User user) {
        return userService.updateUserById(userId, user);
    }

    @Override
    public ResponseEntity deleteUserById(Optional<Integer> userId) {
        return userService.deleteUserById(userId);
    }
}
