package com.midea.util;

import com.midea.dto.Table;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 11:53
 */
public class Utils {


    static int BATCH = 50;
    static final int PAGING = 100;


  /*  public static String convertDatabaseCharsetType(String in, String type) {
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
    }*/


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
    public static List<Table> getTables(ResultSet rs) throws Exception {
        /*
        * ResultSet rs = dbMetData.getTables(null,
                Utils.convertDatabaseCharsetType("zou", "mysql"), null,
                new String[] { "TABLE"});
        *
        * */
        List<Table> tables = new ArrayList<>();
        System.out.println("获取表总数为：" + Utils.sumColumn(rs));
        while (rs.next()) {
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE"))) {
                Table table = new Table();

                String tableName = rs.getString(3).toLowerCase();


                /**
                 * liuwenbo
                 */

                /*if(("T2A_TRANS").equalsIgnoreCase(tableName)
                        || ("T2A_VALIDATE_JOB_LOG").equalsIgnoreCase(tableName)
                        ){
                        continue;
                }*/


                table.setTablename(tableName);

                tables.add(table);

            }
        }
//        rs.close();
        return tables;

    }

    /**
     * 获取所有表的 列 及列总数
     * @param dbMetData
     * @param tables 所有表
     * @return
     * @throws Exception
     */
    public static List<Table> setAllColumns(DatabaseMetaData dbMetData, List<Table> tables) throws Exception {
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
    public static Table getColumn(String tableName, DatabaseMetaData dbMetData) throws Exception {
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
     *  获取oracle执行sql
     * @param tables
     * @return
     */
    public static List<Table> generateOracleSelectSQL(List<Table> tables) {
        StringBuffer sb = new StringBuffer("select ");
        for (Table table : tables) {
            for (String column : table.getColumns()) {
                sb.append(column + ",");
            }
            //去掉拼接sql后最后一个，
            sb = sb.deleteCharAt(sb.length()-1);
            sb.append(" from " + table.getTablename());
            table.setOracleSb(sb + "");
            //重新new一个对象 不能使用 setlength 因为指向同一个引用
             sb = new StringBuffer("select ");


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
                null, null,
                new String[] { "TABLE"});
//        rs.close();
        return rs;

    }

    //  获取mysql所有执行sql
    public static void generateMysqlInsertSQL(List<Table> tables, Connection oracleCon, BlockingQueue<String> blockingQueue) throws Exception {


        ResultSet resultSet = null;
        PreparedStatement statement = null;
        //查询总数据了sql
        String sql;
        //表名
        String tableNmae;
        //表总数据量
        int sum;


        for (Table table : tables) {


            String oracleSql = table.getOracleSb();
            //获取表名
            tableNmae = table.getTablename();

            try {

                /**
                 * todo  内存溢出
                 * 大对象，沾满老年代 （老年代2.5G）
                 * 1 分页
                 * 2 大内存
                  */

                System.out.println("表: " + tableNmae  + "  开始加载数据..........");
                //查询表数据总量
                sum =  Utils.getsum(oracleCon, tableNmae);
                //将表总量回插table对象中
                table.setSum(sum);
//                statement.setQueryTimeout(60 * 60);
                //对大数据表进行分页 10000W为分页基础 如果效率不够可以多开几个线程，同时消费者也需要新加几个
                if (sum > PAGING) {
                    Utils.putblocking(sum, oracleCon, table, blockingQueue);
                    return;
                }

                resultSet = statement.executeQuery(oracleSql + "");

                //如果表中有数据则拼接sql存放在BlockingQueue

                if (table.getSum() > 0) {
                  generateMysqlInsertSQLByTable(resultSet, table, blockingQueue);
                }


                // 释放 resultSet
                if(resultSet != null) {
                    resultSet.close();
                }
                if(statement != null) {
                    statement.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("表: " + table.getTablename() + "不存在");
            }


        }
        System.out.println("所有表导出完成！！");
    }

    //通过rs拼接单个mysql SQL
    public static void generateMysqlInsertSQLByTable(ResultSet rs, Table table, BlockingQueue<String> queue) throws Exception {


        //获取mysql拼接列
        String mysqlColumn = Utils.getMysqlColum(table);

        /**
         * todo  这个地方一条一条的生成sql语句
         * 100条一个sql
         */

        // prefix
        StringBuffer mysqlSql = new StringBuffer("insert into " + table.getTablename() + "( " + mysqlColumn + " )" + "values(");
        int count = 0;


        while (rs.next()) {

            count++;

            // a,b,c,d
            String singleValue = geneateOneSQL(rs, table);

            // insert values( +  a,b,c,d
            mysqlSql.append(singleValue);


            if (count % BATCH == 0) {
                // insert values( +  a,b,c,d  + )
                mysqlSql.append(")");
                // 放入队列
                queue.put(mysqlSql.toString());


                mysqlSql = new StringBuffer("insert into " + table.getTablename() + "( " + mysqlColumn + " )" + "values(");


            } else {
                // insert values( +  a,b,c,d  + ),(
                mysqlSql.append("),(");
            }

        }

        if (count % BATCH != 0) {
            mysqlSql = mysqlSql.deleteCharAt(mysqlSql.length() - 1);
            mysqlSql = mysqlSql.deleteCharAt(mysqlSql.length() - 1);

            // 放入队列
            queue.put(mysqlSql.toString());
        }


    }



    public static String geneateOneSQL(ResultSet rs, Table table) throws Exception {

        StringBuffer singleSQL = new StringBuffer("");

        //  返回一条数据的values  'a','b','c','d'
        for (int i =1; i <= table.getColumnSum(); i++) {

            String value = rs.getString(i);

            if (value == null) {
                //为null 不拼接 ''
                singleSQL.append(value + ",");
            } else {
                if (value.contains("'")) {
                    value = value.replace("'","''");
                }
                singleSQL.append("'" + value + "'" + ",");
            }
        }


        singleSQL = singleSQL.deleteCharAt(singleSQL.length() - 1);//去掉末尾逗号

        return  singleSQL.toString();

    }

    /**
     * 获取mysql 拼接sql列
     * @param table
     * @return
     */
    public static String getMysqlColum(Table table) {
        StringBuffer sb = new StringBuffer();
        for (String colum : table.getColumns()) {
            sb.append("`" + colum + "`,");
        }
        //去掉最后的,
        sb = sb.deleteCharAt(sb.length() - 1);
        return sb + "";
    }

    /*
     *  查询表总数据量
     * */
    public static int getsum(Connection connection, String tableNmae) throws Exception {
        int sum = 0;

        String sql = "select count(1) from " + tableNmae;
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            sum = rs.getInt(1);

        }
        System.out.println("表数据总量为: " + sum);
        ps.close();
        return sum;

    }


    /*
    * oracle分页
    *
    * 得到分页 SQL
    * */

    public static String getResultSet(int index, int lastPage, Table table) {
        StringBuffer sb = new StringBuffer("select ");
            for (String column : table.getColumns()) {
                sb.append(column + ",");
            }
            //去掉拼接sql后最后一个，
            sb = sb.deleteCharAt(sb.length()-1);
        sb.append(" FROM (SELECT ROWNUM AS rowno, t.* FROM " + table.getTablename() + " t "
        + "WHERE  ROWNUM <=" + lastPage + ") table_alias " +
                " WHERE table_alias.rowno >=" + index);

        return  sb.toString();
    }


    /*
    * 数据量超过1W做分页循环插入
    * */

    public static void putblocking(int sum, Connection con, Table table, BlockingQueue queue) throws Exception {
        String sql = "";
        //初始页数
        int index = 1;
        //最后一页
        int lastPage = PAGING;
        while (sum > 0){
            sql = getResultSet(index, lastPage, table);
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            //执行sql并放入队列中
            generateMysqlInsertSQLByTable(rs, table, queue);
            rs.close();
            ps.close();

            sum = sum -PAGING;
            index = lastPage + 1;
            if (sum > PAGING) {
                lastPage = lastPage + PAGING;
            } else {
                lastPage = lastPage + sum;
            }

        }




    }







}
