package com.zou.service.Impl;

import com.zou.Application;
import com.zou.service.TestService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 9:38
 * @Description TODO
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
class TestServiceImplTest {

    @Autowired
    TestService testService;

    @Test
    void getOrderAndUser() {
        testService.getOrderAndUser();
    }
}