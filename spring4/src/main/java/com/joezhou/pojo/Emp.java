package com.joezhou.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author JoeZhou
 */

@Data
public class Emp implements Serializable {
    private String name;
    private Integer age;
    private String[] hobby;
    private List<String> userList;
    private Set<String> userSet;
    private Map<String, Object> userMap;
    private Properties jdbc;
}