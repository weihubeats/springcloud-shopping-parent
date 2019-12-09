package com.mybatis.service;

import com.mybatis.po.User;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/1 18:00
 */
public interface UserService {

    List<User> getUser();

    void addUser(User user);


}
