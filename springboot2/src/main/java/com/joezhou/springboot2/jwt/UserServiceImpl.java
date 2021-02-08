package com.joezhou.springboot2.jwt;

import org.springframework.stereotype.Service;

/**
 * @author JoeZhou
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User login(User user) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return null;
        }
        return new User(1, "admin", "123", "admin.jpg");
    }
}