package rabbitmq.customer;

import com.rabbitmq.client.*;
import rabbitmq.util.MQConnectionUtils;

import java.io.IOException;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/21 20:28
 */
public class Customer {
    private static final String QUEUE_NAME = "mq";

    public static void main(String[] args) throws  Exception {
        System.out.println("消费者2启动");
        // 1.获取连接
        Connection newConnection = MQConnectionUtils.newConnection();
        /* 2.获取通道 */
        Channel channel = newConnection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //表示一次只消费一个消息
        channel.basicQos(1);
        //监听队列
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
                    throws IOException {
                String msgString = new String(body, "UTF-8");
                System.out.println("消费者获取消息:" + msgString);
                //手动应答
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        // 3.监听队列  true表示自动应答，false表示手动应答
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);

    }

}



