package com.joezhou.springboot2security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author JoeZhou
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role implements Serializable {
    private String keyword;
    private Set<Permission> permissions = new HashSet<>();
}