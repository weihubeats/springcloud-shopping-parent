package com.midea.dto;

import lombok.Data;

import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 16:13
 */
@Data
public class Table {

    //表名
    String tablename;
    //列
    List<String> columns;
    //列总数
    int columnSum;
    //表中数据总量
    int sum;
    //mysql执行sql语句
    StringBuffer mysqSb = new StringBuffer();
    //oracle执行sql
    StringBuffer oraacleSb;

}
