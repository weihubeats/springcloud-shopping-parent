import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 10:58
 */
public class OracleToMysql {


    //        static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
    static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    // uat
    static final String MYSQL_URL = "jdbc:mysql://10.16.91.67:3306/zou?useUnicode=true&autoReconnect=true&allowMultiQueries=true&useSSL=false&serverTimezone=Asia/Shanghai";
    static final String ORACLE_URL = "jdbc:oracle:thin:@10.16.86.103:1601:FTSHARE";
    static final String MYSQL_USERNAME = "Fin_admin";
    static final String ORACLE_USERNAME = "aml";
    static final String MYSQL_PASSWORD = "Fin_admin#0824";
    static final String ORACLE_PASSWORD = "aml#0921";
    static final String SQL = "select `ID_`,USERNAME,POLITICAL,WRONGPASSWORD,PASSWORD from ACT_GE_PROPERTY";
    static int count = 0;
    static List<String> list = new ArrayList<>();
    static String oracleSql = "";
    static StringBuffer coulum = new StringBuffer();
    //所有列
    static String columnName;
    static int sumColum;

    public static void main(String[] args) {

        Connection mysqlCon = null;
        Connection oracleCon = null;
        PreparedStatement mysqlPs = null;
        PreparedStatement oraclePs = null;
        try {
            Class.forName(MYSQL_DRIVER);
            Class.forName(ORACLE_DRIVER);
            System.out.println("开始连接到数据库");
            mysqlCon = DriverManager.getConnection(MYSQL_URL, MYSQL_USERNAME, MYSQL_PASSWORD);
            oracleCon = DriverManager.getConnection(ORACLE_URL, ORACLE_USERNAME, ORACLE_PASSWORD);
            System.out.println("连接到数据库");
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
    // ,PreparedStatement oraclePs
    private static void getTables(Connection conn, Connection oracleCon) throws SQLException {
        DatabaseMetaData dbMetData = conn.getMetaData();
        // mysql convertDatabaseCharsetType null
        ResultSet rs = dbMetData.getTables(null,
                convertDatabaseCharsetType("zou", "mysql"), null,
                new String[] { "TABLE"});

        while (rs.next()) {
            if (rs.getString(4) != null
                    && (rs.getString(4).equalsIgnoreCase("TABLE"))) {
                String tableName = rs.getString(3).toLowerCase();
                System.out.print("tableName " + tableName + "\n");
                // 根据表名提取表里面信息：
                ResultSet colRet = dbMetData.getColumns(null, "%", tableName,
                        "%");

                colRet.last();
                //每个表的列数
                sumColum = colRet.getRow();
                colRet.beforeFirst();

                while (colRet.next()) {

                    columnName = colRet.getString("COLUMN_NAME");
                    if (columnName.equals("ID_")){
                        continue;
                    }
                    coulum.append(columnName + ",");


                }
                coulum = coulum.deleteCharAt(coulum.length()-1);
                System.out.println("所有列 " + coulum);
                System.out.println("总列数: " + sumColum);
                oracleSql = "select " + coulum + " from " + tableName;
                System.out.println("oracle执行sql为： " + oracleSql);
                Statement oracleps = oracleCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                ResultSet resultSet = null;
                try {
                    resultSet = oracleps.executeQuery(oracleSql + "");
                    System.out.println("清空sql");


                } catch (SQLSyntaxErrorException e) {
                    System.out.println("表不存在 " + tableName);
                    coulum.setLength(0);
                    continue;
                }

                //重置执行sql
                oracleSql ="";
                StringBuffer sql1 = new StringBuffer();
                //获取最后一行去,
                resultSet.last();
                int oracleCount = resultSet.getRow();
                resultSet.beforeFirst();
                System.out.println("总条数为 " + oracleCount);
                conn.setAutoCommit(false);
                if (oracleCount > 0) {
                    sql1.append("insert into " + tableName + "( "  + coulum + " )" + "values(");
                    int count2 = 0;
                    while (resultSet.next()) {
                        count2++;
                        for (int i =1; i <= sumColum; i++) {
                            //最后一列不需要,
                            if (i == sumColum) {
                                if (resultSet.getString(i)==null){
                                    sql1.append( resultSet.getString(i));
                                }
                                sql1.append("'" + resultSet.getString(i) + "'");
                            }
                            else {
                                if (resultSet.getString(i)==null){
                                    sql1.append(resultSet.getString(i) + ",");
                                }
                                sql1.append("'" + resultSet.getString(i) + "'" + ",");
                            }
                        }

                        if (count2 != oracleCount) {
                            sql1.append("),(");
                        } else {
                            sql1.append(")");
                        }


                    }

                    System.out.println("========================获取mysql执行sql为： " + sql1);
                    PreparedStatement preparedStatement = conn.prepareStatement(sql1 + "");
                    preparedStatement.executeUpdate();


                }
                coulum.setLength(0);
                resultSet.close();
                sql1.setLength(0);
                count++;
                System.out.println("-------------------------------");
                System.out.println("已成功表数量：" + count);

            }
        }
        conn.commit();
        System.out.println();



    }
}
