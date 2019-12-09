package rabbitmq.producer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.util.MQConnectionUtils;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/21 20:26
 */
public class Producer {
    private static final String QUEUE_NAME = "mq";

    public static void main(String[] args) throws Exception {
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        // 2.创建通道
        Channel channel = newConnection.createChannel();
        // 3.创建队列声明
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 10; i++) {
            String msg = "直接模式消息发送" + " " + i;
            System.out.println("生产者发送消息:" + msg);
            // 4.发送消息
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        }

        channel.close();
        newConnection.close();
    }

}


