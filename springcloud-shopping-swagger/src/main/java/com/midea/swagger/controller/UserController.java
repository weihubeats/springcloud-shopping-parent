package com.midea.swagger.controller;

import com.midea.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-19 16:55
 */
@RestController
@RequestMapping("/user")
@Api(tags = "1.0.0-SNAPSHOT", description = "用户管理", value = "用户管理")
public class UserController {
    static User user;
    static List<User> users;

    //模拟数据库查询
    static {
        user = new User();
        users = new ArrayList<>();
        user.setId(10);
        user.setName("奏");
        user.setPassword("123456");
        User user1 = new User();
        user1.setId(20);
        user1.setName("阿离");
        user1.setPassword("6666");
        users.add(user);
        users.add(user1);
    }

    @ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息")
    @GetMapping("/{id}")
    public @ResponseBody
    User getUserById(@PathVariable(value = "id") Integer id) {
        return user;
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public  @ResponseBody List<User> getUserList() {
        return users;
    }

    @ApiOperation(value = "新增用户", notes = "根据用户实体创建用户")
    @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User")
    @PostMapping("/add")
    public @ResponseBody Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "删除用户", notes = "根据用户id删除用户")
    @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path")
    @DeleteMapping("/{id}")
    public @ResponseBody Map<String, Object> deleteUser(@PathVariable(value = "id") Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }

    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体", required = true, dataType = "User") })
    @PutMapping("/{id}")
    public @ResponseBody Map<String, Object> updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", "success");
        return map;
    }





}
