package com.zou.dao.db0.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zou.dao.db0.UserDao;
import com.zou.dao.db0.mapper.UserMapper;
import com.zou.model.User;
import org.springframework.stereotype.Repository;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 9:29
 * @Description TODO
 */
@Repository
public class UserDaoImpl extends ServiceImpl<UserMapper, User> implements UserDao {
}
