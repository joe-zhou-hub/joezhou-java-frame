package com.joezhou.springboot2security.util;

import com.joezhou.springboot2security.pojo.User;
import com.joezhou.springboot2security.pojo.Role;
import com.joezhou.springboot2security.pojo.Permission;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author JoeZhou
 */
public class DataUtil {

    public static Map<String, User> getData() {

        Map<String, User> usersFromDb = new ConcurrentHashMap<>();

        // permission list
        Permission insertPermission = new Permission("insert");
        Permission selectPermission = new Permission("select");
        Permission updatePermission = new Permission("update");
        Permission deletePermission = new Permission("delete");

        // role list
        Role adminRole = new Role();
        adminRole.setKeyword("ROLE_ADMIN");
        adminRole.getPermissions().add(insertPermission);
        adminRole.getPermissions().add(selectPermission);
        adminRole.getPermissions().add(updatePermission);
        adminRole.getPermissions().add(deletePermission);

        Role commRole = new Role();
        commRole.setKeyword("ROLE_COMM");
        commRole.getPermissions().add(selectPermission);

        // user list
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("123");
        admin.getRoles().add(adminRole);
        usersFromDb.put(admin.getUsername(), admin);

        User zhaosi = new User();
        zhaosi.setUsername("zhaosi");
        zhaosi.setPassword("zhaosi");
        zhaosi.getRoles().add(commRole);
        usersFromDb.put(zhaosi.getUsername(), zhaosi);
        return usersFromDb;
    }
}