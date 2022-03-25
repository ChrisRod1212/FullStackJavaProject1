package com.revature.services;

import com.revature.models.User;
import com.revature.repositories.UserDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserServiceTest {
    private UserService userService;
    private UserDAO userDAO = Mockito.mock(UserDAO.class);

    public UserServiceTest(){
        this.userService = new UserService(userDAO);
    }

    ///getByUserId
    @Test
    void getByUserIdInvalidId() {
        //arrange
        int expectedId = 270;
        User expectedUser = null;
        Mockito.when(userDAO.getByUserId(expectedId)).thenReturn(expectedUser);

        //act
        User actualUser = userService.getByUserId(expectedId);

        //assert
        Assertions.assertEquals(expectedUser,actualUser);

    }
    @Test
    void getByUserIdValidId() {
        //arrange
        int expectedId = 2;
        User expectedUser = new User(2, "username", "password", 1, "firstname", "lastname", "email", 5555555555L, "state", 11111, "address");
        Mockito.when(userDAO.getByUserId(expectedId)).thenReturn(expectedUser);

        //act
        User actualUser = userService.getByUserId(expectedId);

        //assert
        Assertions.assertEquals(expectedUser,actualUser);
    }

    ///validateCredentials
    @Test
    void validateCredentialsInvalidUsername() {
        //arrange
        String expectedUsername = "invalidUsername";
        String expectedPassword = "Password";
        User expectedUser = null;
        Mockito.when(userDAO.validateCredentials(expectedUsername,expectedPassword)).thenReturn(expectedUser);

        //act
        User actualUser = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedUser,actualUser);

    }
    @Test
    void validateCredentialsInvalidPassword() {
        //arrange
        String expectedUsername = "validUsername";
        String expectedPassword = "invalidPassword";
        User expectedUser = null;
        Mockito.when(userDAO.validateCredentials(expectedUsername,expectedPassword)).thenReturn(expectedUser);

        //act
        User actualUser = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedUser,actualUser);

    }
    @Test
    void validateCredentialsValidCredentials() {
        //arrange
        String expectedUsername = "validUsername";
        String expectedPassword = "validPassword";
        User expectedUser = new User(2, "validUsername", "validPassword", 1, "firstname", "lastname", "email", 5555555555L, "state", 11111, "address");
        Mockito.when(userDAO.getByUsername(expectedUsername)).thenReturn(expectedUser);

        //act
        User actualUser = userService.validateCredentials(expectedUsername,expectedPassword);

        //assert
        Assertions.assertEquals(expectedUser,actualUser);

    }

//todo createUserTest
    @Test
    void createUser() {
        //arrange
        String username = "user";
        String password = "password";
        String firstname = "firstname";
        String lastname = "lastname";
        String email = "email";
        Long phone = 5555555555L;
        String state = "state";
        int zip = 55555;
        String address = "address";
        int role = 1;
        User userToPass = new User(username,password,firstname,lastname,email,phone,state,zip,address,role);

        //act
        userService.createUser(userToPass);
        //assert
        Mockito.verify(userDAO).createUser(userToPass);
    }
}