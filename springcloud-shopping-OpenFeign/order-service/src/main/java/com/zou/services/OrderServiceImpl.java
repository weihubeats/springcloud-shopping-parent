package com.zou.services;

import com.zou.model.Order;
import com.zou.service.OrderService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author WH
 * @version 1.0
 * @date 2020/9/12 18:57
 * @Description TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Order getOrder(String orderNo) {
        System.out.println("收到的订单号为: " + orderNo);
        Order order = new Order();
        order.setOrderNo("123456");
        order.setOrderCount(5);
        order.setPrice(new BigDecimal("52.6"));
        return order;
    }
}
