package com.joezhou.springboot2;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JoeZhou
 */
class RestTemplateTest {

    private RestTemplate restTemplate = new RestTemplate();

    @Test
    void getForObject() {
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

    @Test
    void getForEntity() {
        String baseUrl = "http://127.0.0.1:8080/api/rest-template/get-mapping";

        // with no param...
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl, String.class);
        System.out.println("body: " + response.getBody());
        System.out.println("statusCode: " + response.getStatusCode());
        System.out.println("statusCodeValue: " + response.getStatusCodeValue());
        System.out.println("headers: " + response.getHeaders());

        // with k-v param...
        System.out.println(restTemplate.getForEntity(baseUrl + "?name=joezhou", String.class));

        // with array param...
        System.out.println(restTemplate.getForEntity(baseUrl + "?name={name}", String.class, "joezhou"));

        // with map param...
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", "joezhou");
        System.out.println(restTemplate.getForEntity(baseUrl + "?name={name}", String.class, params));
    }

    @Test
    void postForObject() {
        String baseUrl = "http://127.0.0.1:8080/api/rest-template/post-mapping";

        // with no param...
        System.out.println(restTemplate.postForObject(baseUrl, null, String.class));

        // with k-v param...
        System.out.println(restTemplate.postForObject(baseUrl + "?name=joezhou", null, String.class));

        // with array param...
        System.out.println(restTemplate.postForObject(baseUrl + "?name={name}", null, String.class, "joezhou"));

        // with map param...
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", "joezhou");
        System.out.println(restTemplate.postForObject(baseUrl + "?name={name}", null, String.class, params));

        // with request-body param...
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "joezhou");
        System.out.println(restTemplate.postForObject(baseUrl, requestBody, String.class));
    }

    @Test
    void postForEntity() {
        String baseUrl = "http://127.0.0.1:8080/api/rest-template/post-mapping";

        // with no param...
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl, null, String.class);
        System.out.println("body: " + response.getBody());
        System.out.println("statusCode: " + response.getStatusCode());
        System.out.println("statusCodeValue: " + response.getStatusCodeValue());
        System.out.println("headers: " + response.getHeaders());

        // with k-v param...
        System.out.println(restTemplate.postForEntity(baseUrl + "?name=joezhou", null, String.class));

        // with array param...
        System.out.println(restTemplate.postForEntity(baseUrl + "?name={name}", null, String.class, "joezhou"));

        // with map param...
        Map<String, Object> params = new HashMap<>(1);
        params.put("name", "joezhou");
        System.out.println(restTemplate.postForEntity(baseUrl + "?name={name}", null, String.class, params));

        // with request-body param...
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "joezhou");
        System.out.println(restTemplate.postForEntity(baseUrl, requestBody, String.class));
    }

    @Test
    void redirect() {
        String baseUrl = "http://127.0.0.1:8080/api/rest-template/redirect?name=joezhou";
        System.out.println(restTemplate.postForLocation(baseUrl,null));
    }
}
