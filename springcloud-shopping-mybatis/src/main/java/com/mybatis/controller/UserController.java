package com.mybatis.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
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

        @GetMapping("/druid/stat")
        public Object druidStat(){
            // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据，除此之外 DruidStatManagerFacade 还提供了一些其他方法，你可以按需选择使用。
            return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
        }


}
