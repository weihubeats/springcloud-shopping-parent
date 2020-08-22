package com.zou.dao.db1.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zou.dao.db1.OrderDao;
import com.zou.dao.db1.mapper.OrderMapper;
import com.zou.model.Order;
import org.springframework.stereotype.Repository;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 9:31
 * @Description TODO
 */
@Repository
public class OrderDaoImpl extends ServiceImpl<OrderMapper, Order> implements OrderDao {
}
