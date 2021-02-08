package com.joezhou.springboot2jwt.jwt;

import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User login(User user) {
        String username, password;
        if (user == null || (username = user.getUsername()) == null || (password = user.getPassword()) == null) {
            return null;
        }

        String usernameFromDb = "admin";
        String passwordFromDb = "123";
        if (usernameFromDb.equals(username) && passwordFromDb.equals(password)) {
            return new User(1, "admin", "123", "admin.jpg");
        } else {
            return null;
        }
    }
}