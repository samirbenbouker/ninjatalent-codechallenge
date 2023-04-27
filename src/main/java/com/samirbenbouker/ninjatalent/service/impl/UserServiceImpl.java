package com.samirbenbouker.ninjatalent.service.impl;

import com.samirbenbouker.ninjatalent.model.User;
import com.samirbenbouker.ninjatalent.repository.UserRepository;
import com.samirbenbouker.ninjatalent.service.UserErrorEnum;
import com.samirbenbouker.ninjatalent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        if(!user.anyNull()) {
            throw new ResponseStatusException(
                    HttpStatus.METHOD_NOT_ALLOWED,
                    UserErrorEnum.USER_INVALID_INPUT.gerErrorMessage()
            );
        }

        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if(optionalUser.isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    UserErrorEnum.USER_ALREADY_EXIST.gerErrorMessage()
            );
        }

        return userRepository.save(user);
    }

    @Override
    public User getUserById(Optional<Integer> userId) {
        if(userId.isPresent()) {
            return userRepository.findById(userId.get())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            UserErrorEnum.USER_NOT_FOUND.gerErrorMessage()
                    ));

        }

        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage()
        );
    }

    @Override
    public User updateUserById(Optional<Integer> userId, User updatedUser) {
        if(userId.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage()
            );
        }

        if(!userId.get().equals(updatedUser.getUserId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    UserErrorEnum.USER_IDENTIFIER_MISMATCH.gerErrorMessage()
            );
        }

        User user = userRepository.findById(userId.get())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        UserErrorEnum.USER_NOT_FOUND.gerErrorMessage()
                ));

        if(updatedUser.getEmail() != null
                && updatedUser.getEmail().length() > 0
                && !Objects.equals(updatedUser.getEmail(), user.getEmail())
        ) {
            Optional<User> optionalUser = userRepository.findByEmail(updatedUser.getEmail());
            if(optionalUser.isPresent()) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        UserErrorEnum.USER_EMAIL_TAKEN.gerErrorMessage()
                );
            }
        }

        return updatedUser;
    }

    @Override
    public ResponseEntity<String> deleteUserById(Optional<Integer> userId) {
        if(userId.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage()
            );
        }

        userRepository.findById(userId.get())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        UserErrorEnum.USER_NOT_FOUND.gerErrorMessage()
                ));

        userRepository.deleteById(userId.get());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body("User deleted correctly");
    }
}

