package com.mybatis.controller;

import com.mybatis.po.User;
import com.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/1 18:34
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getuser")
    public List<User> getUser() {
        return userService.getUser();
    }

    @GetMapping("/adduser")
    public void addUser() {
        User user = new User("12365987", "6666", "女", "小奏");
        userService.addUser(user);
    }

}
