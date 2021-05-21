package com.zou.dao.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zou.dao.OrderDao;
import com.zou.dao.entity.Order;
import com.zou.dao.mapper.OrderMapper;
import org.springframework.stereotype.Service;

/**
 * @author : wh
 * @date : 2021/5/21 10:33
 * @description:
 */
@Service
public class OrderDaoImpl extends ServiceImpl<OrderMapper, Order> implements OrderDao {
}
