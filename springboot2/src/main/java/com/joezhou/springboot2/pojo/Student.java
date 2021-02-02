package com.joezhou.springboot2.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author JoeZhou
 */
@Data
public class Student implements Serializable {

    private Integer id;
    private String name;
    private Integer gender;
    private Integer age;
    private String info;
}
