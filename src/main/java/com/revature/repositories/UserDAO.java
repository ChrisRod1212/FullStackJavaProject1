package com.revature.repositories;

import com.revature.models.User;

public interface UserDAO {
    User getByUserId(int userId);
    void createUser(User user);
    User getByUsername(String username);
    User validateCredentials(String username, String password);
}
