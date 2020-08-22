package com.zou.service.Impl;

import com.zou.dao.db0.UserDao;
import com.zou.dao.db1.OrderDao;
import com.zou.model.Order;
import com.zou.model.User;
import com.zou.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 9:36
 * @Description TODO
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    UserDao userDao;


    @Override
    public void getOrderAndUser() {
        List<Order> list = orderDao.list();
        System.out.println(list);
        List<User> list1 = userDao.list();
        System.out.println(list1);

    }
}
