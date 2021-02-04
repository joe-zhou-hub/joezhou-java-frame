package com.joezhou.springboot2.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoeZhou
 */
@Configuration
@Endpoint(id = "my-end-point")
public class MyEndPoint {
    @ReadOperation
    public Map<String, String> execute() {
        Map<String, String> map = new HashMap<>(3);
        map.put("key-01", "value-01");
        map.put("key-02", "value-02");
        map.put("key-03", "value-03");
        return map;
    }
}