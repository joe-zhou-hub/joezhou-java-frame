package com.joezhou.springboot2security.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author JoeZhou
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Permission implements Serializable {
    private String keyword;
}