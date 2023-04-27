package com.samirbenbouker.ninjatalent;

import com.samirbenbouker.ninjatalent.model.User;
import com.samirbenbouker.ninjatalent.repository.AddressRepository;
import com.samirbenbouker.ninjatalent.repository.UserRepository;
import com.samirbenbouker.ninjatalent.resources.impl.UserResourceImpl;
import com.samirbenbouker.ninjatalent.service.UserErrorEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private UserResourceImpl userResource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    private final Integer EXISTING_USER_ID = 1;
    private final String EXISTING_USER_EMAIL = "samir@gmail.com";
    private final Integer USER_ID_NOT_EXIST = 99;

    @Test
    void contextLoads() {
        assertThat(userResource).isNotNull();
    }

    @Test
    public void shouldGetAllUsers() {
        //given
        List<User> expectedUsers = userRepository.findAll();
        List<User> actualUsers = userResource.getUsers();

        //expected
        Integer expectedSize = expectedUsers.size();
        Integer actualSize = actualUsers.size();

        String expectedEmail = expectedUsers.get(0).getEmail();
        String actualEmail = actualUsers.get(0).getEmail();

        //then
        assertEquals(expectedSize, actualSize);
        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void shouldGetUserByIdSuccess() {
        //given
        User actualUser = userResource.getUserById(Optional.of(EXISTING_USER_ID));

        //expected
        String actualEmail = actualUser.getEmail();

        //then
        assertEquals(EXISTING_USER_EMAIL, actualEmail);
    }

    @Test
    public void shouldGetUserByIdBadRequest() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.getUserById(Optional.empty());
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.BAD_REQUEST.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldGetUserByIdNotFound() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.getUserById(Optional.of(USER_ID_NOT_EXIST));
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_NOT_FOUND.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.NOT_FOUND.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldCreateUserSuccess() {
        // expected
        Integer sizeNotExpected = (int) userRepository.count();

        //given
        userResource.createUsers(createNewUser());
        Integer actualSize = (int) userRepository.count();

        //then
        assertNotEquals(sizeNotExpected, actualSize);
    }

    @Test
    public void shouldCreateUserMethodNotAllowed() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            User newUser = createNewUser();
            newUser.setUserId(EXISTING_USER_ID);
            userResource.createUsers(newUser);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_INVALID_INPUT.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.METHOD_NOT_ALLOWED.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldCreateUserConflict() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            User newUser = createNewUser();
            newUser.setEmail(EXISTING_USER_EMAIL);
            userResource.createUsers(newUser);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_ALREADY_EXIST.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.CONFLICT.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdateUserByIdSuccess() {
        //given
        User existingUser = userRepository.findById(1).get();
        existingUser.setEmail("testUpdateUserById@gmail.com");

        User updateUser = userResource.updateUserById(Optional.of(existingUser.getUserId()), existingUser);
        String actualEmail = updateUser.getEmail();

        //then
        assertNotEquals(EXISTING_USER_EMAIL, actualEmail);
    }

    @Test
    public void shouldUpdateUserByIdBadRequest() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.updateUserById(Optional.empty(), null);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.BAD_REQUEST.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdateUserByIdIdentifierMismatch() {
        //given
        User user = createNewUser();
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.updateUserById(Optional.of(EXISTING_USER_ID), user);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_IDENTIFIER_MISMATCH.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.BAD_REQUEST.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdateUserByIdNotFound() {
        //given
        User user = createNewUser();
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.updateUserById(Optional.of(user.getUserId()), user);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_NOT_FOUND.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.NOT_FOUND.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldUpdateUserByIdConflict() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            User user = userRepository.findById(1).get();
            user.setEmail("pedro@gmail.com");
            userResource.updateUserById(Optional.of(user.getUserId()), user);
        });

        //expected
        String expectedMessage = UserErrorEnum.USER_EMAIL_TAKEN.gerErrorMessage();
        String expectedHttpStatus = HttpStatus.CONFLICT.toString();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldDeleteUserByIdSuccess() {
        //given
        userResource.deleteUserById(Optional.of(EXISTING_USER_ID));

        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.deleteUserById(Optional.of(EXISTING_USER_ID));
        });

        //expected
        String expectedHttpStatus = HttpStatus.NOT_FOUND.toString();
        String expectedMessage = UserErrorEnum.USER_NOT_FOUND.gerErrorMessage();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldDeleteUserByIdBadRequest() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.deleteUserById(Optional.empty());
        });

        //expected
        String expectedHttpStatus = HttpStatus.BAD_REQUEST.toString();
        String expectedMessage = UserErrorEnum.USER_INVALID_USER_ID.gerErrorMessage();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldDeleteUserByIdNotFound() {
        //given
        Exception exception = assertThrows(ResponseStatusException.class, () -> {
            userResource.deleteUserById(Optional.of(USER_ID_NOT_EXIST));
        });

        //expected
        String expectedHttpStatus = HttpStatus.NOT_FOUND.toString();
        String expectedMessage = UserErrorEnum.USER_NOT_FOUND.gerErrorMessage();

        String actualMessage = exception.getMessage();

        //then
        assertTrue(actualMessage.contains(expectedHttpStatus));
        assertTrue(actualMessage.contains(expectedMessage));
    }

    private User createNewUser() {
        return new User(
                4,
                "Test New User",
                "testnewuser@gmail.com",
                LocalDateTime.of(1234, Month.AUGUST, 3, 0, 0, 0),
                addressRepository.findById(1).get()
        );
    }


}
