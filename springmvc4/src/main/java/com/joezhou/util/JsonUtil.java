package com.joezhou.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author JoeZhou
 */
public class JsonUtil {

    /**
     * build string in JSON format for front
     *
     * @param statusCode the status code of response
     * @param message    the status message of response
     * @param data       the data of response
     * @return string in JSON format
     */
    public static String build(int statusCode, String message, Object data) {
        String result = "";

        Map<String, Object> map = new HashMap<>(3);
        map.put("statusCode", statusCode);
        map.put("message", message);
        map.put("data", data);

        ObjectMapper objectMapper = new ObjectMapper()
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
                .setDateFormat(new SimpleDateFormat("yyyy/MM/dd hh:mm:ss"));

        try {
            result = objectMapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            System.out.println("writeValueAsString() error...");
            e.printStackTrace();
        }
        return result;
    }

    public static String build(Object data) {
        return build(200, "ok", data);
    }

    public static String build(int statusCode, String message) {
        return build(statusCode, message, null);
    }
}
