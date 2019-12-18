package com.midea.dto;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 16:13
 */

public class Table {

    //表名
    String tablename;
    //所有列,必须使用list集合，使用string直接拼接后期对关键字冲突不好拼接''
    List<String> columns;
    //列总数
    int columnSum;
    //表中数据总量
    int sum;
    //mysql执行sql语句
    String mysqSb;
    //oracle执行sql
    String oracleSb;
    //放入队列中的sql总数
    private AtomicInteger count = new AtomicInteger();




//    public Table(String tablename, List<String> columns, int columnSum, int sum, String mysqSb, String oraacleSb, AtomicInteger count) {
//        this.tablename = tablename;
//        this.columns = columns;
//        this.columnSum = columnSum;
//        this.sum = sum;
//        this.mysqSb = mysqSb;
//        this.oraacleSb = oraacleSb;
//        this.count = count;
//    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public int getColumnSum() {
        return columnSum;
    }

    public void setColumnSum(int columnSum) {
        this.columnSum = columnSum;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getMysqSb() {
        return mysqSb;
    }

    public void setMysqSb(String mysqSb) {
        this.mysqSb = mysqSb;
    }

    public String getOracleSb() {
        return oracleSb;
    }

    public void setOracleSb(String oracleSb) {
        this.oracleSb = oracleSb;
    }

    public AtomicInteger getCount() {
        return count;
    }

    public void setCount(AtomicInteger count) {
        this.count = count;
    }
}
