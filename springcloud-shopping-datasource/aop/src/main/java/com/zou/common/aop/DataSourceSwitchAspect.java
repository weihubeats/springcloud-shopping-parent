package com.zou.common.aop;

import com.zou.common.config.DbContextHolder;
import com.zou.common.constans.Constant;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author WH
 * @version 1.0
 * @date 2020/8/22 8:39
 * @Description TODO
 */
@Component
@Order(value = -100)
@Aspect
@Slf4j
public class DataSourceSwitchAspect {

    private static final String DB0= "execution(* com.zou.dao.db0..*.*(..))";

    private static final String DB1 = "execution(* com.zou.dao.db1..*.*(..))";

    @Pointcut(DB0)
    private void db0Aspect() {
    }

    @Pointcut(DB1)
    private void db1Aspect() {
    }


    @Before("db0Aspect()")
    public void dsc() {
        DbContextHolder.setDbType(Constant.DB0);
    }

    @Before("db1Aspect()")
    public void admin() {
        DbContextHolder.setDbType(Constant.DB1);
    }


}
