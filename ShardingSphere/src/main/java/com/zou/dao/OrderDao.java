package com.zou.dao;

import com.zou.entity.Order;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 14:03
 * @Description TODO
 */
@Repository
public interface OrderDao {

    // 查询某个用户订单列表
    List<Order> getOrders(Order order);

    // 插入订单信息
    int addOrder(Order orderInfo);
}
