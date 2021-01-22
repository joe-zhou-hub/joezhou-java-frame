package com.joezhou.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.util.Date;

/**
 * @author JoeZhou
 */
@Data
public class Teacher implements Serializable {

    @NotNull(message = "email is null...", groups = GroupA.class)
    @Email(message = "email is error...", groups = GroupA.class)
    private String email;

    @NotNull(message = "birth is null...", groups = GroupB.class)
    @Past(message = "birth is error...", groups = GroupB.class)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    public interface GroupA {
    }

    public interface GroupB {
    }
}