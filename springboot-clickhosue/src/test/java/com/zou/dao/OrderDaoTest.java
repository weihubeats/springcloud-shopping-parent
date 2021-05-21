package com.zou.dao;

import com.zou.SpringbootTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author : wh
 * @date : 2021/5/21 10:53
 * @description:
 */
public class OrderDaoTest extends SpringbootTestBase {

    @Autowired
    OrderDao orderDao;

    @Test
    public void testSelect() {
        orderDao.list();

    }

}