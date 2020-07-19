package com.zou.dao;


import com.zou.ShardingSphereApplication;
import com.zou.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 14:27
 * @Description TODO
 */
@SpringBootTest(classes = ShardingSphereApplication.class)
@RunWith(SpringRunner.class)
public class OrderDaoTest {

    @Autowired
    OrderDao orderDao;


    @Test
    public void insert() {
        for (int i = 0; i < 1000; i++) {
            long userId = i;
            long orderId = i + 1;
            Order order = new Order();
            order.setUserName("阿离");
            order.setUserId(userId);
            order.setOrderId(orderId);
            int result = orderDao.addOrder(order);
        }
    }

    @Test
    public void testQueryList() {
        Order order = new Order();
        order.setUserId(2l);

        List<Order> list = orderDao.getOrders(order);
        list.forEach(s -> System.out.println(s));
    }


    @Test
    public void getOrders() {
        Order order = new Order();
        order.setUserId(8l);
        order.setOrderId(8l);
       orderDao.getOrders(order);

    }

}