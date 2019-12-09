package rabbitmq.customer;

import com.rabbitmq.client.*;
import rabbitmq.util.MQConnectionUtils;

import java.io.IOException;

/**
 * @author WH
 * @version 1.0
 * @date 2019/11/21 22:32
 */
public class CustomerEmailDirect {
    //队列名称
    private static final String EMAIL_QUEUE = "email_fanout";
    //交换机名称
    private static final String DESTINATION_NAME = "direct_destination";

    //routing key
    private static final String ROUTING_KEY = "email";
    private static final String ROUTING_KEY1 = "sms";

    public static void main(String[] args) throws Exception{
        System.out.println("邮件消费者启动");
        //1.建立连接
        Connection connection = MQConnectionUtils.newConnection();
        //2. 创建通道
        Channel channel = connection.createChannel();
        // 3.声明消费队列
        channel.queueDeclare(EMAIL_QUEUE, false, false, false, null);
        //4.消费者绑定交换机   参数1 队列 参数2交换机 参数3 routingKey
        channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, ROUTING_KEY);
        channel.queueBind(EMAIL_QUEUE, DESTINATION_NAME, ROUTING_KEY1);
        //5.消费者监听消息
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("邮件消费者获取消息" + msg);
            }
        };
        channel.basicConsume(EMAIL_QUEUE, true, defaultConsumer);

    }
}
