package com.joezhou.springboot2.jwt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoeZhou
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User{
    private Integer id;
    private String username;
    private String password;
    private String avatar;
}