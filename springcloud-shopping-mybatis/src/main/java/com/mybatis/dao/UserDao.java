package com.mybatis.dao;

import com.mybatis.po.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/1 17:55
 */
public interface UserDao {

    public List<User> getUser();

    void addUser(User user);

}
