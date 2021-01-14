package com.joezhou.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author JoeZhou
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {
    @JsonIgnore
    private Integer id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss", locale="zh", timezone="UTC")
    private Date birthday;
}