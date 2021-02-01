package com.joezhou.springboot2.shield;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

/**
 * @author JoeZhou
 */
class ShieldTest {

    @Test
    void shield() {
        String baseUrl = "http://127.0.0.1:8080/api/shield/test?word=a-blue-apple";
        System.out.println(new RestTemplate().getForObject(baseUrl, String.class));
    }
}
