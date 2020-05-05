package com.zou.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zou.dao.UserMapper;
import com.zou.entity.User;
import com.zou.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author WH
 * @version 1.0
 * @date 2020/5/5 16:16
 * @Description TODO
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
