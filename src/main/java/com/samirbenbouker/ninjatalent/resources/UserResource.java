package com.samirbenbouker.ninjatalent.resources;

import com.samirbenbouker.ninjatalent.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public interface UserResource {

    @GetMapping(value = "/getusers", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    List<User> getUsers();

    @PostMapping("/createUsers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "405", description = "Invalid input"),
            @ApiResponse(responseCode = "409", description = "User already exist")
    })
    User createUsers(@RequestBody User user);

    @GetMapping(value = {"/getusersById","/getusersById/{userId}"})
    @Operation(summary = "Get one user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid user id"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    User getUserById(@PathVariable Optional<Integer> userId);

    @PutMapping(value = {"/updateUsersById", "/updateUsersById/{userId}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid user id"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "Email Taken")
    })
    User updateUserById(@PathVariable Optional<Integer> userId, @RequestBody User user);

    @DeleteMapping(value = { "/deleteUsersById","/deleteUsersById/{userId}"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Invalid user id"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    ResponseEntity deleteUserById(@PathVariable Optional<Integer> userId);
}
