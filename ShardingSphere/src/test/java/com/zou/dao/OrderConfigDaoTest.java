package com.zou.dao;


import com.zou.ShardingSphereApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WH
 * @version 1.0
 * @date 2020/7/19 17:20
 * @Description TODO
 */
@SpringBootTest(classes = ShardingSphereApplication.class)
@RunWith(SpringRunner.class)
public class OrderConfigDaoTest {

    @Autowired
    OrderConfigDao orderConfigDao;

    @Test
  public  void getOrderConfig() {
        orderConfigDao.getOrderConfig();

    }



}