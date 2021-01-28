package com.joezhou.springboot2;

import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoeZhou
 */
public class RestTemplateTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void getMapping() {
        String baseUrl = "http://127.0.0.1:8080/api/rest-template/get-mapping";

        // with no param...
        System.out.println(restTemplate.getForObject(baseUrl, String.class));

        // with k-v param...
        System.out.println(restTemplate.getForObject(baseUrl + "?name=joezhou", String.class));

        // with array param...
        System.out.println(restTemplate.getForObject(baseUrl + "?name={name}", String.class, "joezhou"));

        // with map param...
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", "joezhou");
        System.out.println(restTemplate.getForObject(baseUrl + "?name={name}", String.class, params));
    }
}
