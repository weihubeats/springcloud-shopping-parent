package com.midea.main;

import com.midea.dto.Table;
import com.midea.util.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 17:06
 */

public class Main {

    static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    static final String MYSQL_URL = "jdbc:mysql://10.16.91.67:3306/aml-test?useUnicode=true&autoReconnect=true&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";
    static final String MYSQL_USERNAME = "Fin_admin";
    static final String MYSQL_PASSWORD = "Fin_admin#0824";


    static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    static final String ORACLE_URL = "jdbc:oracle:thin:@10.16.86.103:1601:FTSHARE";
    static final String ORACLE_USERNAME = "aml";
    static final String ORACLE_PASSWORD = "aml#0921";

    static Connection mysqlCon = null;
    static Connection oracleCon = null;

    static List<Table> tables = new ArrayList<>();

    static BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(30);

    static private volatile boolean FLAG = true;

    public static void main(String[] args) throws Exception {
        try {
            //初始化连接
            mysqlCon = init(MYSQL_DRIVER, MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            oracleCon = init(ORACLE_DRIVER, ORACLE_URL, ORACLE_USERNAME, ORACLE_PASSWORD);


            DatabaseMetaData dbMetData = mysqlCon.getMetaData();


            //获取元数据rs
            ResultSet rs = Utils.getRs(dbMetData);

            //获取所有表名
            tables = Utils.getTables(rs);


            //获取所有表的所有列 及每个表的列总数
            tables = Utils.setAllColumns(dbMetData, tables);


            //获取oracle执行sql
            tables = Utils.generateOracleSelectSQL(tables);


            // 消费线程，插入mysql
            Consumer consumer = new Consumer(blockingQueue/*, mysqlCon, FLAG*/);
            Thread consumerThread = new Thread(consumer);
            consumerThread.start();


            //通过查询oracleSql获取mysqlSql,放入BlockingQueue
            Utils.generateMysqlInsertSQL(tables, oracleCon, blockingQueue);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (mysqlCon != null) {
                try {
                    mysqlCon.close();
                    oracleCon.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }

    }

    //初始化连接
    public static Connection init(String driver, String url, String username, String password) throws Exception {
        Class.forName(driver);
        System.out.println("驱动： " + driver + "获取连接成功");
        return DriverManager.getConnection(url, username, password);
    }


}
