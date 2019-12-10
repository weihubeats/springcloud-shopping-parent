package com.mybatis.service.impl;

import com.mybatis.dao.UserDao;
import com.mybatis.po.User;
import com.mybatis.service.UserService;
import com.mybatis.utils.WhTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/1 18:01
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUser() {
        return userDao.getUser();
    }

    @Override
    @WhTransaction
    public void addUser(User user) {
        userDao.addUser(user);
        //模拟发生异常
        userDao.addUser(user);
    }


}
