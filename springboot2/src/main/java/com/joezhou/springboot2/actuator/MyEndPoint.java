package com.joezhou.springboot2.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author JoeZhou
 */
@Configuration
@Endpoint(id = "students")
public class MyEndPoint {

    @ReadOperation
    public List<Map<String, String>> execute() {
        List<Map<String, String>> users = new ArrayList<>();
        Map<String, String> map;
        map = new HashMap<>(3);
        map.put("id", "1");
        map.put("name", "赵四");
        map.put("age", "58");
        users.add(map);
        map = new HashMap<>(3);
        map.put("id", "2");
        map.put("name", "刘能");
        map.put("age", "59");
        users.add(map);
        return users;
    }
}