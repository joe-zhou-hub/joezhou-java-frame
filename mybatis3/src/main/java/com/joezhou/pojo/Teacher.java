package com.joezhou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoeZhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {
    private String id;
    private String name;
    private Integer gender;
    private Integer age;
    private String info;
}