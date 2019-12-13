import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author wh
 * @version 1.0
 * @date 2019-12-13 11:53
 */
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


    public static int sumColumn(ResultSet rs) throws Exception {
        rs.last();
        //每个表的列数
        int sumColum = rs.getRow();
        rs.beforeFirst();
        return  sumColum;
    }




}
