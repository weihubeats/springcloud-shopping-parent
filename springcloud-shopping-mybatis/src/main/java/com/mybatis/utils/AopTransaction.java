package com.mybatis.utils;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/7 18:14
 */
//@Component
//@Aspect
@Slf4j
public class AopTransaction {

    private static final String POINT_CUT = "execution(* com.mybatis.service..*.*(..))";

    //定义切面
    @Pointcut(POINT_CUT)
    private void pointcut(){}

    @Autowired
    TransactionUtils transactionUtils;

    /*环绕通知
    * 方法执行前后执行
    *
    * */
    @Around("pointcut()")
    public void around(ProceedingJoinPoint pjp) throws Throwable {
        //1.获取代理对象的方法
        //获取方法名称
        String methodName = pjp.getSignature().getName();
        //获取目标对象
        Class<?> classTarget = pjp.getTarget().getClass();
        //获取目标对象类型
        Class<?>[] par = ((MethodSignature) pjp.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objmethod = classTarget.getMethod(methodName, par);
        //2.获取该方法上是否加注解
        WhTransaction whTransaction = objmethod.getDeclaredAnnotation(WhTransaction.class);
        //3. 如果存在事务注解则开启事务
        TransactionStatus transactionStatus = null;
        if (whTransaction != null) {
            log.info("开启事务");
            transactionStatus = transactionUtils.begin();
        }
        //4. 调用目标代理对象方法
        pjp.proceed();
        //5. 判断该方法是否有注解
        if (whTransaction != null) {
            //6.如果存在注解，提交事务
            log.info("提交事务");
            transactionUtils.commit(transactionStatus);
        }

    }

    //异常通知
    @AfterThrowing("pointcut()")
    public void afterTrowing() {
        log.info("回滚事务");
        //获取当前事务并回滚
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
    }






}
