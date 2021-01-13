package com.joezhou.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JoeZhou
 */
@Data
public class User implements Serializable {
    private String name;
    private Integer age;
    private Boolean gender;
}
