package rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/21 20:02
 */
public class MQConnectionUtils {
    private static final String IP = "49.233.150.105";
    private static final Integer PORT = 5672;
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "admin";

    public static Connection newConnection() throws Exception {
        //定义连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置服务地址
        factory.setHost(IP);
        //设置端口号
        factory.setPort(5672);
        //设置账号信息，用户名、密码、vhost
        factory.setUsername(USERNAME);
        factory.setPassword(PASSWORD);
        factory.setVirtualHost("/admin_host");
        //创建连接
        Connection connection = factory.newConnection();
        return connection;

    }
}
