package com.midea.main;

import com.midea.util.Utils;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 10:58
 */
@Slf4j
public class OracleToMysql {


    static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    // uat
    static final String MYSQL_URL = "jdbc:mysql://10.16.91.67:3306/aml-test?useUnicode=true&autoReconnect=true&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";
    static final String ORACLE_URL = "jdbc:oracle:thin:@10.16.86.103:1601:FTSHARE";
    static final String MYSQL_USERNAME = "Fin_admin";
    static final String ORACLE_USERNAME = "aml";
    static final String MYSQL_PASSWORD = "Fin_admin#0824";
    static final String ORACLE_PASSWORD = "aml#0921";
    static int count = 0;
    static String oracleSql = "";
    static StringBuffer coulum = new StringBuffer();
    //待``所有列，防止mysql出现关键字冲突
    static StringBuffer mysqlColumn = new StringBuffer();
    //所有列
    static String columnName;
    static int sumColum;
    static Connection mysqlCon = null;
    static Connection oracleCon = null;
    //临时值
    static String value;

    public static void main(String[] args) {

        try {
            init(MYSQL_DRIVER, MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            init(ORACLE_DRIVER, ORACLE_URL, ORACLE_USERNAME, ORACLE_PASSWORD);
            getTables(mysqlCon, oracleCon);
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


    private static void getTables(Connection conn, Connection oracleCon) throws Exception {
        DatabaseMetaData dbMetData = conn.getMetaData();
        //获取元数据 rs
        ResultSet rs = dbMetData.getTables(null,
                Utils.convertDatabaseCharsetType("zou", "mysql"), null,
                new String[] { "TABLE"});

        while (rs.next()) {
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE"))) {
                String tableName = rs.getString(3).toLowerCase();
                System.out.println("tableName " + tableName);
                // 根据表名提取表里面信息：
                ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
                        "%");
                //每个表的列数
                sumColum = Utils.sumColumn(colRet);


                while (colRet.next()) {
                    columnName = colRet.getString("COLUMN_NAME");
                    //去掉mysql主键
                    if (columnName.equals("ID_")){
                        sumColum--;
                        continue;
                    }
                    coulum.append(columnName + ",");
                    mysqlColumn.append("`" + columnName + "`" + ",");
                }
                //去掉末尾逗号
                coulum = coulum.deleteCharAt(coulum.length()-1);
                mysqlColumn = mysqlColumn.deleteCharAt(mysqlColumn.length()-1);

                System.out.println("所有列coulum " + coulum);
                System.out.println("总列数sumColum: " + sumColum);
                //拼接oracle SQL
                oracleSql = "select " + coulum + " from " + tableName;
                System.out.println("oracle执行sql为： " + oracleSql);
                Statement oracleps = oracleCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = null;
                try {
                    resultSet = oracleps.executeQuery(oracleSql + "");
                } catch (SQLSyntaxErrorException e) {
                    System.out.println("表不存在 " + tableName);
                    System.out.println("清空sql");
                    coulum.setLength(0);
                    mysqlColumn.setLength(0);
                    continue;
                }

                //重置执行sql
                oracleSql ="";
                StringBuffer mysqlSql = new StringBuffer();
                //获取最后一行去,
                int oracleCount = Utils.sumColumn(resultSet);

                System.out.println("表: " + tableName + "数据量为: " + oracleCount);
                //开启事物
                conn.setAutoCommit(false);
                //如果表里面有数据,执行mysql 脚本
                if (oracleCount > 0) {
                    mysqlSql.append("insert into " + tableName + "( "  + mysqlColumn + " )" + "values(");
                    while (resultSet.next()) {

                        for (int i =1; i <= sumColum; i++) {
                            value = resultSet.getString(i);
                            if (value == null){
                                //为null 不拼接 ''
                                mysqlSql.append(value + ",");
                            } else {
                                if (value.contains("'")) {
                                    value = value.replace("'","''");
                                }
                                    mysqlSql.append("'" + value + "'" + ",");
                            }

                        }
                        //去掉末尾逗号
                        mysqlSql = mysqlSql.deleteCharAt(mysqlSql.length()-1);
                        mysqlSql.append("),(");

                    }
                    //去掉末尾 ,(
                    mysqlSql = mysqlSql.deleteCharAt(mysqlSql.length()-1);
                    mysqlSql = mysqlSql.deleteCharAt(mysqlSql.length()-1);

                    System.out.println("mysql执行sql为： " + mysqlSql);
                    PreparedStatement preparedStatement = conn.prepareStatement(mysqlSql + "");
                    preparedStatement.executeUpdate();
                    System.out.println("mysql sql执行成功！");


                }
                coulum.setLength(0);
                mysqlColumn.setLength(0);
                resultSet.close();
                mysqlSql.setLength(0);
                count++;
                System.out.println("-------------------------------");
                System.out.println("已成功表数量：" + count);

            }
        }
        conn.commit();
        System.out.println();



    }
    //初始化连接
    public static Connection init(String driver, String url, String username, String password) throws Exception {
        Class.forName(driver);
        log.info("驱动： " + driver + "获取连接成功");
        return DriverManager.getConnection(url, username, password);
    }




}
