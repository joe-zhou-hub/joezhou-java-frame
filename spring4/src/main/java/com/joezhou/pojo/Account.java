package com.joezhou.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author JoeZhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private Integer id;
    private String username;
    private String password;
}