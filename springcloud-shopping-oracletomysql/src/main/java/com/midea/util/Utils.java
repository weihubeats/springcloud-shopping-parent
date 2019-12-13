package com.midea.util;

import com.midea.dto.Table;
import javafx.scene.control.Tab;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 11:53
 */
@Slf4j
public class Utils {

    public static String convertDatabaseCharsetType(String in, String type) {
        String dbUser;
        if (in != null) {
            if (type.equals("oracle")) {
                dbUser = in.toUpperCase();
            } else if (type.equals("postgresql")) {
                dbUser = "public";
            } else if (type.equals("mysql")) {
                dbUser = null;
            } else if (type.equals("mssqlserver")) {
                dbUser = null;
            } else if (type.equals("db2")) {
                dbUser = in.toUpperCase();
            } else {
                dbUser = in;
            }
        } else {
            dbUser = "public";
        }
        return dbUser;
    }


    /**
     *  获取查询结果总条数
     * @param rs
     * @return int  查询结果总条数
     * @throws Exception
     */
    public static int sumColumn(ResultSet rs) throws Exception {
        rs.last();
        //每个表的列数
        int sumColum = rs.getRow();
        rs.beforeFirst();
        return  sumColum;
    }



    /**
     * 获取数据库所有表名
     * @param rs
     * @return List<Table>
     * @throws Exception
     */
    public static List<Table> getTables(ResultSet rs) throws Exception{
        /*
        * ResultSet rs = dbMetData.getTables(null,
                Utils.convertDatabaseCharsetType("zou", "mysql"), null,
                new String[] { "TABLE"});
        *
        * */
        List<Table> tables = new ArrayList<>();
        log.info("获取表总数为：" + Utils.sumColumn(rs));
        while (rs.next()) {
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE"))) {
                Table table = new Table();
                table.setTablename(rs.getString(3).toLowerCase());
                tables.add(table);

            }
        }
        rs.close();
        return tables;

    }

    /**
     * 获取所有表的 列 及列总数
     * @param dbMetData
     * @param tables 所有表
     * @return
     * @throws Exception
     */
    public static List<Table> getAllColumn(DatabaseMetaData dbMetData, List<Table> tables) throws Exception {
        tables.forEach(table -> {
            try {
                Table table1 = getColumn(table.getTablename(), dbMetData);
                table.setColumns(table1.getColumns());
                table.setColumnSum(table1.getColumnSum());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        );
        return tables;
    }


    //根据表名获取 列 及列总数
    public static Table getColumn(String tableName, DatabaseMetaData dbMetData) throws Exception{
        Table table = new Table();
        String columnName;
        //列
        List<String> list = new ArrayList<>();
        ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
                "%");
        //获取列总数
        int sumColum = Utils.sumColumn(colRet);

        //获取所有列
        while (colRet.next()) {
            columnName = colRet.getString("COLUMN_NAME");
            //去掉mysql主键,如果为主键 列总数 -1
            if (columnName.equals("ID_")){
                sumColum--;
                continue;
            }
            list.add(columnName);
        }
        table.setColumns(list);
        table.setColumnSum(sumColum);
        return  table;
    }

    /**
     *  获取mysql执行sql
     * @param tables
     * @return
     */
    public static List<Table> getMysql(List<Table> tables) {
        StringBuffer sb = new StringBuffer("select ");
        for (Table table : tables) {
            for (String column : table.getColumns()) {
                sb.append(column + ",");
            }
            //去掉拼接sql后最后一个，
            sb = sb.deleteCharAt(sb.length()-1);
            sb.append(" from " + table.getTablename());
            table.setOraacleSb(sb);
            //制空 sb 重新拼接sql
            sb.setLength(0);
            sb.append("select ");


        }
        return tables;
    }


    /**
     * 获取元数据rs
     * @param dbMetData
     * @return
     * @throws Exception
     */
    public static ResultSet getRs(DatabaseMetaData dbMetData) throws Exception {
        //获取元数据 rs
        ResultSet rs = dbMetData.getTables(null,
                Utils.convertDatabaseCharsetType("zou", "mysql"), null,
                new String[] { "TABLE"});
        return rs;

    }






}
