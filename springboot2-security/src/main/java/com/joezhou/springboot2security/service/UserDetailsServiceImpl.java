package com.joezhou.springboot2security.service;

import com.joezhou.springboot2security.pojo.Permission;
import com.joezhou.springboot2security.pojo.Role;
import com.joezhou.springboot2security.pojo.User;
import com.joezhou.springboot2security.util.DataUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoeZhou
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = DataUtil.getData().get(username);

        if (user == null) {
            throw new RuntimeException("username not exist...");
        }

        String password = "{noop}" + user.getPassword();
        System.out.println("password: " + password);

        return new org.springframework.security.core.userdetails.User(username, password, getAuthoritiesByUser(user));
    }

    private List<GrantedAuthority> getAuthoritiesByUser(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getKeyword()));
            for (Permission permission : role.getPermissions()) {
                authorities.add(new SimpleGrantedAuthority(permission.getKeyword()));
            }
        }
        return authorities;
    }
}