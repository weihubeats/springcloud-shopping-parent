package com.zou.controller;

import com.zou.model.Order;
import com.zou.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 23:16
 * @Description TODO
 */

public class OrderController implements OrderService{

    @Autowired
    OrderService orderService;


    @Override
    public Order getOrder(String orderNo) {
        return orderService.getOrder("123");
    }
}
