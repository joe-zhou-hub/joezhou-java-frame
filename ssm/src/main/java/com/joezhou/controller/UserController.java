package com.joezhou.controller;

import com.github.pagehelper.PageInfo;
import com.joezhou.pojo.User;
import com.joezhou.service.UserService;
import com.joezhou.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author JoeZhou
 */
@RequestMapping("/api/user")
@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @RequestMapping("paging")
    public JsonData paging(Integer page, Integer size) {
        PageInfo<User> users = userService.paging(page, size);
        return new JsonData(users);

    }

    @ResponseBody
    @RequestMapping("select-by-id")
    public JsonData selectById(Integer id) {
        return new JsonData(userService.selectById(id));
    }

    @ResponseBody
    @RequestMapping("insert")
    public JsonData insert(User user) {
        userService.insert(user);
        return new JsonData(200, "insert success!");
    }

    @ResponseBody
    @RequestMapping("update-by-id")
    public JsonData updateById(User user) {
        userService.updateById(user);
        return new JsonData(200, "update success!");
    }

    @ResponseBody
    @RequestMapping("delete-by-ids")
    public JsonData deleteById(Integer[] ids) {
        userService.deleteByIds(ids);
        return new JsonData(200, "delete success!");
    }

}