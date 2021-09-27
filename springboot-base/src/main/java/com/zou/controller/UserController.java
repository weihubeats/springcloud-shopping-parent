package com.zou.controller;

import com.google.common.collect.Lists;
import com.zou.annotation.ZouResponseBody;
import com.zou.model.Student;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : wh
 * @date : 2021/6/1 20:02
 * @description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @ZouResponseBody
    @GetMapping("/user")
    public List<Student> getUsers() {
        Student student = new Student();
        student.setName("小奏");
        student.setAge(22);
        Student student1 = new Student();
        student.setName("小明");
        student.setAge(23);
        return Lists.newArrayList(student, student1);
    }

}
