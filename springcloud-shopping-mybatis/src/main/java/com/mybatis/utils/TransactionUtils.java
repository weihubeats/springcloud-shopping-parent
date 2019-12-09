package com.mybatis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * @author WH
 * @version 1.0
 * @date 2019/12/5 22:27
 */
@Component
@Scope("prototype")
//设置为原型，防止多线程操作引起的事务问题
public class TransactionUtils {

    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    //开启事务
    public TransactionStatus begin() {
        TransactionStatus transactionStatus = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    //提交事务
    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
    }

    //回滚事务
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
