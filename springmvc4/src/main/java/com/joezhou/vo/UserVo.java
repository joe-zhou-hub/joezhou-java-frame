package com.joezhou.vo;

/**
 * @author JoeZhou
 */

import com.joezhou.pojo.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author JoeZhou
 */
@Data
public class UserVo implements Serializable {
    private User user;
    private Integer[] ids;
    private List<User> users;
}
